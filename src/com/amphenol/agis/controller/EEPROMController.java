package com.amphenol.agis.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.EcheckModel;
import com.amphenol.agis.model.EepConfigModel;
import com.amphenol.agis.model.PkgModel;
import com.amphenol.agis.model.ProductModel;
import com.amphenol.agis.model.ProgramModel;
import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.model.VerifyModel;
import com.amphenol.agis.util.EmailService;
import com.amphenol.agis.util.FileReader;
import com.amphenol.agis.util.FileScanner;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class EEPROMController extends Controller 
{
	public void index()
	{
		view();
	}
	/**
	 * 打开EEPROM配置设置对话框，如果能获取到id信息显示编辑窗口，
	 * 如果没有id信息显示新建窗口。
	 */
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
	/**
	 * Json版本显示配置，数据用Json格式返回
	 */
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
	/**
	 * DWZ版显示配置文件
	 */
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
	
	public void pkgInput()
	{
		PkgModel pkg=new PkgModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String customerSn=getPara("customer_sn").trim();
		String regex="[xX]\\d{9}";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(customerSn);
		if(m.matches())
		{
			
			if(PkgModel.dao.findByCustomerSn(getPara("customer_sn"))!=null)
			{
				setAttr("statusCode", "300");
				setAttr("message","产品序列号重复！！！");
			}
			else
			{
				pkg.set("customer_sn", getPara("customer_sn"));
				pkg.set("customer_name", getPara("customer_name"));
				pkg.set("pn", getPara("pn"));
				pkg.set("wo",getPara("wo"));
				pkg.set("timestamp", sdf.format(new Date()));
				if(pkg.save())
				{
					
					setAttr("customer_name",getPara("customer_name"));
					setAttr("pn",getPara("pn"));
					setAttr("wo",getPara("wo"));
					openPkgInputPage();
					return;
				}
				else
				{
					setAttr("statusCode", "300");
					setAttr("message","保存失败");
				}
			}
		}
		
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","序列号有误，请重新扫描");
		}
		
		renderJson();
	}
	
	public void deletePkgInput()
	{
		if(PkgModel.dao.deleteById(getParaToLong("id")))
		{
			setAttr("statusCode", "200");
			setAttr("message","删除成功");
			setAttr("navTabId","pkg_input");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
		}
		renderJson();
	}
	
	/**
	 * 打开包装EEPROM扫描输入页面
	 */
	public void openPkgInputPage()
	{
		getPkgInfo();
		render("/dwzpage/eeprom/pkginput.jsp");
	}
	/**
	 * 获取EEPROM包装扫描输入的数据
	 */
	private void getPkgInfo() {
		
		List<PkgModel> list=new ArrayList<PkgModel>();
		list=PkgModel.dao.findAll();
		setAttr("pkglist",list);
	}
	/**
	 * 打开EEPROM Dashboard，主要显示文件上传，启动检测等等。
	 */
	public void dashboard()
	{
		render("/dwzpage/eeprom/edashboard.jsp");
	}
	/**
	 * 打开文件上传页面
	 */
	public void openUploadForm()
	{
		setAttr("filetype",getPara("filetype"));
		render("/dwzpage/eeprom/uploaddata.jsp");
	}
	/**
	 * 上传文件处理，上传文件，上传文件大小必须小于10M。
	 */
	public void uploadData()
	{
		
		UploadFile upfile=getFile("datafile");
		File file=upfile.getFile();
		System.out.println(file.getName());
		System.out.println(!file.getName().endsWith(".xlsx"));
		if(file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx"))
		{
			
			FileReader reader = new FileReader();
			reader.readExcel(file, getPara("filetype"));
			setAttr("statusCode", "200");
			setAttr("message","数据导入成功");
			setAttr("callbackType","closeCurrent");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","文件格式不对，只支持Excel文件导入");
			
		}
		renderJson();
	}
	
	public void productView()
	{

		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<ProductModel> productList = new ArrayList<ProductModel>();
		
		int totalCount;
		int numPerPage;
		int currentPage;		
			//dccList=DCCListModel.dao.findAll();
			//判断是否是查询
			if(words!=null)
			{
				Page<ProductModel> pages=ProductModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
				productList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<ProductModel> pages=ProductModel.dao.paginate(pageNumber, pageSize);
				productList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("productList",productList);
		render("/dwzpage/eeprom/productlist.jsp");
	}
	
	public void programView()
	{
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<ProgramModel> programList = new ArrayList<ProgramModel>();
		
		int totalCount;
		int numPerPage;
		int currentPage;		
			if(words!=null)
			{
				Page<ProgramModel> pages=ProgramModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
				programList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<ProgramModel> pages=ProgramModel.dao.paginate(pageNumber, pageSize);
				programList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("programList",programList);
		render("/dwzpage/eeprom/programlist.jsp");
	}
	
	public void verifyView()
	{
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<VerifyModel> verifyList = new ArrayList<VerifyModel>();
		
		int totalCount;
		int numPerPage;
		int currentPage;		
			if(words!=null)
			{
				Page<VerifyModel> pages=VerifyModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
				verifyList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<VerifyModel> pages=VerifyModel.dao.paginate(pageNumber, pageSize);
				verifyList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("verifyList",verifyList);
		render("/dwzpage/eeprom/verifylist.jsp");
	}
	
	public void echeckView()
	{
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<EcheckModel> echeckList = new ArrayList<EcheckModel>();
		
		int totalCount;
		int numPerPage;
		int currentPage;		
			if(words!=null)
			{
				Page<EcheckModel> pages=EcheckModel.dao.paginateByKeyWords(pageNumber, pageSize, key, words);
				echeckList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<EcheckModel> pages=EcheckModel.dao.paginate(pageNumber, pageSize);
				echeckList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("echeckList",echeckList);
		render("/dwzpage/eeprom/echecklist.jsp");
	}
	//发货扫描
	public void checkShipdata()
	{
		String workPath=EepConfigModel.dao.getWorkPath();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=f.format(new Date());
		FileReader reader=new FileReader();
		FileScanner scanner= new FileScanner();
		List<File> files=scanner.getTxtFileList(new File(workPath));
		try
		{
			reader.readFiles(files);
			reader.checkStatus();
			List<ShipdataModel> noEEPROM= ShipdataModel.dao.getNoEEPROMList();
			List<ShipdataModel> notindts= ShipdataModel.dao.getNotOnDTSList();
			for (ShipdataModel shipdataModel : noEEPROM) {
				EcheckModel echeck = new EcheckModel();
				echeck.set("customer_name", shipdataModel.get("customer_name"));
				echeck.set("customer_sn", shipdataModel.get("customer_sn"));
				echeck.set("pn", shipdataModel.get("pn"));
				echeck.set("wo", shipdataModel.get("wo"));
				echeck.set("hasprogram",shipdataModel.get("hasprogram"));
				echeck.set("hasverify",shipdataModel.get("hasverify"));
				echeck.set("hastagin",shipdataModel.get("on_dts"));
				echeck.set("timestamp",startTime);
				try {
					echeck.save();
				} catch (ActiveRecordException e) {
					// TODO: handle exception
					continue;
				}
				
			}
			for (ShipdataModel shipdataModel : notindts) {
				EcheckModel echeck = new EcheckModel();
				echeck.set("customer_name", shipdataModel.get("customer_name"));
				echeck.set("customer_sn", shipdataModel.get("customer_sn"));
				echeck.set("pn", shipdataModel.get("pn"));
				echeck.set("wo", shipdataModel.get("wo"));
				echeck.set("hasprogram",shipdataModel.get("hasprogram"));
				echeck.set("hasverify",shipdataModel.get("hasverify"));
				echeck.set("hastagin",shipdataModel.get("on_dts"));
				echeck.set("timestamp",startTime);
				try {
					echeck.save();
				} catch (ActiveRecordException e) {
					// TODO: handle exception
					continue;
				}
			}
			
			if(new EmailService().sendNoEepromList(noEEPROM,notindts))
			{
				System.out.println("邮件发送成功");
				
				setAttr("statusCode", "200");
				setAttr("message","邮件发送成功");
			}
			else
			{
				System.out.println("邮件发送失败");
				setAttr("statusCode", "300");
				setAttr("message","邮件发送失败，请联系管理员");
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","读取log文件失败，请重试");
		}
		
		
		renderJson();
		
	}
	//包装入库扫描验证
	public void checkPkgInput()
	{
		String workPath=EepConfigModel.dao.getWorkPath();
		FileReader reader=new FileReader();
		FileScanner scanner= new FileScanner();
		List<File> files=scanner.getTxtFileList(new File(workPath));
	}
}
