package com.amphenol.agis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Properties;

import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.core.Controller;

public class TestController extends Controller 
{
	public void index() throws URISyntaxException
	{
		Properties properties=new Properties();
		InputStream is=TestController.class.getClassLoader().getResourceAsStream("job.properties");
		File f= new File(Class.class.getClass().getResource("/").toURI().getPath()+"job.properties");
		
		System.out.println("file path:"+Class.class.getClass().getResource("/").toURI().getPath());
		FileOutputStream fos=null;
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------------load properties---------------");
		System.out.println(properties.toString());
		System.out.println("-------------------------------------------");
		Enumeration<Object> enums=properties.keys();
		while(enums.hasMoreElements())
		{
			String key=enums.nextElement()+"";
			System.out.println("key:"+key);
			if(key.endsWith("job"))
			{
				String k=key.substring(0, key.lastIndexOf("job")) + "enable";
				System.out.println(k);
				properties.setProperty(k, "true");
				
				
			}
		}
		try{
			fos=new FileOutputStream(f);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			properties.store(fos, "#");	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try{
			fos.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//renderText(properties.keys().toString());
		renderText(properties.toString());
	}
	
	public void pluginTest() {
		try {
			QuartzPlugin quartz=(QuartzPlugin) Class.forName("com.jfinal.ext.plugin.quartz.QuartzPlugin").newInstance();
			if(quartz.start())
				System.out.println("任务加载成功");
			else
				System.out.println("任务停止失败");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderNull();
	}
}
