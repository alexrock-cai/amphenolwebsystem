package com.amphenol.agis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 能够根据给定的路径扫描该路径下所有的文件，并返回List。
 * 提供文件夹拷贝功能
 * 提供单个文件复制功能
 * @author rocky
 *
 */
public class FileScanner 
{
	private List<File> files=new ArrayList<File>();
	
	public FileScanner(){}
	
	private void scanDir(File file)
	{
		
		//判断文件是否是文件，如果是文件，获取路径，并计数
		if(file.isFile())
		{
			if(!file.isHidden())
				files.add(file);
		}
		else
		{
			//如果是文件夹，声明一个数组放文件夹和他的子文件
			File[] f=file.listFiles();
			//遍历文件夹下的文件，并获取路径
			for(File file2:f)
			{
				scanDir(file2);
			}
		}
	}
	
	public List<File> getFileList(File file)
	{
		scanDir(file);
		return files;
	}
	
	public boolean copyDir(String file1,String file2) 
	{
		
		File in=new File(file1);
		File out=new File(file2);
		boolean flag=false;
		if(!in.exists())
		{
			System.out.println(in.getAbsolutePath()+"源文件路径错误！！！");
			return flag;
		}
		else 
		{
			System.out.println("源文件路径"+in.getAbsolutePath());
			System.out.println("目标路径"+out.getAbsolutePath());
			
		}
		if(!out.exists()) 
			out.mkdirs();
		File[] file=in.listFiles();
		FileInputStream fin=null;
		FileOutputStream fout=null;
		for(int i=0;i<file.length;i++)
		{
			if(file[i].isFile())
			{
				try 
				{
					fin=new FileInputStream(file[i]);
				} 
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return flag;
				}
				System.out.println("in.name="+file[i].getName());
				try 
				{
					fout=new FileOutputStream(new File(file2+"/"+file[i].getName()));
				} 
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return flag;
				}
				System.out.println(file2);
				int c;
				byte[] b=new byte[1024*5];
				try 
				{
					while((c=fin.read(b))!=-1)
					{
					
						fout.write(b, 0, c);
						System.out.println("复制文件中！");
					}

					fin.close();
					fout.flush();
					fout.close();

				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return flag;
				}

//			return true;
			}
			else copyDir(file1+"/"+file[i].getName(),file2+"/"+file[i].getName());
		}
		
		return flag=true;

	}
	/** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public boolean copyFile(String oldPath, String newPath) { 
	   boolean flag =true;
       try {
    	   
    	   (new File(newPath)).mkdirs();
          // int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath + "/" + 
                       (oldfile.getName()).toString()); 
               byte[] buffer = new byte[1444]; 
              // int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   //bytesum += byteread; //字节数 文件大小 
                   //System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 
           return flag=false;
       } 
       return flag;
   } 
   
   public boolean copyFile(File oldfile , String newPath)
   {
	   boolean flag =true;
       try {
    	   
    	   (new File(newPath)).mkdirs();
          // int bytesum = 0; 
           int byteread = 0; 
           
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldfile); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath + "/" + 
                       (oldfile.getName()).toString()); 
               byte[] buffer = new byte[1444]; 
              // int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   //bytesum += byteread; //字节数 文件大小 
                  // System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 
           return flag=false;
       } 
       return flag;
   }
	/** 
     * 复制整个文件夹内容 
     * @param oldPath String 原文件路径 如：c:/fqf 
     * @param newPath String 复制后路径 如：f:/fqf/ff 
     * @return boolean 
     */ 
   public boolean copyFolder(String oldPath, String newPath) { 
	   boolean flag=true;
       try { 
           (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
           File a=new File(oldPath); 
           String[] file=a.list(); 
           File temp=null; 
           for (int i = 0; i < file.length; i++) { 
               if(oldPath.endsWith(File.separator)){ 
                   temp=new File(oldPath+file[i]); 
               } 
               else{ 
                   temp=new File(oldPath+File.separator+file[i]); 
               } 

               if(temp.isFile()){ 
                   FileInputStream input = new FileInputStream(temp); 
                   FileOutputStream output = new FileOutputStream(newPath + "/" + 
                           (temp.getName()).toString()); 
                   byte[] b = new byte[1024 * 5]; 
                   int len; 
                   while ( (len = input.read(b)) != -1) { 
                       output.write(b, 0, len); 
                   } 
                   output.flush(); 
                   output.close(); 
                   input.close(); 
               } 
               if(temp.isDirectory()){//如果是子文件夹 
                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
               } 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制整个文件夹内容操作出错"); 
           e.printStackTrace(); 
           return flag=false;
       } 
       return flag;
   }
	
   public boolean cutFile(String oldPath, String newPath)
   {
	   File file=new File(oldPath);
	   if(file.exists())
	   {
		   if(copyFile(file, newPath))
			   file.delete();
		   return true;
	   }
		   return false;
   }
   
   public boolean cutFile(File file,String newPath)
   {
	   if(file.exists())
	   {
		   if(copyFile(file,newPath))
			    return file.delete();
	   }
	   return false;
   }
   
	public static void main(String[] args) 
	{
		FileScanner s=new FileScanner();
		System.out.println(s.copyFolder("/Users/rocky/Desktop/EPROM/", "/Users/rocky/Desktop/ECO_SYSTEM"));
		System.out.println(s.copyFile("/Users/rocky/Desktop/EPROM/dts/5630889.xlsx", "/Users/rocky/Desktop/ECO_SYSTEM/"));
	}

		
}
