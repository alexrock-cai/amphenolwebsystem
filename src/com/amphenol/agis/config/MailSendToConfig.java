package com.amphenol.agis.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.jfinal.kit.PathKit;


public class MailSendToConfig {

	private Properties table = new Properties();
	
	public MailSendToConfig(String file) {
		try {
			String fullFile;	// String fullFile = PathUtil.getWebRootPath() + file;
			if (file.startsWith(File.separator))
				fullFile = PathKit.getWebRootPath() + File.separator + "WEB-INF" + file;
			else
				fullFile = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + file;
			
			table.load(new FileInputStream(fullFile));
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
