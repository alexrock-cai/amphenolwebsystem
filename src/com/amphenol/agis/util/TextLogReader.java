package com.amphenol.agis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import com.amphenol.agis.pojo.PNInfo;

public class TextLogReader 
{
	public List<PNInfo> typeAProgramLogReader(File file) throws IOException
	{
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String str=null;
		List<PNInfo> pninfos=new ArrayList<PNInfo>();
		while((str=reader.readLine())!=null)
		{
			//读入一行，先根据制表符将字符串分位三段。
			String[] temp=str.split("\t");
			if(temp.length==3)
			{
				String[] sn=temp[2].split("\\s");
				PNInfo  info= new PNInfo();
				info.setCustomerSN(sn[0]);
				info.setTimeStamp(temp[0]);
				if(sn[1].equalsIgnoreCase("Left"))
				{
					info.setLeftProgStatus(sn[2]);
				}
				if(sn[1].equalsIgnoreCase("Right"))
				{
					info.setRightProgStatus(sn[2]);
				}
				sn=temp[1].split("\\s");
				info.setCustomerPN(sn[0]+sn[1]+sn[2]);
				info.setRev(sn[3]);
				pninfos.add(info);
			}
			else
			{
				continue;
			}
		}
		return pninfos;
	}
	
	public PNInfo typeAVerifyLogReader(File file) throws IOException
	{
		PNInfo pninfo=new PNInfo();
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String str=null;
	    String[] temp=file.getName().split("_");
	    pninfo.setCustomerSN(temp[0]);
	    if((str=reader.readLine())!=null)
	    {
	    	temp=str.split("\t");
	    	pninfo.setVerStatus(temp[1]);
	    }
		return pninfo;
	}
	
	public void saveLogFile(File file,String type)
	{
		
	}
	
	public static void main(String[] agrs) throws IOException
	{
		TextLogReader tlr=new TextLogReader();
		FileScanner fileScanner=new FileScanner();
		List<File> files=fileScanner.getFileList(new File("/Users/rocky/Desktop/Program"));
		List<PNInfo> ls=tlr.typeAProgramLogReader(files.get(0));
		System.out.println("记录数量："+ls.size());
		for(int i=0;i<ls.size();i++)
		{
			System.out.println(ls.get(i).toString());
		}
		PNInfo info=tlr.typeAVerifyLogReader(new File("/Users/rocky/Desktop/kvb118/X912286104_091413_205952.txt"));
		System.out.println("VerityLog: "+info.toString());
	}
}
