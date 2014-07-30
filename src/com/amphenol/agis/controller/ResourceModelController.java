package com.amphenol.agis.controller;

import java.util.ArrayList;
import java.util.List;

import com.amphenol.agis.model.ResourceModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Page;

public class ResourceModelController extends Controller 
{
	public void index()
	{
		
	}
	
	public void create()
	{
		ResourceModel res=getModel(ResourceModel.class);
		res.set("id", getParaToLong("id"));
		res.set("name", getPara("name"));
		res.set("type", getPara("type"));
		res.set("url",getPara("url"));
		res.set("parent_id",getPara("parent_id"));
		res.set("parent_ids", getPara("parent_ids"));
		res.set("permission", getPara("permission"));
		res.set("available", true);
		try
		{
			res.save();
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","res_list");
		}
		catch(ActiveRecordException e)
		{
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
		}
		renderJson();
	}
	
	public void update()
	{
		ResourceModel res=ResourceModel.dao.findById(getParaToLong("resid"));
		res.set("name", getPara("resname"));
		res.set("type", getPara("restype"));
		res.set("url",getPara("resurl"));
		res.set("parent_id",getPara("parent_id"));
		res.set("parent_ids", getPara("parent_ids"));
		res.set("permission", getPara("permission"));
		try
		{
			res.update();
			setAttr("status","修改成功");
		}
		catch(ActiveRecordException e)
		{
			setAttr("status","创建失败");
		}
		renderJson();
	}
	
	public void edit()
	{
		ResourceModel res=ResourceModel.dao.findById(getParaToLong("id"));
		res.set("name", getPara("name"));
		res.set("type", getPara("type"));
		res.set("url",getPara("url"));
		res.set("parent_id",getPara("parent_id"));
		res.set("parent_ids", getPara("parent_ids"));
		res.set("permission", getPara("permission"));
		try
		{
			res.update();
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","res_list");
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
		}
		renderJson();
	}
	
	public void delete()
	{
		if(ResourceModel.dao.deleteById(getParaToLong("id")))
		{
			setAttr("statusCode", "200");
			setAttr("message","删除成功");
			setAttr("navTabId","res_list");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
		}
		renderJson();
	}
	
	public void view()
	{
		List<ResourceModel> list=ResourceModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}

	public void resView()
	{
		getRes();
		render("/dwzpage/user/resourcelist.jsp");
	}
	
	private void getRes()
	{
		List<ResourceModel> list=new ArrayList<ResourceModel>();
		Page<ResourceModel> pages=ResourceModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		list=pages.getList();
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("reslist",list);
	}
	
	public void resLookup()
	{
		getRes();
		render("/dwzpage/user/reslookup.jsp");
	}
	
	public void openResForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/user/rescreate.jsp");
		}
		else
		{
			setAttr("res",ResourceModel.dao.findById(getParaToInt("id")));
			render("/dwzpage/user/resedit.jsp");
		}
	}
}
