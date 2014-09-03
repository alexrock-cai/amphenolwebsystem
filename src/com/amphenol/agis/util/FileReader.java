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
import com.amphenol.agis.model.ShipdataModel;
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

	public void readText(File file) throws Exception 
	{
		// TODO Auto-generated method stub
		String path = file.getParent().substring(EepConfigModel.dao.getWorkPath().length());
		System.out.println("readText Path="+path);
		//判断此文件是程序烧录日志文件还是验证日志文件
		if(path.startsWith("LOG_FILE\\Program"))
		{
			readProgramLog(file,path);
		}
		if(path.startsWith("LOG_FILE\\Verify"))
		{
			readVerifyLog(file,path);
		}
	}
	public void readVerifyLog(File file, String path) throws Exception 
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
					verify.set("verify_times",1);
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
		System.out.println("文件处理完毕："+ file.getAbsolutePath());
	}
	/**
	 * 处理烧录程序log文件
	 * @param file
	 * @param path
	 * @throws Exception
	 */
	public void readProgramLog(File file,String path) throws Exception 
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
					if(ProductModel.dao.getProductIdByCustomerSn(s[2])!=null)
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
					if(ProductModel.dao.getProductIdByCustomerSn(temp[0])!=null)
					{
						//System.out.println(ProductModel.dao.findByCustomerSn(temp[0]));
						p.set("product_id", ProductModel.dao.getProductIdByCustomerSn(temp[0]));
					}
					p.set("program_times", 1);
					
					p.save();
				}
			}
		}
		
		reader.close();
		in.close();
		fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath()+path);
		System.out.println("文件处理完毕："+ file.getAbsolutePath());
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
						if(row==null) continue;
						if(row.getCell(0) == null ) continue;
						if(row.getCell(0).getRichStringCellValue().getString().trim().equals("")) continue;
						
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
						if(row==null) continue;
						if(row.getCell(0) == null) continue;
						if(row.getCell(0).getRichStringCellValue().getString().trim().equals("")) continue;
						
						saveShipData(row);
						//showRow(row);
					}
				}
				//将处理过的文件移到已经处理文件目录中
				fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath());	
				System.out.println("文件处理完毕："+ file.getAbsolutePath());
				
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

	public void readExcel(File file,String filetype) 
	{
		
		try
		{
			in= new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(in);
			
			if(filetype.equals("tagin"))
			{
				
				//循环wb中所有的sheet
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet = wb.getSheetAt(sheetIndex);
					//循环row
					for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						if(row==null) continue;
						if(row.getCell(0) == null ) continue;
						if(row.getCell(0).getRichStringCellValue().getString().trim().equals("")) continue;
						
						saveProduct(row);
						//showRow(row);
					
					}
				}
				//将处理过的文件移到已经处理文件目录中
				
			}
			
			if(filetype.equals("shipdata"))
			{
				
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet=wb.getSheetAt(sheetIndex);
					for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						if(row==null) continue;
						if(row.getCell(0) == null) continue;
						if(row.getCell(0).getRichStringCellValue().getString().trim().equals("")) continue;
						
						saveShipData(row);
						//showRow(row);
					}
				}
				//将处理过的文件移到已经处理文件目录中
				//fileScanner.cutFile(file, EepConfigModel.dao.getHandledPath());	
				System.out.println("文件处理完毕："+ file.getAbsolutePath());
				
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
		ShipdataModel ship=new ShipdataModel();
		if(row.getCell(1)!=null)
			ship.set("customer_name", row.getCell(1).getRichStringCellValue().getString());
		if(row.getCell(3)!=null)
			ship.set("pn", row.getCell(3).getRichStringCellValue().getString());
		if(row.getCell(9)!=null)
			ship.set("wo", row.getCell(9).getRichStringCellValue().getString());
		if(custSn!=null)
			ship.set("customer_sn",custSn);
		boolean flag=ship.save();
		ProductModel p = ProductModel.dao.findByCustomerSn(custSn) ;

		if(p==null)
		{
			EepLogModel log = new EepLogModel();
			log.set("customer_sn", custSn);
			log.set("timestamp",new Date());
			log.set("content","this customer_sn has not found in product list");
			log.set("type",Config.LOG_TYPE_ERROR);
			
			log.save();
		}
		return flag;
		
	}

	private boolean saveProduct(Row row) 
	{
		ProductModel product = new ProductModel();
		if(row.getCell(0)!=null)
			product.set("org", row.getCell(0).getRichStringCellValue().getString());
		if(row.getCell(1)!=null)
			product.set("customer_name",row.getCell(1).getRichStringCellValue().getString());
		if(row.getCell(2)!=null)
			product.set("pn",row.getCell(2).getRichStringCellValue().getString());
		if(row.getCell(3)!=null)
			product.set("customer_pn",row.getCell(3).getRichStringCellValue().getString());
		if(row.getCell(4)!=null)
			product.set("rev",row.getCell(4).getRichStringCellValue().getString());
		if(row.getCell(5)!=null)
			product.set("team",row.getCell(5).getRichStringCellValue().getString());
		if(row.getCell(6)!=null)
			product.set("wo",row.getCell(6).getRichStringCellValue().getString());
		if(row.getCell(7)!=null)
			product.set("sn",row.getCell(7).getRichStringCellValue().getString());
		if(row.getCell(8)!=null)
			product.set("customer_sn",row.getCell(8).getRichStringCellValue().getString());
		if(row.getCell(9)!=null)
			product.set("timestamp",row.getCell(9).getRichStringCellValue().getString());
		if(row.getCell(10)!=null)
			product.set("status",row.getCell(10).getRichStringCellValue().getString());
		if(row.getCell(11)!=null)
			product.set("mac_address",row.getCell(11).getRichStringCellValue().getString());
		if(row.getCell(12)!=null)
			product.set("pn_label",row.getCell(12).getRichStringCellValue().getString());
		if(row.getCell(13)!=null)
			product.set("rev_label",row.getCell(13).getRichStringCellValue().getString());
		
		return product.save();
		
	}
	
	public void checkStatus()
	{
		List<ProductModel> products=ProductModel.dao.findAll();
		List<ShipdataModel> shipdatas=ShipdataModel.dao.findAll();
		for(ProductModel product : products)
		{
			String custSn=product.getStr("customer_sn");
			ProgramModel program = ProgramModel.dao.findByCustomerSn(custSn);
			VerifyModel verify = VerifyModel.dao.findByCustomerSn(custSn);
//			ShipdataModel shipdata=ShipdataModel.dao.findByCustomerSn(custSn);
			//System.out.println(product.getStr("customer_sn"));
			//System.out.println(shipdata.getStr("customer_sn"));
			if(program!=null)
			{
				if(program.getBoolean("status"))
				{
					product.set("hasprogram", true);
					product.set("program_id", program.getLong("id"));
					
				}
				else if(program.getBoolean("left_status")&&program.getBoolean("right_status"))
				{
					product.set("hasprogram", true);
					product.set("program_id", program.getLong("id"));
				}
				else
				{
					product.set("hasprogram", false);
					product.set("program_id", program.getLong("id"));
				}
			}
			if(verify !=null)
			{
				if(verify.getBoolean("status"))
				{
					product.set("hasverify",true);
					product.set("verify_id",verify.getLong("id"));
				}
				else
				{
					product.set("hasverify",false);
					product.set("verify_id",verify.getLong("id"));
				}
			}
//			if(shipdata!=null)
//			{
//				product.set("onship", true);
//				//System.out.println(shipdata.getStr("pn"));
//			}
			product.update();
		}
		
		for(ShipdataModel shipdata : shipdatas)
		{
			String custSn= shipdata.getStr("customer_sn");
			ProgramModel program = ProgramModel.dao.findByCustomerSn(custSn);
			VerifyModel verify = VerifyModel.dao.findByCustomerSn(custSn);
			ProductModel p= ProductModel.dao.findByCustomerSn(custSn);
			//System.out.println(custSn);
			//System.out.println(p);
			if(program !=null)
			{
				if(program.getBoolean("status"))
				{
					shipdata.set("hasprogram", true);
					shipdata.set("program_id", program.getLong("id"));
				}
				else if(program.getBoolean("left_status")&program.getBoolean("right_status"))
				{
					shipdata.set("hasprogram", true);
					shipdata.set("program_id", program.getLong("id"));
				}
			}
			if(verify !=null)
			{
				if(verify.getBoolean("status"))
				{
					shipdata.set("hasverify", true);
					shipdata.set("verify_id",verify.getLong("id"));
				}
			}
			if(p!=null)
			{
				p.set("onship",true);
				p.update();
				shipdata.set("on_dts", true);
			}
			
			shipdata.update();
		}

	}
}
