package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class EcheckModel extends Model<EcheckModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -625436160724804614L;
	
	public static EcheckModel dao = new EcheckModel();
	
	public List<EcheckModel> findAll()
	{
		String sql = "select * from sys_echeck";
		return find(sql);
	}
	
	public Page<EcheckModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber,pageSize,"select *","from sys_echeck order by id desc");
	}
	
	public Page<EcheckModel> paginateByKeyWords(int pageNumber, int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_echeck "+sql.toString()+"order by id desc");
	}

	public Page<EcheckModel> getEcheckTimes(int pageNumber ,int pageSize)
	{
		return paginate(pageNumber,pageSize,"select distinct timestamp","from sys_echeck");
	}
}
