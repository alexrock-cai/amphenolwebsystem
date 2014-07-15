package com.amphenol.agis.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.UrlConfig;
import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.util.FileScanner;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
@RequiresAuthentication
public class DCCFileController extends Controller 
{
	public void index()
	{
		renderText("Welcome to DCC !");
	}
	
	public void initWI()
	{

		FileScanner scanner = new FileScanner();
		String path=getRequest().getServletContext().getRealPath("/");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<File> wilist=scanner.getFileList(new File(path+UrlConfig.WI_PATH));
		for(File file : wilist)
		{
			String p=file.getAbsolutePath().substring(path.length()).substring(UrlConfig.WI_PATH.length()+1);
			
			DCCListModel dccModel=new DCCListModel();
			
			dccModel.set("customer",p.substring(0, p.indexOf("/")).toUpperCase());
			dccModel.set("type", "WI");
			//获取PN number
			if(p.substring(p.indexOf("/")+1).equals(file.getName()))
			{
				if(file.getName().indexOf("-")>0)
				{
					dccModel.set("pn",file.getName().substring(file.getName().indexOf("-")+1, file.getName().indexOf(" ")));
					dccModel.set("rev",file.getName().substring(file.getName().indexOf(" "),file.getName().indexOf(".")));
				}
			}
			else 
			{
				p=p.substring(p.indexOf("/")+1);
				if(p.substring(0, p.indexOf("/")).indexOf("-")>0)
				{
					dccModel.set("pn", p.substring(p.indexOf("-")+1,p.indexOf(" ")));
					dccModel.set("rev",p.substring(p.indexOf(" "), p.indexOf("/")));
				}
			}
			
			dccModel.set("filename", file.getName());
			
			dccModel.set("filepath", file.getPath());
			dccModel.set("lastmodify", f.format(new Date(file.lastModified())));
			dccModel.set("operate", "<a href=\""+file.getAbsolutePath().substring(path.length())+"\" target=\"_brank\">"+"Open"+"</a>");
			if(dccModel.save())
				setAttr("status", "init finished");
		}
		renderJson();
	}
	
	public void delete()
	{
		if(DCCListModel.dao.deleteById(getParaToLong("id")))
		{
			setAttr("status", "删除成功");
		}
		else
		{
			setAttr("status","删除失败");
		}
		
		renderJson();
	}
	
	public void create()
	{
		//设置文件保存路径
		String path=getRequest().getServletContext().getRealPath("/")+UrlConfig.WI_PATH;
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //获取上传的文件
		UploadFile upfile=getFile("file");
		File file=upfile.getFile();
		String filename=file.getName();
		String newPath=path+File.separator+getPara("customer");
		FileScanner fileScanner=new FileScanner();
		fileScanner.copyFile(file, newPath);
		file.delete();
		DCCListModel dcc=new DCCListModel();
		dcc.set("customer", getPara("customer").toUpperCase());
		dcc.set("pn",getPara("pn"));
		dcc.set("type",getPara("type"));
		dcc.set("rev",getPara("rev"));
		dcc.set("filepath",newPath+File.separator+filename);
		dcc.set("filename", filename);
		dcc.set("lastmodify",f.format(new Date()));
		dcc.set("operate","<a href=\""+UrlConfig.WI_PATH+File.separator+getPara("customer")+File.separator+filename+"\" target=\"_brank\">"+"Open"+"</a>");
		dcc.save();
		System.out.println(filename);
		renderHtml("<html><body><textarea> upload ok,文件保存路径:"+file.getAbsolutePath()+"</textarea></body></html>");
	}
	
	public void view()
	{
		List<DCCListModel> list=DCCListModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	
}
