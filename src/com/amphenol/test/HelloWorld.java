package com.amphenol.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

public class HelloWorld
{
   public static void main(String[] argv) throws ParseException{
     try{
       System.out.println("中文");//1
       System.out.println("中文".getBytes());//2
       System.out.println("中文".getBytes("GB2312"));//3
       System.out.println("中文".getBytes("ISO8859_1"));//4
       System.out.println(new String("中文".getBytes()));//5
       System.out.println(new String("中文".getBytes(),"GB2312"));//6
       System.out.println(new String("中文".getBytes(),"ISO8859_1"));//7
       System.out.println(new String("中文".getBytes("GB2312")));//8
       System.out.println(new String("中文".getBytes("GB2312"),"GB2312"));//9
       System.out.println(new String("中文".getBytes("GBK"),"ISO8859_1"));//10
       System.out.println(new String("中文".getBytes("ISO8859_1")));//11
       System.out.println(new String("中文".getBytes("ISO8859_1"),"utf-8"));//12
       System.out.println(new String("中文".getBytes("ISO8859_1"),"ISO8859_1"));//13
     }
     catch(Exception e){
       e.printStackTrace();
     }
     String[] keys={"id","equipmentID","model","equipmentName",
 			"owner","ownerEmail","supervisor","supervisorEmail","isMonthlyPM","isQuerterlyPM","isYearlyPM"}; 
     String words ="helloword";
     StringBuilder sql=new StringBuilder("where 1=2 ");
		for(int i=0;i<keys.length;i++){
			sql.append(" or ").append(keys[i]).append(" like '%").append(words).append("%' ");
		}
		System.out.println("sql-----:"+sql.toString());
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
		String s="2014-11-11 12:53:54";
		String s2="2014-11-7 11:00:10";
		Calendar c=Calendar.getInstance();
		Date d1=sdf.parse(s);
		
		Date d2=sdf.parse(s2);
		
		System.out.println("compareTo:"+d1.compareTo(d2));
		System.out.println(sdf.format(d1));
		System.out.println(c.getTime());
		System.out.println(sdf.format(c.getTime()));
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.MONTH)+1);
		System.out.println(c.get(Calendar.DATE));
		c.add(Calendar.DAY_OF_MONTH, 5);
		
		System.out.println(sdf.format(c.getTime()));
		c.setTime(d2);
		System.out.println(c.get(Calendar.DATE));
		List<String> test=new ArrayList<String>();
		Map<String, List<String>> tMap=new HashMap<String, List<String>>();
		test.add("123");
		tMap.put("a", test);
		tMap.get("a").add("444");
		System.out.println(tMap.get("a").toString());
	}
}
