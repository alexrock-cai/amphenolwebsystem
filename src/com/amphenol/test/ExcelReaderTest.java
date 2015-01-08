package com.amphenol.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelReaderTest 
{
    /**
     * POI ：简单读取excel文件
     * @param inputStream
     * @throws IOException
     */
	public static void read(InputStream inputStream) throws IOException
	{
		//初始化excel
		HSSFWorkbook wb= new HSSFWorkbook(inputStream);
		//获取第一个sheet表
		HSSFSheet sheet= wb.getSheetAt(0);
		//获取第一行
		HSSFRow row0= sheet.getRow(0);
		//获取第一行第一个单元格
		HSSFCell cell = row0.getCell(0);
		System.out.println(cell.getRichStringCellValue().getString());
	}
	
	public static void readAll(InputStream inputStream) throws IOException
	{
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		//循环wb中所有的sheet
		for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
		{
			HSSFSheet sheet=wb.getSheetAt(sheetIndex);
			System.out.println("Sheet序号："+sheetIndex+",sheet名称:"+wb.getSheetName(sheetIndex));
			//循环sheet中所有的行
			for(int rowIndex=0;rowIndex<=sheet.getLastRowNum();rowIndex++)
			{
				HSSFRow row = sheet.getRow(rowIndex);
				if(row == null)
				{
					continue;
				}
				//循环行中所有的表格
				System.out.print("第"+rowIndex+" 行内容为：");
				for(int cellnum=0;cellnum<row.getLastCellNum();cellnum++)
				{
					HSSFCell cell = row.getCell(cellnum);
					System.out.print("      "+cell.getRichStringCellValue().getString()+"        ");
				}
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
	}
	
	public static void readxlsx(InputStream inputStream) throws IOException, InvalidFormatException
	{
		Workbook wb = WorkbookFactory.create(inputStream);
		//循环wb中所有的sheet
		for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
		{
			Sheet sheet=wb.getSheetAt(sheetIndex);
			System.out.println("Sheet序号："+sheetIndex+",sheet名称:"+wb.getSheetName(sheetIndex));
			//循环sheet中所有的行
			for(int rowIndex=0;rowIndex<=sheet.getLastRowNum();rowIndex++)
			{
				Row row = sheet.getRow(rowIndex);
				if(row == null)
				{
					continue;
				}
				//循环行中所有的表格
				System.out.print("第"+rowIndex+" 行内容为：");
				for(int cellnum=0;cellnum<row.getLastCellNum();cellnum++)
				{
					Cell cell = row.getCell(cellnum);
					System.out.print("      "+cell.getRichStringCellValue().getString()+"        ");
				}
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
	}

	public static void main(String[] args)
	{
		InputStream inputStream = null;
		try
		{
			//读取文件流
			String str="5630889";
			inputStream=new FileInputStream(new File("/Users/rocky/Desktop/kvb118/"+str+".xlsx"));
			readxlsx(inputStream);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("文件未找到");
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
			System.out.println("文件格式不支持");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("IO exception");
		}
		finally
		{
			try{
				if(inputStream !=null)
				{
					inputStream.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.out.println("file close error");
			}
		}
	}
}
