package com.amphenol.agis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.UserModel;
import com.jfinal.core.Controller;

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
		List<DCCListModel> dccList = new ArrayList<DCCListModel>();
		boolean hasAllWI = false;
		
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
			dccList=DCCListModel.dao.findAll();
		}
		else
		{
			dccList= DCCListModel.dao.findByCustomer(list);
		}
		
		setAttr("identifier", "id");
		setAttr("items",dccList);
		renderJson(new String[]{"items","identifier"});
	}
}
