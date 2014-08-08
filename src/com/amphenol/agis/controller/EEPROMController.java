package com.amphenol.agis.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.amphenol.agis.model.EepConfigModel;
import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.util.EmailService;
import com.amphenol.agis.util.FileReader;
import com.amphenol.agis.util.FileScanner;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class EEPROMController extends Controller 
{
	public void index()
	{
		view();
	}
	
	public void openConfigForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/eeprom/configcreate.jsp");
		}
		else
		{
			setAttr("config", EepConfigModel.dao.findById(getPara("id")));
			render("/dwzpage/eeprom/configedit.jsp");
		}
	}
	
	public void configCreate()
	{
		EepConfigModel config=new EepConfigModel();
		config.set("filepath", getPara("filepath"));
		config.set("function",getPara("function"));
		
		if(config.save())
		{
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","eeprom_list");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
			
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
		if(EepConfigModel.dao.deleteById(getParaToLong("id")))
		{
			setAttr("statusCode", "200");
			setAttr("message","删除成功");
			setAttr("navTabId","eeprom_list");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
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
	
	public void getConfig()
	{
		List<EepConfigModel> list=new ArrayList<EepConfigModel>();
		Page<EepConfigModel> pages=EepConfigModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		list=pages.getList();
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("configlist",list);
	}
	public void view()
	{
		getConfig();
		render("/dwzpage/eeprom/eepromcheck.jsp");
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
		List<ShipdataModel> notindts= ShipdataModel.dao.getNotOnDTSList();
		if(new EmailService().sendNoEepromList(noEEPROM,notindts))
		{
			System.out.println("邮件发送成功");
		}
		else
		{
			System.out.println("邮件发送失败");
		}
		setAttr("noEepromList",noEEPROM);
		setAttr("total",noEEPROM.size());
		render("/dwzpage/eeprom/no_eeprom_list.jsp");

	}
}
