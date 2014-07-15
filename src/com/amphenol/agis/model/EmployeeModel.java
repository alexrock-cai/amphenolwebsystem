package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * table sys_employee
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| emp_number   | varchar(50)  | YES  |     | NULL    |                |
| name         | varchar(100) | YES  |     | NULL    |                |
| en_name      | varchar(100) | YES  |     | NULL    |                |
| gender       | varchar(10)  | YES  |     | NULL    |                |
| job          | varchar(100) | YES  |     | NULL    |                |
| title        | varchar(50)  | YES  |     | NULL    |                |
| dept_name    | varchar(50)  | YES  |     | NULL    |                |
| dept_number  | varchar(50)  | YES  |     | NULL    |                |
| leader_id    | bigint(20)   | YES  |     | NULL    |                |
| desk_phone   | varchar(50)  | YES  |     | NULL    |                |
| moblie_phone | varchar(50)  | YES  |     | NULL    |                |
| email        | varchar(100) | YES  |     | NULL    |                |
| available    | tinyint(1)   | YES  |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */

public class EmployeeModel extends Model<EmployeeModel>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6646182621970946163L;
	public static EmployeeModel dao = new EmployeeModel();
	
	public List<EmployeeModel> findAll()
	{
		String sql="select * from sys_employee";
		return EmployeeModel.dao.find(sql);
	}
}
