package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class WOModel extends Model<WOModel> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8383881457205381507L;
	public static WOModel dao = new WOModel();
	
	public List<WOModel> findAll()
	{
		String sql = "select * from sys_wo";
		return WOModel.dao.find(sql);
	}
}
