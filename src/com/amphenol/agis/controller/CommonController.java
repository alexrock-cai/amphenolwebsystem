package com.amphenol.agis.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.UrlConfig;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.pojo.TestData;
import com.amphenol.agis.util.BatchCreateUser;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class CommonController extends Controller 

{
	@RequiresAuthentication
	public void index()
	{
		render(UrlConfig.INDEX_URL);
	}
	
	public void tt(){
		render("/bootstrap/dashboard.jsp");
	}
	
	public void pm(){
		render("/bootstrap/pmdashboard.jsp");
	}
	public void bootgrid(){
		int current=getParaToInt("current");
		int rowCount=getParaToInt("rowCount");
		Page<UserModel> pages=UserModel.dao.paginate(current, rowCount);
		
		setAttr("current", current);
		setAttr("rowCount", rowCount);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	public void timeout()
	{
		setAttr("statusCode",301);
		setAttr("message","会话超时请重新登录");
	}
	
	/**
	 * 从excel中批量创建用户
	 */
	public void initUser()
	{
		
		try {
			BatchCreateUser.createUser(new File("/Users/rocky/Desktop/userlist.xls"));
			setAttr("statusCode","200");
			setAttr("message","批量创建用户成功");
			setAttr("navTabId","user_list");
			
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			setAttr("statusCode","300");
			setAttr("message","批量创建用户失败");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setAttr("statusCode","300");
			setAttr("message","批量创建用户失败");
		}
		renderJson();
	}

}
