package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 *
 * table sys_eep_verify
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| customer_sn | varchar(100) | YES  | MUL | NULL    |                |
| timestamp   | varchar(100) | YES  |     | NULL    |                |
| status      | tinyint(1)   | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class VerifyModel extends Model<VerifyModel>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609626559621320046L;
	
	public static VerifyModel dao = new VerifyModel();
	
	public List<VerifyModel> findAll()
	{
		String sql = "select * from sys_eep_verify";
		return VerifyModel.dao.find(sql);
	}
	
	public VerifyModel findByCustomerSn(String sn)
	{
		String sql="select * from sys_eep_verify where customer_sn = ?";
		return VerifyModel.dao.findFirst(sql,sn);
	}
}
