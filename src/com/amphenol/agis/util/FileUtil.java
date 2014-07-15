package com.amphenol.agis.util;

import java.io.File;
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
