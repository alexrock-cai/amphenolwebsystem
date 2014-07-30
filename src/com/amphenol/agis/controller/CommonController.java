package com.amphenol.agis.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.UrlConfig;
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

}
