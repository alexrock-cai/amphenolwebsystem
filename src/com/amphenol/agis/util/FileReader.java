package com.amphenol.agis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.amphenol.Config;
import com.amphenol.agis.model.EepConfigModel;
import com.amphenol.agis.model.EepLogModel;
import com.amphenol.agis.model.ProductModel;
import com.amphenol.agis.model.ProgramModel;
import com.amphenol.agis.model.VerifyModel;

/**
 * 文件解析类
 * 根据获得的文件list
 * 自动处理并解析，excel，和txt类型文件，
 * 并将处理的结果保存到相应的数据库中。如果该文件是excel类型的文件
 * 并且是存储在DTS 文件路径下的，将保存到sys_product 表中。
 * 如果是存储在Shipment，将处理的数据保存到sys_product对应的表中。
 * @author rocky
 *
 */

public class FileReader 
{
	private FileScanner fileScanner = new FileScanner();
	private InputStream in=null;
	/**
	 * 处理获取的所有文件
	 * @param files
	 * @return
	 * @throws Exception 
	 */
	public boolean readFiles(List<File> files) throws Exception
	{
		for(File file : files)
		{
			readFile(file);
		}
		return true;
	}

	public  void readFile(File file) throws Exception 
	{
		// TODO Auto-generated method stub
		String filename=file.getName();
		if(filename.endsWith(".xls")||filename.endsWith(".xlsx"))
		{
			readExcel(file);
		}
		if(filename.endsWith(".txt"))
		{
			readText(file);
		}
		
	}

	private void readText(File file) throws Exception 
	{
		// TODO Auto-generated method stub
		String path = file.getParent().substring(EepConfigModel.dao.getWorkPath().length());
		//判断此文件是程序烧录日志文件还是验证日志文件
		if(path.startsWith(File.separator+"LOG_FILE"+File.separator+"Program"))
		{
			readProgramLog(file,path);
		}
		if(path.startsWith(File.separator+"LOG_FILE"+File.separator+"Verify"))
		{
			readVerifyLog(file,path);
		}
	}
	private void readVerifyLog(File file, String path) throws Exception 
	{
		// TODO Auto-generated method stub
		in=new FileInputStream(file);
		String str=null;
		BufferedReader reader= new BufferedReader(new InputStreamReader(in));
		while((str=reader.readLine())!=null)
		{
			if(str.startsWith("Barcode"))
			{
				VerifyModel verify;
				String[] temp=file.getName().split("_");
				String customerSn=temp[0];
				verify=VerifyModel.dao.findByCustomerSn(customerSn);
				if(verify !=null)
				{
					int times=verify.getInt("verify_times");
					verify.set("verify_times",times+1);
					if(str.endsWith("PASS"))
						verify.set("status",true);
					verify.set("timestamp",temp[1]+temp[2].substring(0,5));
					verify.update();
				}
				else
				{
					verify=new VerifyModel();
					verify.set("customer_sn",customerSn);
					verify.set("timestamp",temp[1]+temp[2].substring(0,5));
					if(str.endsWith("PASS"))
						verify.set("status",true);
					verify.save();
				}
				break;
			}
			if(str.startsWith("Result"))
			{
				VerifyModel verify;
				String[] temp=str.split(":");
				if(temp[1].equals("PASS"))
				{
					StringBuilder sb = new StringBuilder();
					sb.append(str).append(":");
					//连续读取4行
					for(int i=0;i<4;i++)
					{
						if((str=reader.readLine())!=null)
						{
							sb.append(str).append(":");
						}
						else
							break;
					}
					str=sb.toString();
					String[] t=str.split(":");
					String customerSn=t[7];
					String date=t[9];
					verify=VerifyModel.dao.findByCustomerSn(customerSn);
					if(verify !=null)
					{
						int times=verify.getInt("verify_times");
						verify.set("status",true);
						verify.set("timestamp", date);
						verify.set("verify_times",times+1);
						verify.update();
					}
					else
					{
						verify = new VerifyModel();
						verify.set("status", true);
						verify.set("customer_sn", customerSn);
						verify.set("timestamp", date);
						verify.set("verify_times",1);
						verify.save();
					}
					
				}
			}
		}
		
		reader.close();
		in.close();
		fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath()+path);
	}
	/**
	 * 处理烧录程序log文件
	 * @param file
	 * @param path
	 * @throws Exception
	 */
	private void readProgramLog(File file,String path) throws Exception 
	{
		// TODO Auto-generated method stub
		in=new FileInputStream(file);
		String str=null;
		BufferedReader reader= new BufferedReader(new InputStreamReader(in));
		while((str=reader.readLine())!=null)
		{
			if(str.endsWith("PASSED"))
			{
				ProgramModel program;
				String[] s = str.split("\t");
				program=ProgramModel.dao.findByCustomerSn(s[2]);
				if(program !=null)
				{
					int times=program.getInt("program_times");
					program.set("program_times",times+1);
					program.set("last_timestamp",s[0]);
					program.set("status", true);
					program.update();
				}
				else
				{
					program= new ProgramModel();
				
					program.set("customer_sn", s[2]);
					program.set("timestamp", s[0]);
					program.set("status",true);
					program.set("product_id", ProductModel.dao.getProductIdByCustomerSn(s[2]));
					program.set("program_times",1);
					program.set("last_timestamp",s[0]);
					
					program.save();
				}
			}
			
			if(str.endsWith("Passed"))
			{
				ProgramModel p ;
				String[] s=str.split("\t");
				String[] temp=s[2].split("\\s");
				p=ProgramModel.dao.findByCustomerSn(temp[0]);
				if(p !=null)
				{
					int times=p.getInt("program_times");
					if(temp[1].equals("Left"))
					{
						if(p.getBoolean("left_status"))
						{
							times=times+1;
						}
						else
						{
							p.set("left_status", true);
							times=times+1;
						}
					}
					if(temp[1].equals("Right"))
					{
						if(p.getBoolean("right_status"))
						{
							times=times+1;
						}
						else
						{
							p.set("right_status", true);
							times=times+1;
						}
					}
					
					p.set("last_timestamp", s[0]);
					p.set("program_times", times);
					
					p.update();
					
				}
				else
				{
					p=new ProgramModel();
					if(temp[1].equals("Left"))
					{
						p.set("left_status", true);
					}
					if(temp[1].equals("Right"))
					{
						p.set("right_status", true);
					}
					p.set("customer_sn", temp[0]);
					p.set("timestamp",s[0]);
					p.set("last_timestamp", s[0]);
					p.set("product_id", ProductModel.dao.findByCustomerSn(temp[0]));
					p.set("program_times", 1);
					
					p.save();
				}
			}
		}
		
		reader.close();
		in.close();
		fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath()+path);
	}

	/**
	 * 处理获取到的excel表 
	 * 判断此表是DTS 初始化的数据还是
	 * 仓库出货数据。
	 * 
	 * @param file
	 */
	private void readExcel(File file) 
	{
		
		try
		{
			in= new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(in);
			
			if(file.getParent().endsWith("DTS_DATA"))
			{
				
				//循环wb中所有的sheet
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet = wb.getSheetAt(sheetIndex);
					//循环row
					for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						if(row == null) continue;
						
						saveProduct(row);
						//showRow(row);
					
					}
				}
				//将处理过的文件移到已经处理文件目录中
				fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath());
			}
			
			if(file.getParent().endsWith("SHIP_DATA"))
			{
				
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet=wb.getSheetAt(sheetIndex);
					for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						if(row == null) continue;
						
						saveShipData(row);
						//showRow(row);
					}
				}
				//将处理过的文件移到已经处理文件目录中
				fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath());				
				
			}
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvalidFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(in!=null)
				{
					in.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	private boolean saveShipData(Row row) 
	{
		// TODO Auto-generated method stub
		String custSn=row.getCell(7).getRichStringCellValue().getString();
		ProductModel p = ProductModel.dao.findByCustomerSn(custSn) ;
		if( p !=null)
		{
			p.set("onship", true);
			return p.update();
		}
		else
		{
			EepLogModel log = new EepLogModel();
			log.set("customer_sn", custSn);
			log.set("timestamp",new Date());
			log.set("content","this customer_sn has not found in product list");
			log.set("type",Config.LOG_TYPE_ERROR);
			
			log.save();
		}
		return false;
		
	}

	private boolean saveProduct(Row row) 
	{
		ProductModel product = new ProductModel();
		
		product.set("org", row.getCell(0));
		product.set("customer_name",row.getCell(1));
		product.set("pn",row.getCell(2));
		product.set("customer_pn",row.getCell(3));
		product.set("rev",row.getCell(4));
		product.set("team",row.getCell(5));
		product.set("wo",row.getCell(6));
		product.set("sn",row.getCell(7));
		product.set("customer_sn",row.getCell(8));
		product.set("timestamp",row.getCell(9));
		product.set("status",row.getCell(10));
		product.set("mac_address",row.getCell(11));
		product.set("pn_label",row.getCell(12));
		product.set("rev_label",row.getCell(13));
		
		return product.save();
		
	}
	

}
