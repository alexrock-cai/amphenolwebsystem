package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class ShipdataModel extends Model<ShipdataModel>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8162740926584605956L;
	public static ShipdataModel dao = new ShipdataModel();
	
	public List<ShipdataModel> findAll()
	{
		String sql ="select * from sys_shipdata";
		return ShipdataModel.dao.find(sql);
	}
	
	public ShipdataModel findByCustomerSn(String sn)
	{
		String sql ="select * from sys_shipdata where customer_sn = ?";
		return ShipdataModel.dao.findFirst(sql,sn);
	}
	
	public List<ShipdataModel> getNoEEPROMList()
	{
		String sql = "select * from sys_shipdata where hasprogram = false or hasverify= false";
		return ShipdataModel.dao.find(sql);
	}
}
