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
	
	//2015-2-11 更新获取所有的
	public List<PkgModel> findNoProgramAndNoVerify()
	{
		String sql = "select * from sys_pkg where hasprogram is not true or hasverify is not true or hastagin is not true";
		return find(sql);
	}
	
	public List<PkgModel> getNoEEPROMList()
	{
		String sql = "select * from sys_pkg where hasprogram = false or hasverify = false";
		return find(sql);
	}
	
	public List<PkgModel> getNoTagIn()
	{
		String sql = "select * from sys_pkg where hastagin = false";
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
