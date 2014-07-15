package com.amphenol.agis.controller;


import java.util.List;

import com.amphenol.agis.model.UserModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;

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
	/**
	 * 新建用户
	 */
	public void create()
	{
		UserModel u=getModel(UserModel.class);
		u.set("username", getPara("username"));
		u.set("password",getPara("password"));
		u.set("organization_id", 1);
		u.set("role_ids",getPara("role_ids"));
		try{
			u.save();
			setAttr("status", "保存成功");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("status","保存失败");
		}
		
		renderJson();
	}
	/**
	 * 更新用户
	 */
	public void update()
	{
		UserModel u=UserModel.dao.findById(getPara("userid"));
		u.set("username", getPara("username"));
		u.set("password",getPara("password"));
		u.set("organization_id", getPara("organization_id"));
		u.set("role_ids", getPara("role_ids"));
		try{
			u.update();
			setAttr("status", "修改成功");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("status","修改失败");
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
			setAttr("status","root 账号不能删除");
		}
		else
		{	
			if(UserModel.dao.deleteById(getParaToLong("id")))
			{
				setAttr("status","删除成功");
			}
			else
			{
				setAttr("status","删除失败");
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
}
