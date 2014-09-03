package com.amphenol.agis.shiro;

import java.io.UnsupportedEncodingException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class SessionHandler extends Handler
{
	
	
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled)
	{
		int index = target.indexOf(";jsessionid".toUpperCase());
		if (index != -1) target = target.substring(0, index);
		//System.out.println("target:"+target);
		try {
			target=new String(target.getBytes("ISO8859-1"),"UTF-8");
			//System.out.println("new target:"+target);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nextHandler.handle(target, request, response, isHandled);
	}
}
