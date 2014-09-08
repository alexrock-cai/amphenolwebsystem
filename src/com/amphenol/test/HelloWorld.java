package com.amphenol.test;

public class HelloWorld
{
   public static void main(String[] argv){
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
   }
}
