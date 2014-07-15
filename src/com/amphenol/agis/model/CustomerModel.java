package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 *
 * 
 * table sys_customer;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| id            | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| customer_name | varchar(100) | YES  |     | NULL    |                |
| country       | varchar(50)  | YES  |     | NULL    |                |
| contact       | varchar(50)  | YES  |     | NULL    |                |
| email         | varchar(100) | YES  |     | NULL    |                |
| csr           | varchar(100) | YES  |     | NULL    |                |
| pm            | varchar(100) | YES  |     | NULL    |                |
| pe            | varchar(100) | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class CustomerModel extends Model<CustomerModel>
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2025986117795279493L;
	public static CustomerModel dao = new CustomerModel();
	
	public List<CustomerModel> findAll()
	{
		String sql="select * from sys_customer";
		return CustomerModel.dao.find(sql);
	}
}
