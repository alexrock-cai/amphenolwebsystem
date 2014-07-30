package com.amphenol.agis.util;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.amphenol.agis.model.UserModel;

public class BatchCreateUser 
{
	public static void readExcel(File file) throws InvalidFormatException, IOException 
	{
		InputStream in = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(in);
		//循环wb中所有的sheet
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet=wb.getSheetAt(sheetIndex);
					System.out.println("总共"+sheet.getLastRowNum()+"行");
					System.out.println("Sheet序号："+sheetIndex+",sheet名称:"+wb.getSheetName(sheetIndex));
					//循环sheet中所有的行
					for(int rowIndex=0;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						if(row.getCell(0)==null)
						{
							
							System.out.println("row==null"+row);
							break;
						}
						//循环行中所有的表格
						System.out.print("第"+rowIndex+" 行内容为：");
						for(int cellnum=1;cellnum<row.getLastCellNum();cellnum++)
						{
							Cell cell = row.getCell(cellnum);
							if(cell ==null) continue;
							System.out.print("      "+cell.getRichStringCellValue().getString()+"        ");
						}
						System.out.println();
					}
					System.out.println("------------------------------------------------------------------------------------------------------");
				}
	}
	
	public static void createUser(File file) throws IOException, InvalidFormatException
	{
		InputStream in = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(in);
		//循环wb中所有的sheet
				for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
				{
					Sheet sheet=wb.getSheetAt(sheetIndex);
					System.out.println("总共"+sheet.getLastRowNum()+"行");
					System.out.println("Sheet序号："+sheetIndex+",sheet名称:"+wb.getSheetName(sheetIndex));
					//循环sheet中所有的行
					for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++)
					{
						Row row = sheet.getRow(rowIndex);
						UserModel user=new UserModel();
						if(row==null) break;
						if(row.getCell(0)==null)break;
							user.set("username", row.getCell(1).getRichStringCellValue().getString());
							user.set("name", row.getCell(2).getRichStringCellValue().getString());
							user.set("role_ids", 2);
							user.set("password",123456789);
							user.set("img", "/static/faceimg/"+row.getCell(1).getRichStringCellValue().getString()+".png");
							user.save();
					}
					
				}
				if(in!=null)
					in.close();
	}
	
	public static void main(String[] args)
	{
		File file=new File("/Users/rocky/Desktop/userlist.xls");
		try {
			BatchCreateUser.readExcel(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
