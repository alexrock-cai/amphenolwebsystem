package com.amphenol.agis.controller;

import java.util.List;

import com.amphenol.agis.model.ResourceModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;

public class ResourceModelController extends Controller 
{
	public void index()
	{
		
	}
	
	public void create()
	{
		ResourceModel res=getModel(ResourceModel.class);
		res.set("id", getParaToLong("resid"));
		res.set("name", getPara("resname"));
		res.set("type", getPara("restype"));
		res.set("url",getPara("resurl"));
		res.set("parent_id",getPara("parent_id"));
		res.set("parent_ids", getPara("parent_ids"));
		res.set("permission", getPara("permission"));
		res.set("available", true);
		try
		{
			res.save();
			setAttr("status","创建成功");
		}
		catch(ActiveRecordException e)
		{
			setAttr("status","创建失败");
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
	
	public void delete()
	{
		if(ResourceModel.dao.deleteById(getParaToLong("resid")))
		{
			setAttr("status","删除成功");
		}
		else
		{
			setAttr("status","删除失败");
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

}
