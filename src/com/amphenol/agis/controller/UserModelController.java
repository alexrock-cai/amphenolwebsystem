package com.amphenol.agis.controller;


import java.util.ArrayList;
import java.util.List;

import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.pojo.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Page;

/**
 * sys_user 表进行 CRUD 操作类
 * @author rocky
 *
 */

public class UserModelController extends Controller 
{
	public void index()
	{
		render("hello User");
	}
	
	public void openUserForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/user/usercreate.jsp");
		}
		else
		{
			setAttr("u", new User(UserModel.dao.findById(getParaToLong("id"))));
			render("/dwzpage/user/useredit.jsp");
		}
	}
	
	/**
	 * 新建用户
	 */
	
	public void create()
	{
		UserModel u=getModel(UserModel.class);
		u.set("username", getPara("username"));
		u.set("password",getPara("password"));
		u.set("name", getPara("name"));
		u.set("organization_id", 1);
		u.set("role_ids",getPara("roleLookup.id"));
		try{
			u.save();
			setAttr("statusCode", "200");
			setAttr("message","创建成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","user_list");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","创建失败");
		}
		
		renderJson();
	}
	/**
	 * 更新用户
	 */
	public void update()
	{
		UserModel u=UserModel.dao.findById(getPara("id"));
		System.out.println(getPara("username"));
		u.set("username", getPara("username"));
		u.set("name", getPara("name"));
		u.set("password",getPara("password"));
		u.set("organization_id", 1);
		u.set("role_ids", getPara("roleLookup.id"));
		try{
			u.update();
			setAttr("statusCode", "200");
			setAttr("message","更新成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","user_list");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","更新失败");
		}
		renderJson();
	}
	/**
	 * 删除用户
	 */
	public void delete()
	{
		if(getParaToLong("id")==1)
		{
			setAttr("statusCode", "300");
			setAttr("message","root 账号不能删除");
		}
		else
		{	
			if(UserModel.dao.deleteById(getParaToLong("id")))
			{
				setAttr("statusCode", "200");
				setAttr("message","删除成功");
				setAttr("navTabId","user_list");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","删除失败");
			}
		}
		renderJson();
	}
	/**
	 * 查看用户
	 */
	public void view()
	{
		List<UserModel> list=UserModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	
	public void userView()
	{
		
		getUser();
		renderJsp("/dwzpage/user/userlist.jsp");
	}
	
	private void getUser()
	{
		List<User> list=new ArrayList<User>();
		Page<UserModel> pages=UserModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		for(UserModel u: pages.getList())
		{
			list.add(new User(u));
			
		}
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("userlist",list);
	}
}
