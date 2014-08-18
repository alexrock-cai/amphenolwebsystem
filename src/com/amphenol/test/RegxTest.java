package com.amphenol.test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amphenol.agis.util.FileScanner;

public class RegxTest 
{
	public static void main(String[] args)
	{
		FileScanner scanner=new FileScanner();
		String path="/Users/rocky/Workspaces/MyEclipse 10/amphenolapps/WebRoot/static/WI";
		List<File> list=scanner.getPdfFileList(new File(path));
		for(File file:list)
		{
			String filename=file.getAbsolutePath().substring(path.length()+1);
			//String regex="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*[\\-|\\w])/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[\\-_]*(\\w+)\\.pdf";
			//String regex="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*[\\-|\\w])\\.pdf";
			String regex="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})[\\-_]([a-zA-Z]+\\s*[a-zA-Z]*)[\\-_]([rR][Ee][vV]\\s*[\\-|\\w])\\.pdf";
			Pattern p=Pattern.compile(regex);
			Matcher m=p.matcher(file.getAbsolutePath());
			//System.out.println("m.find():"+m.find());
			if(m.find())
			{
				//
				System.out.println(m.groupCount());
				for(int i=0;i<=m.groupCount();i++)
					System.out.println("i="+i+"  "+m.group(i).toUpperCase());

				System.out.println("-----------------------------------");
			}
		}
		String str="Brocade/WI-KG76400049 RevB/WI-KG76400049_ATB1.pdf";
		String regex="[a-zA-Z]+/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[rR][Ee][vV]\\s*[\\-|\\w]\\.pdf";
		String regex2="[a-zA-Z]+/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[rR][Ee][vV]\\s*[\\-|\\w]/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*\\-*\\w+\\.pdf";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(str);
		boolean result=m.matches();
		System.out.println(result);
	}
}
