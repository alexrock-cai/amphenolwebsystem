package com.amphenol.agis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.UserDccCertModel;
import com.amphenol.agis.model.UserModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class WIViewController extends Controller
{
	public void index()
	{
		renderText("<strong>你貌似没有该产品WI查看权限，如需要该权限请向主管申请！</strong>");
	}
	/**
	 * 在登录界面显示我所能查看的Wi有哪些
	 */
	@RequiresAuthentication 
	public void myWi()
	{
		
		UserModel user=(UserModel)getSessionAttr("user");
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<DCCListModel> dccList = new ArrayList<DCCListModel>();
		boolean hasAllWI = false;
		int totalCount;
		int numPerPage;
		int currentPage;
		List<String> list = new ArrayList<String>();
		for(String s : user.getPermissionNameList())
		{
			//判断是否有wi的权限
			if(s.split(":")[0].equals("wi"))
			{
				if(s.split(":")[1].equals("*"))
				{
					hasAllWI=true;
					break;
				}
				else
				{
					list.add(s.split(":")[1]);
				}
			}
			//System.out.println("s--->:"+s);
			
		}
		if(hasAllWI)
		{
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
			
		}
		else
		{
			//dccList= DCCListModel.dao.findByCustomer(list);
			if(words!=null)
			{
				//2015-2-11 修改
				Page<DCCListModel> pages=DCCListModel.dao.paginateByKeyWordsAndUploadTime(pageNumber, pageSize,"station",list,key,words);
				dccList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				//2015-2-11 修改
				Page<DCCListModel> pages=DCCListModel.dao.paginateByKeyWordsAndUploadTime(pageNumber, pageSize,"station",list);
				dccList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
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
		render("/dwzpage/wi/mywilist.jsp");
	}
	
	/**
	 * 2015-1-31 启用新的认证规则获取已认证WI清单
	 */
	@RequiresAuthentication 
	public void newMyWi(){
		UserModel user=(UserModel)getSessionAttr("user");
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		List<DCCListModel> dccList = new ArrayList<DCCListModel>();
		boolean hasAllWI = false;
		int totalCount;
		int numPerPage;
		int currentPage;
		List<String> list = new ArrayList<String>();
		for(String s : user.getPermissionNameList())
		{
			//判断是否有wi的权限
			if(s.split(":")[0].equals("wi"))
			{
				if(s.split(":")[1].equals("*"))
				{
					hasAllWI=true;
					break;
				}
				else
				{
					list.add(s.split(":")[1]);
				}
			}
			//System.out.println("s--->:"+s);
			
		}
		if(hasAllWI)
		{
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
			
		}
		else
		{
			//dccList= DCCListModel.dao.findByCustomer(list);
			
			if(words!=null)
			{
				Page<DCCListModel> pages=UserDccCertModel.dao.paginateDccBykeyWords(pageNumber, pageSize, user.getLong("id"), key, words);
				dccList=pages.getList();
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			else
			{
				Page<UserDccCertModel> pages= UserDccCertModel.dao.paginateByUserId(pageNumber, pageSize, user.getLong("id"));
				dccList=UserDccCertModel.dao.paginateDccList(pageNumber, pageSize, user.getLong("id"));
				totalCount=pages.getTotalRow();
				numPerPage=pages.getPageSize();
				currentPage=pages.getPageNumber();
			}
			
		}
		
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("wilist",dccList);
		render("/dwzpage/wi/newmywilist.jsp");
	}
	
	
}
