package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class EepConfigModel extends Model<EepConfigModel> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134377216080521590L;
	public static EepConfigModel dao = new EepConfigModel();
	
	public List<EepConfigModel> findAll()
	{
		String sql="select * from sys_eep_config";
		return EepConfigModel.dao.find(sql);
	}
}
