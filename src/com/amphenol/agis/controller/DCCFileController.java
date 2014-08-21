package com.amphenol.agis.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.authz.annotation.RequiresAuthentication;


import com.amphenol.UrlConfig;
import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.StationModel;

import com.amphenol.agis.util.FileScanner;
import com.amphenol.agis.util.FileUtil;
import com.amphenol.agis.util.StringUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
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
		List<File> wilist=scanner.getPdfFileList(new File(path+UrlConfig.WI_PATH));
		
		for(File file : wilist)
		{
			String p=file.getAbsolutePath().substring(path.length()).substring(UrlConfig.WI_PATH.length()+1);
			// "/xx/xx/xx/xx.pdf"
			System.out.println(p);
			String pp=file.getAbsolutePath().substring(path.length());
			DCCListModel dccModel=new DCCListModel();
			String customer=p.substring(0, p.indexOf("/"));
			dccModel.set("customer",customer.toUpperCase());
			dccModel.set("type", "WI");
			//获取PN number
			if(p.substring(p.indexOf("/")+1).equals(file.getName()))
			{
				if(file.getName().indexOf("-")>0)
				{
					dccModel.set("pn",file.getName().substring(file.getName().indexOf("-")+1, 10));
					dccModel.set("rev",file.getName().substring(13,file.getName().indexOf(".")));
				}
			}
			else 
			{
				p=p.substring(p.indexOf("/")+1);
				if(p.substring(0, p.indexOf("/")).indexOf("-")>0)
				{
					
						dccModel.set("pn", p.substring(p.indexOf("-")+1,10));
					
					//dccModel.set("rev",p.substring(p.indexOf(" "), p.indexOf("/")));
				}
			}
			
			dccModel.set("filename", file.getName());
			
			dccModel.set("filepath", pp);
			dccModel.set("lastmodify", f.format(new Date(file.lastModified())));
			dccModel.set("operate", "<a href=\""+file.getAbsolutePath().substring(path.length())+"\" target=\"_brank\">"+"Open"+"</a>");
			dccModel.save();
				
		}
		setAttr("statusCode", "200");
		setAttr("message","更新成功");
		setAttr("callbackType","closeCurrent");
		setAttr("navTabId","wi_list");
		renderJson();
	}
	
	public void regexInitWI()
	{
		FileScanner scanner = new FileScanner();
		String path=getRequest().getServletContext().getRealPath("/");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<File> wilist=scanner.getPdfFileList(new File(path+UrlConfig.WI_PATH));
		String regex1="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*[\\-|\\w])/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[\\-_]*(\\w+)\\.pdf";
		String regex2="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*[\\-|\\w])\\.pdf";
		String regex3="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})[\\-_]([a-zA-Z]+\\s*[a-zA-Z]*)[\\-_]([rR][Ee][vV]\\s*[\\-|\\w])\\s*\\.pdf";
		Pattern p1=Pattern.compile(regex1);
		Pattern p2=Pattern.compile(regex2);
		Pattern p3=Pattern.compile(regex3);
		for(File file : wilist)
		{
			Matcher m1=p1.matcher(file.getAbsolutePath());
			Matcher m2=p2.matcher(file.getAbsolutePath());
			Matcher m3=p3.matcher(file.getAbsolutePath());
			DCCListModel dccModel=new DCCListModel();
			if(m1.find())
			{
				
				
				dccModel.set("customer", m1.group(1).toUpperCase());
				dccModel.set("station", m1.group(5).toUpperCase());
				dccModel.set("pn", m1.group(2));
				dccModel.set("rev",m1.group(3));
					
			}
			else if(m2.find())
			{
				dccModel.set("customer", m2.group(1).toUpperCase());
				dccModel.set("pn",m2.group(2));
				dccModel.set("rev",m2.group(3));
			}
			else if(m3.find())
			{
				dccModel.set("customer", m3.group(1).toUpperCase());
				dccModel.set("pn", m3.group(3));
				dccModel.set("station", m3.group(4).toUpperCase());
				dccModel.set("rev", m3.group(5));
			}
			// "/xx/xx/xx/xx.pdf"
			
			String pp=file.getAbsolutePath().substring(path.length());
			
			//获取PN number
			
			
			dccModel.set("filename", file.getName());
			
			dccModel.set("filepath", pp);
			dccModel.set("lastmodify", f.format(new Date(file.lastModified())));
			dccModel.set("operate", "<a href=\""+file.getAbsolutePath().substring(path.length())+"\" target=\"_brank\">"+"Open"+"</a>");
			dccModel.save();
				
		}
		setAttr("statusCode", "200");
		setAttr("message","更新成功");
		
		setAttr("navTabId","wi_publish");
		renderJson();
	}
	
	public void delete()
	{
		if(getParaToLong("id")!=null)
		{
			String filepath=DCCListModel.dao.findById(getParaToLong("id")).getStr("filepath");
			if(new File(getRequest().getServletContext().getRealPath("/")+filepath).delete())
			{
				
				DCCListModel.dao.deleteById(getParaToLong("id"));
				setAttr("statusCode", "200");
				setAttr("message","删除成功");
			
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","删除失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
		}
		renderJson();
	}
	
	public void create()
	{
		//设置文件保存路径
		String path=getRequest().getServletContext().getRealPath("/");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //获取上传的文件
		UploadFile upfile=getFile("wi");
		File file=upfile.getFile();
		String filename=file.getName();
		System.out.println(file.getAbsolutePath()+"  "+filename);
		DCCListModel dcc=new DCCListModel();
		String regex="([wW][iI])[\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})[\\-_]([a-zA-Z]+\\s*[a-zA-Z]*)[\\-_]([rR][Ee][vV]\\s*[\\-|\\w])\\s*\\.pdf";
		Pattern p1=Pattern.compile(regex);
		Matcher m1=p1.matcher(filename);
		dcc.set("customer", getPara("customer").toUpperCase());
		if(m1.find())
		{
			for(int i=0;i<=m1.groupCount();i++)
			{
				System.out.println("i="+i+" :"+m1.group(i));
			}
			String type=m1.group(1);
			String pn=m1.group(2);
			String rev = m1.group(4);
			String wiPath=UrlConfig.WI_PATH+"/"+StringUtil.toUpperCaseFirstOne(getPara("customer").toLowerCase())+"/"+type.toUpperCase()+"-"+pn;
			FileUtil.copyFile(file, path+wiPath);
			file.delete();
			dcc.set("pn",pn);
			dcc.set("type",type);
			dcc.set("rev",rev);
			dcc.set("station",getPara("station").toUpperCase());
			dcc.set("filepath",wiPath+"/"+filename);
			dcc.set("filename", filename);
			dcc.set("lastmodify",f.format(new Date()));
			dcc.set("operate","<a href=\""+UrlConfig.WI_PATH+File.separator+getPara("customer")+File.separator+filename+"\" target=\"_brank\">"+"Open"+"</a>");
			if(dcc.save())
			{
				setAttr("statusCode", "200");
				setAttr("message","更新成功");
				setAttr("callbackType","closeCurrent");
				setAttr("navTabId","wi_publish");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","文件保存失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","文件名不规范");
		}
		
		renderJson();
			
	}
	
	public void view()
	{
		List<DCCListModel> list=DCCListModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	
	public void wiview()
	{
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		List<DCCListModel> list= new ArrayList<DCCListModel>();
	
		Page<DCCListModel> pages=DCCListModel.dao.paginate(pageNumber, pageSize);
		list=pages.getList();
		int totalCount=pages.getTotalRow();
		int numPerPage=pages.getPageSize();
		int currentPage=pages.getPageNumber();
		System.out.println("totalCount:"+totalCount+" numPerPage:"+numPerPage+"currentPage: "+currentPage);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("wilist",list);
		render("/dwzpage/wi/wilist.jsp");
	}
	
	public void search()
	{
		String key=getPara("key");
		String words=getPara("words");
		int pageNumber=1;
		int pageSize =20; 
		List<DCCListModel> list= new ArrayList<DCCListModel>();
		Page<DCCListModel> pages=DCCListModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
		list=pages.getList();
		int totalCount=pages.getTotalRow();
		int numPerPage=pages.getPageSize();
		int currentPage=pages.getPageNumber();
		System.out.println("totalCount:"+totalCount+" numPerPage:"+numPerPage+"currentPage: "+currentPage);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("words",words);
		setAttr("wilist",list);
		render("/dwzpage/wi/wilist.jsp");
	}
	
	public void getStationList()
	{
		List<StationModel> list=StationModel.dao.findAll();
		renderJson(list);
	}
	
	public void getCustomerList()
	{
		List<DCCListModel> list=DCCListModel.dao.getCustomerList();
		renderJson(list);
	}
	public void openWiForm()
	{
		if(getPara("id")==null)
		{
			List<DCCListModel> customerList=DCCListModel.dao.getCustomerList();
			List<StationModel> stationList=StationModel.dao.findAll();
			setAttr("dcc",customerList);
			setAttr("station",stationList);
			render("/dwzpage/wi/publishwi.jsp");
		}
		else
		{
			
		}
	}
	
}
