package com.amphenol.agis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileUtil 
{
	 static int n=0;
	 static Map<String,String> map=new HashMap<String, String>();
	 
	
	public static void getFiles(File file) throws RuntimeException
	{
		
		
			//判断文件是否是文件，如果是文件，获取路径，并计数
			if(file.isFile())
			{
				n++;
				map.put(file.getAbsolutePath(), file.getParent());
			}
			else
			{
				//如果是文件夹，声明一个数组放文件夹和他的子文件
				File[] f=file.listFiles();
				//遍历文件夹下的文件，并获取路径
				for(File file2:f)
				{
					getFiles(file2);
				}
			}
		
	}

	/** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public static boolean copyFile(String oldPath, String newPath) { 
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
	
   public static boolean copyFile(File oldfile , String newPath)
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
   
   public static boolean copyFile(File oldfile,String newPath,String newName)
   {
	   boolean flag =true;
       try {
    	   
    	   (new File(newPath)).mkdirs();
          // int bytesum = 0; 
           int byteread = 0; 
           
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldfile); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath + "/" + 
                       rename(oldfile.getName(), newName)); 
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
   
   public static String getFileType(File file)
   {
	   String fileName=file.getName();
	   String fileType=fileName.substring(fileName.lastIndexOf("."), fileName.length());
	   return fileType;
   }
   
   public static String rename(String fileName,String newName)
   {
	   return newName+fileName.substring(fileName.lastIndexOf("."), fileName.length());
   }
	public static int getN() {
		return n;
	}

	public static void setN(int n) {
		FileUtil.n = n;
	}

	public static Map<String, String> getMap() {
		return map;
	}

	public static void setMap(Map<String, String> map) {
		FileUtil.map = map;
	}
	
}
