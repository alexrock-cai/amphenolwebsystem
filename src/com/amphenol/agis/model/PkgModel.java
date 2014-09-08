package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class PkgModel extends Model<PkgModel>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327515516802761387L;
	
	public static PkgModel dao = new PkgModel();
	
	public List<PkgModel> findAll()
	{
		String sql = "select * from sys_pkg order by id desc";
		return dao.find(sql);
	}
	
	public List<PkgModel> findNoProgramAndNoVerify()
	{
		String sql = "select * from sys_pkg where hasprogram is null or hasverify is null";
		return find(sql);
	}
	
	public PkgModel findByCustomerSn(String sn)
	{
		String sql = "select * from sys_pkg where customer_sn = ?";
		return dao.findFirst(sql, sn);
	}
	
	public Page<PkgModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber,pageSize,"select *","from sys_pkg order by id desc");
	}
}