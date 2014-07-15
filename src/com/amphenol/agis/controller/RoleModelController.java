package com.amphenol.agis.controller;

import java.util.List;

import com.amphenol.agis.model.RoleModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;

public class RoleModelController extends Controller 
{
	public void index()
	{
		
	}
	
	public void create()
	{
		RoleModel r=getModel(RoleModel.class);
		r.set("role", getPara("role"));
		r.set("description",getPara("description"));
		r.set("resource_ids",getPara("resource_ids"));
		r.set("available", true);
		try{
			r.save();
			setAttr("status", "保存成功");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("status","保存失败");
		}
		
		renderJson();
	}
	
	public void update()
	{
		RoleModel r=RoleModel.dao.findById(getPara("roleid"));
		r.set("role", getPara("role"));
		r.set("description",getPara("description"));
		r.set("resource_ids",getPara("resource_ids"));
		r.set("available", true);
		try{
			r.update();
			setAttr("status", "保存成功");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("status","保存失败");
		}
		renderJson();
	}
	
	public void delete()
	{
		if(getParaToLong("id")==1)
		{
			setAttr("status","admin 为默认权限不能删除");
		}
		else
		{
			if(RoleModel.dao.deleteById(getParaToLong("id")))
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
	 * view all role from sys_role
	 * 
	 */
	public void view()
	{
		List<RoleModel> list=RoleModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
}

