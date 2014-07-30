package com.amphenol.agis.controller;

import java.util.ArrayList;
import java.util.List;

import com.amphenol.agis.model.RoleModel;
import com.amphenol.agis.pojo.Role;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Page;

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
		r.set("resource_ids",getPara("resLookup.id"));
		r.set("available", getParaToBoolean("available"));
		try{
			r.save();
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","role_list");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
		}
		
		renderJson();
	}
	
	public void update()
	{
		RoleModel r=RoleModel.dao.findById(getPara("id"));
		r.set("role", getPara("role"));
		r.set("description",getPara("description"));
		r.set("resource_ids",getPara("resLookup.id"));
		r.set("available", true);
		try{
			r.update();
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("navTabId","role_list");
			setAttr("callbackType","closeCurrent");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
		}
		renderJson();
	}
	
	public void delete()
	{
		if(getParaToLong("id")==1)
		{
			setAttr("statusCode", "300");
			setAttr("message","默认权限不能删除");
		}
		else
		{
			if(RoleModel.dao.deleteById(getParaToLong("id")))
			{
				setAttr("statusCode", "200");
				setAttr("message","删除成功");
				setAttr("navTabId","role_list");
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
	
	private void getRole()
	{
		List<Role> list=new ArrayList<Role>();
		Page<RoleModel> pages=RoleModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		for(RoleModel r:pages.getList())
		{
			list.add(new Role(r));
		}
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("rolelist",list);
		
	}
	
	public void roleView()
	{
		getRole();
		render("/dwzpage/user/rolelist.jsp");
	}
	/**
	 * role 查找带回
	 */
	public void roleLookup()
	{
		getRole();
		render("/dwzpage/user/rolelookup.jsp");
	}
	
	public void openRoleForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/user/rolecreate.jsp");
		}
		else
		{
			setAttr("r",new Role(RoleModel.dao.findById(getParaToLong("id"))));
			render("/dwzpage/user/roleedit.jsp");
		}
	}
}

