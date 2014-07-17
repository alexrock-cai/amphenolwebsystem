package com.amphenol.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amphenol.agis.util.FileScanner;

public class TxtReaderTest 
{
	public static void read(InputStream inputStream) throws IOException
	{
		BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));
		String str=null;

		Map<String,String> sn=new HashMap<String, String>();
		Map<String,String> staus=new HashMap<String, String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		int index=0;
		try
		{
			while((str=reader.readLine()) !=null)
			{

				String[] temp=str.split("\\s");
				if(temp.length==1)
					continue;
				for(int x=0;x<temp.length;x++)
				{
					if(x==temp.length-3)
					{
						sn.put(""+index, temp[x]);
					}
					if(x==temp.length-1)
					{
						staus.put(""+index, temp[x-1]+" "+temp[x]);
					}
				}
				index++;
				
			}
			list.add(sn);
			list.add(staus);
			Iterator<String> it=list.get(0).keySet().iterator();
			while(it.hasNext())
			{
				String key=it.next();
				System.out.println("sn="+sn.get(key)+"  status="+staus.get(key));
			}
			reader.close();

			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args)
//	{
//		InputStream in=null;
//		try
//		{
//			in=new FileInputStream(new File("/Users/rocky/Desktop/kvb118/13-09-14.txt"));
//			read(in);
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try{
//				if(in !=null)
//				{
//					in.close();
//				}
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//				System.out.println("file close error");
//			}
//		}
//	}
	
	public static void main(String[] args)
	{
		File f = new File("/Users/rocky/Desktop/EEPROM SYS/LOG_FILE/Verify/118/Verify log/X912332413_062114_100328.txt");
		System.out.println(f.getParent().substring("/Users/rocky/Desktop/EEPROM SYS".length()));
		System.out.println(File.separator);
		FileScanner s = new FileScanner();
		List<File> files = s.getFileList(f);
		for(File file : files)
		{
			System.out.println(file.getAbsolutePath());
		}
	}
}
