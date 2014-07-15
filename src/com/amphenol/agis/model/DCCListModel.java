package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 表 sys_dcc
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| customer   | varchar(100) | YES  |     | NULL    |                |
| pn         | varchar(100) | YES  |     | NULL    |                |
| type       | varchar(50)  | YES  |     | NULL    |                |
| rev        | varchar(50)  | YES  |     | NULL    |                |
| filepath   | varchar(100) | YES  |     | NULL    |                |
| filename   | varchar(100) | YES  | MUL | NULL    |                |
| lastmodify | varchar(100) | YES  |     | NULL    |                |
| operate    | varchar(100) | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class DCCListModel extends Model<DCCListModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297483891924775352L;
	
	public static DCCListModel dao = new DCCListModel();
	
	public List<DCCListModel> findAll()
	{
		String sql="select * from sys_dcc";
		return DCCListModel.dao.find(sql);
	}

	public List<DCCListModel> findByCustomer(String customer)
	{
		String sql="select * from sys_dcc where customer = ?";
		return DCCListModel.dao.find(sql, customer);
	}
	
	public List<DCCListModel> findByCustomer(List<String> customerList)
	{
		List<DCCListModel> list = new ArrayList<DCCListModel>();
		for(String customer : customerList)
		{
			list.addAll(DCCListModel.dao.findByCustomer(customer));
		}
		
		return list;
	}
}
