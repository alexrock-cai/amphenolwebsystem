package com.amphenol.agis.controller;

import java.io.File;
import java.util.List;

import com.amphenol.agis.model.EepConfigModel;
import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.util.EmailService;
import com.amphenol.agis.util.FileReader;
import com.amphenol.agis.util.FileScanner;
import com.jfinal.core.Controller;

public class EEPROMController extends Controller 
{
	public void index()
	{
		render("/pages/apps/eprom/eeprom_config.jsp");
	}
	
	public void configCreate()
	{
		EepConfigModel config=new EepConfigModel();
		config.set("filepath", getPara("filepath"));
		config.set("function",getPara("function"));
		
		if(config.save())
		{
			setAttr("status", "保存成功");
		}
		else
		{
			setAttr("status","保存失败");
		}
		renderJson();
	}
	
	public void configUpdate()
	{
		EepConfigModel config=EepConfigModel.dao.findById(getPara("configid"));
		config.set("filepath", getPara("filepath"));
		config.set("function",getPara("function"));
		if(config.update())
		{
			setAttr("status", "保存成功");
		}
		else
		{
			setAttr("status","保存失败");
		}
		renderJson();
	}
	
	public void configDelete()
	{
		if(EepConfigModel.dao.deleteById(getParaToLong("configid")))
		{
			setAttr("status", "保存成功");
		}
		else
		{
			setAttr("status","保存失败");
		}
		renderJson();
	}
	
	public void configView()
	{
		List<EepConfigModel> list = EepConfigModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	
	public void startScanFile()
	{
		String workPath=EepConfigModel.dao.getWorkPath();
		FileReader reader=new FileReader();
		FileScanner scanner= new FileScanner();
		List<File> files=scanner.getFileList(new File(workPath));
		try
		{
			reader.readFiles(files);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.checkStatus();
		renderJson();
	}
	
	public void checkStatus()
	{
		startScanFile();
		List<ShipdataModel> noEEPROM= ShipdataModel.dao.getNoEEPROMList();
		if(new EmailService().sendNoEepromList(noEEPROM))
		{
			System.out.println("邮件发送成功");
		}
		else
		{
			System.out.println("邮件发送失败");
		}
		setAttr("noEepromList",noEEPROM);
		setAttr("total",noEEPROM.size());
		render("/pages/apps/eprom/no_eeprom_list.jsp");

	}
}
