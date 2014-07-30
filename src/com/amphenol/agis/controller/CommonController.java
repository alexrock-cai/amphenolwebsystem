package com.amphenol.agis.controller;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.UrlConfig;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.util.BatchCreateUser;
import com.jfinal.core.Controller;

public class CommonController extends Controller 

{
	@RequiresAuthentication
	public void index()
	{
		render(UrlConfig.INDEX_URL);
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
