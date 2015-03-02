package com.amphenol.agis.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class MailSendToConfig {

	private Properties table = new Properties();
	
	public MailSendToConfig(String file) {
		try {
			table.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MailSendToConfig.class.getClassLoader();
	}
	
	public int getInt(String key){
		return Integer.parseInt(table.getProperty(key));
	}
	
	public String getString(String key){
		return table.getProperty(key);
	}
	
	public double getDouble(String key){
		return Double.parseDouble(key);
	}
	
	
}
