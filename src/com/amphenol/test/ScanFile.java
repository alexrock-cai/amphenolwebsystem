package com.amphenol.test;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amphenol.agis.util.FileScanner;
import com.amphenol.agis.util.FileUtil;


public class ScanFile 
{
	//初始化，用于计数
	static int n=0;
	
	public static void get(File file)
	{
		
		try{
			//判断文件是否是文件，如果是文件，获取路径，并计数
			if(file.isFile())
			{
				n++;
				System.out.println(file.getParent()+" -- "+file.getName());
				
			}
			else
			{
				//如果是文件夹，声明一个数组放文件夹和他的子文件
				File[] f=file.listFiles();
				//遍历文件夹下的文件，并获取路径
				for(File file2:f)
				{
					get(file2);
				}
			}
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
		}
		

	}
	public static void main(String[] args) throws MalformedURLException 
	{
		//输入文件路径
		File file = new File("/Users/rocky/Documents/WI");
		//System.out.println("file list"+file.listFiles()[0].isHidden()+"  "+file.listFiles()[2].getName());
		System.out.println("file list---->"+file.list().length);
		FileScanner fileScanner=new FileScanner();
		List<File> files=fileScanner.getFileList(file);
		for(int i=0;i<files.size();i++)
		{
			
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(f.format(new Date(files.get(i).lastModified())));
			System.out.println(files.get(i).getAbsolutePath());
		}
//		FileUtil.getFiles(file);
//		Map<String, String> map=FileUtil.getMap();
//		Iterator<String> it=map.keySet().iterator();
//		while(it.hasNext())
//		{
//			System.out.println(map.get(it.next()));
//		}
		//get(file);
		System.out.println("文件个数为："+n);
	}
}
