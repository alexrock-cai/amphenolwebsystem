package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class EepLogModel extends Model<EepLogModel> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1474309892323752589L;
	public static EepLogModel dao = new EepLogModel();
	
	public List<EepLogModel> findAll()
	{
		String sql = "select * from sys_eep_log";
		return EepLogModel.dao.find(sql);
	}
	
}
