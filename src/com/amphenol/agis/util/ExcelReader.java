package com.amphenol.agis.util;



import java.io.InputStream;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;




import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;


public class ExcelReader 
{
	public  void saveExcel(InputStream in) throws Exception 
	{
		try
		{
			Workbook wb = WorkbookFactory.create(in);
			//循环wb中所有的sheet
			for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
			{
				Sheet sheet=wb.getSheetAt(sheetIndex);
				//循环sheet中所有的行
				for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
				{
					Row row = sheet.getRow(rowIndex);
					if(row == null)
					{
						continue;
					}
					Record pninfo=new Record();
					pninfo.set("customerName", row.getCell(1).getRichStringCellValue().getString());
					pninfo.set("pn",row.getCell(2).getRichStringCellValue().getString());
					pninfo.set("customerPN", row.getCell(3).getRichStringCellValue().getString());
					pninfo.set("rev", row.getCell(4).getRichStringCellValue().getString());
					pninfo.set("team",row.getCell(5).getRichStringCellValue().getString());
					pninfo.set("wo", row.getCell(6).getRichStringCellValue().getString());
					pninfo.set("sn",row.getCell(7).getRichStringCellValue().getString());
					pninfo.set("customerSN",row.getCell(8).getRichStringCellValue().getString());
					pninfo.set("timeStamp", row.getCell(9).getRichStringCellValue().getString());
					System.out.println("dddddd");
					System.out.println(Db.save("PnInfoFromDTS", pninfo));
					System.out.println("hhhhh");
					//Db.save("PnInfoFromDTS", pninfo);
					
				}
				
			}
		}
		catch(Exception e)
		{
			throw e;
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
				throw e;
			}
		}
		
		
	}
	
	public  void saveShipmentExcel(InputStream in) throws Exception
	{
		try
		{
			Workbook wb=WorkbookFactory.create(in);
			//循环wb中的所有表
			for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
			{
				Sheet sheet=wb.getSheetAt(sheetIndex);
				//循环所有的行
				for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
				{
					Row row=sheet.getRow(rowIndex);
					if(row==null)
					{
						continue;
					}
					Record record=new Record();
					record.set("org", row.getCell(0).getRichStringCellValue().getString());
					record.set("customer", row.getCell(1).getRichStringCellValue().getString());
					record.set("pl_id", row.getCell(2).getRichStringCellValue().getString());
					record.set("pn", row.getCell(3).getRichStringCellValue().getString());
					record.set("po_num", row.getCell(4).getRichStringCellValue().getString());
					record.set("fiscal_wk", row.getCell(5).getRichStringCellValue().getString());
					record.set("late_reason", row.getCell(6).getRichStringCellValue().getString());
					record.set("decode", row.getCell(7).getRichStringCellValue().getString());
					record.set("ref_id", row.getCell(8).getRichStringCellValue().getString());
					record.set("sn", row.getCell(9).getRichStringCellValue().getString());
					record.set("cust_sn", row.getCell(10).getRichStringCellValue().getString());
					record.set("name", row.getCell(11).getRichStringCellValue().getString());
					record.set("wo", row.getCell(12).getRichStringCellValue().getString());
					record.set("timestamp_entry", row.getCell(13).getRichStringCellValue().getString());
					
					Db.save("PnInfoFromShipment", record);
				}
			}
		
		}
		catch(Exception e)
		{
			throw e;
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
				throw e;
			}
		}
		
	}

}
