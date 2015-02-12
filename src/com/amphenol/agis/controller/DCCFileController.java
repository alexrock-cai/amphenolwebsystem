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
import com.amphenol.agis.model.UserDccCertModel;

import com.amphenol.agis.util.FileScanner;
import com.amphenol.agis.util.FileUtil;
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
		List<File> wilist=scanner.getPdfFileList(new File(path+UrlConfig.OBA_CHECKLIST_PATH));
		// customer/WI-KT12345678 REV/WI-KT12345678 REV A.pdf
		String regex1="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*[\\-|\\w])/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[\\-_]*(\\w+)\\.pdf";
		// customer/WI-KT12345678 REV A.pdf
		String regex2="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*([rR][Ee][vV]\\s*\\.*[\\-|\\w])\\.pdf";
		//customer/WI-KT12345678/WI-KT12345678-STATION-REV A.pdf
		String regex3="([a-zA-Z]+)/[wW]*[iI]*[\\-_]*([a-zA-Z]{2}\\d{8}|\\d{10})\\s*\\w*\\s*[-]*/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})\\s*[\\-_]*\\s*([a-zA-Z]+\\s*[a-zA-Z]*)[\\-_]*\\s*([rR][Ee][vV]\\s*\\.*[\\-|\\w])\\s*\\.pdf";
		String regex4="([a-zA-Z]+)/[wW][iI][\\-_]([a-zA-Z]{2}\\d{8}|\\d{10})/([\\w]+[-][\\w]+[-][\\w]+[-]*\\w*)\\s*([rR][Ee][vV]\\s*\\.*[\\-|\\w])\\s*\\.pdf";
		String regex5="/([\\w]*[\\-\\s]?[\\w]*)/CKL\\-OBA\\-([a-zA-Z]{2}\\d{8}|\\d{10})[\\-]*[\\w]*\\s*([rR][eE][vV]\\s*\\.*[\\-|\\w])?\\s*\\.pdf";
		String regex6="/(\\w*\\-*\\s*\\w*)/CKL\\-OBA\\-([a-zA-Z]{2}\\d{8}|\\d{10})\\s?([rR][eE][vV]\\s*\\.*[\\-|\\w])?/[\\w\\d\\-]*\\.pdf";
		Pattern p1=Pattern.compile(regex1);
		Pattern p2=Pattern.compile(regex2);
		Pattern p3=Pattern.compile(regex3);
		Pattern p4=Pattern.compile(regex4);
		Pattern p5=Pattern.compile(regex5);
		Pattern p6=Pattern.compile(regex6);
		for(File file : wilist)
		{
			Matcher m1=p1.matcher(file.getAbsolutePath());
			Matcher m2=p2.matcher(file.getAbsolutePath());
			Matcher m3=p3.matcher(file.getAbsolutePath());
			Matcher m4=p4.matcher(file.getAbsolutePath());
			Matcher m5=p5.matcher(file.getAbsolutePath());
			Matcher m6=p6.matcher(file.getAbsolutePath());
			DCCListModel dccModel=new DCCListModel();
			if(m5.find())
			{
				dccModel.set("customer", m5.group(1));
				dccModel.set("station","OBA");
				dccModel.set("type","OBA Check list");
				dccModel.set("pn", m5.group(2));
				dccModel.set("rev", m5.group(3));
			}
			else if(m6.find())
			{
				dccModel.set("station","OBA");
				dccModel.set("type","OBA Check list");
				dccModel.set("customer",m6.group(1));
				dccModel.set("pn", m6.group(2));
				dccModel.set("rev",m6.group(3));
			}
			else if(m1.find())
			{
				
				
				dccModel.set("customer", m1.group(1));
				dccModel.set("station", m1.group(5).toUpperCase());
				dccModel.set("pn", m1.group(2));
				dccModel.set("rev",m1.group(3));
					
			}
			else if(m2.find())
			{
				dccModel.set("customer", m2.group(1));
				dccModel.set("pn",m2.group(2));
				dccModel.set("rev",m2.group(3));
			}
			else if(m3.find())
			{
				dccModel.set("customer", m3.group(1));
				dccModel.set("pn", m3.group(3));
				dccModel.set("station", m3.group(4).toUpperCase());
				dccModel.set("rev", m3.group(5));
			}
			else if(m4.find())
			{
				dccModel.set("customer", m4.group(1));
				dccModel.set("pn", m4.group(2));
				dccModel.set("station", "CHESS");
				dccModel.set("rev", m4.group(4));
			}
			else
			{
				dccModel.set("station", "NoneStation");
			}
			// "/xx/xx/xx/xx.pdf"
			
			String pp=file.getAbsolutePath().substring(path.length());
			
			//获取PN number
			
			
			dccModel.set("filename", file.getName());
			
			dccModel.set("filepath", pp);
			dccModel.set("lastmodify", f.format(new Date(file.lastModified())));
			dccModel.set("operate", "<a href=\""+file.getAbsolutePath().substring(path.length())+"\" target=\"_brank\">"+"Open"+"</a>");
			try
			{
				dccModel.save();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
			
				
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
			// windows system code 
			filepath=filepath.replace("/", "\\");
			System.out.println("Delete filepath:"+filepath);
			//windows system code 
			System.out.println("Delete filepath2:"+"H:\\Tomcat 7.0\\webapps"+filepath);
			File testfile = new File("H:\\Tomcat 7.0\\webapps"+filepath);
			System.out.println(testfile.getAbsolutePath());
			if(new File("H:\\Tomcat 7.0\\webapps"+filepath).delete())
			//if(new File(getRequest().getServletContext().getRealPath("/")+filepath).delete())
			{
				
				DCCListModel.dao.deleteById(getParaToLong("id"));
				
				//2015-2-1 新增加 删除DccList记录同事更新WI认证表，将对应的记录置为失效。
				List<UserDccCertModel> dccCertModels=UserDccCertModel.dao.findByDccId(getParaToLong("id"));
				System.out.println("DCC版本更新：ID【 "+getParaToLong("id")+"】,用户认证取消："+dccCertModels);
				if(dccCertModels!=null){
					
					for(UserDccCertModel userDccCertModel : dccCertModels){
						userDccCertModel.delete();
					}
				}
				
				//2015-2-1 新增代码结束
				setAttr("statusCode", "200");
				setAttr("message","删除成功");
				setAttr("navTabId","wi_publish");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","单个删除失败");
			}
		}
		else if(getPara("ids")!=null)
		{
			String[] ids=getPara("ids").split(",");
			boolean flag=false;
			for (String id : ids) {
				String filepath=DCCListModel.dao.findById(id).getStr("filepath");
				//windows system code
				filepath=filepath.replace("/", "\\");
				System.out.println("Delete filepath:"+filepath);
				//windows system code
				if(new File("H:\\Tomcat 7.0\\webapps"+filepath).delete())
				//if(new File(getRequest().getServletContext().getRealPath("/")+filepath).delete()) //linux ,mac os system code 
				{
					
					flag=DCCListModel.dao.deleteById(id);
					
					
				}
				
			}
			if(flag)
			{
				setAttr("statusCode", "200");
				setAttr("message","批量删除成功");
				setAttr("navTabId","wi_publish");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","批量删除失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","id读取错误，删除失败");
		}
		renderJson();
	}
	
	public void create()
	{
		DCCListModel dcc=new DCCListModel();
		if(getFile("wi")!=null)
		{
			UploadFile upfile=getFile("wi");
			File file=upfile.getFile();
			String filename=file.getName();
			System.out.println(file.getAbsolutePath()+"  "+filename);
			//String path=getRequest().getServletContext().getRealPath("/");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				dcc.set("customer", getPara("customer").toUpperCase());
				String path=UrlConfig.WI_PATH+"/"+getPara("customer")+"/"+getPara("type")+"-"+getPara("pn").toUpperCase();
				//windows 系统代码
				String targetPath="H:\\Tomcat 7.0\\webapps\\static\\"+getPara("type")+"\\"+getPara("customer")+"\\"+getPara("type")+"-"+getPara("pn").toUpperCase();
				if(getPara("type").equals("OBA Check list"))
				{
					path=UrlConfig.OBA_CHECKLIST_PATH+"/"+getPara("customer");
					targetPath="H:\\Tomcat 7.0\\webapps\\static\\"+getPara("type")+"\\"+getPara("customer");
				}
				 
				//windows 系统代码
				
				//FileUtil.copyFile(file, path+wiPath);
				
				FileUtil.copyFile(file, targetPath);//windows 系统适用
				file.delete();
				dcc.set("pn",getPara("pn").toUpperCase());
				dcc.set("type",getPara("type"));
				dcc.set("rev",getPara("rev"));
				dcc.set("station",getPara("station").toUpperCase());
				dcc.set("filepath",path+"/"+filename);
				dcc.set("filename", filename);
				dcc.set("lastmodify",f.format(new Date()));
				dcc.set("operate","<a href=\""+UrlConfig.WI_PATH+File.separator+getPara("customer")+File.separator+filename+"\" target=\"_brank\">"+"Open"+"</a>");
				dcc.set("uploadtime", f.format(new Date()));
				if(dcc.save())
				{
					setAttr("statusCode", "200");
					setAttr("message","保存成功");
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
			setAttr("message","请上传文件");
		}
		
		//设置文件保存路径
		
		
		renderJson();
			
	}
	//老代码废除
	public void view()
	{
		List<DCCListModel> list=DCCListModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	//废除不用
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
	//废除不用
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
	
	public void update()
	{
		DCCListModel dcc = DCCListModel.dao.findById(getPara("id"));
		if(dcc!=null)
		{
			System.out.println("hellw");
			dcc.set("pn", getPara("pn"));
			dcc.set("customer",getPara("customer"));
			dcc.set("type",getPara("type"));
			dcc.set("rev",getPara("rev"));
			dcc.set("station",getPara("station"));
			if(dcc.update())
			{
				setAttr("statusCode", "200");
				setAttr("message","更新成功");
				setAttr("callbackType","closeCurrent");
				setAttr("navTabId","wi_publish");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","更新出错");
			}
		}
		else
		{
			System.out.println("dddd");
			setAttr("statusCode", "300");
			setAttr("message","文件保存失败");
		}
		renderJson();
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
			DCCListModel dcc=DCCListModel.dao.findById(getPara("id"));
			setAttr("did",getPara("id"));
			System.out.println(dcc);
			List<DCCListModel> customerList=DCCListModel.dao.getCustomerList();
			List<StationModel> stationList=StationModel.dao.findAll();
			
			setAttr("dcc",customerList);
			setAttr("stationList",stationList);
			
			setAttr("customer",dcc.get("customer"));
			setAttr("pn",dcc.get("pn"));
			setAttr("rev",dcc.get("rev"));
			setAttr("station",dcc.get("station"));
			render("/dwzpage/wi/updatewi.jsp");
		}
	}
	
	public void myWi()
	{
		
		
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<DCCListModel> dccList = new ArrayList<DCCListModel>();
		
		int totalCount;
		int numPerPage;
		int currentPage;		
			//dccList=DCCListModel.dao.findAll();
			//判断是否是查询
			if(words!=null)
			{
				Page<DCCListModel> pages=DCCListModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
				dccList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<DCCListModel> pages=DCCListModel.dao.paginate(pageNumber, pageSize);
				dccList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			

		
		//setAttr("identifier", "id");
		//setAttr("items",dccList);
		//renderJson(new String[]{"items","identifier"});
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("wilist",dccList);
		render("/dwzpage/wi/wilist.jsp");
	}
	
}
