package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * table name sys_eep_program
 * 
+-------------+--------------+------+-----+---------+----------------+
| 
Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| customer_sn | varchar(100) | YES  | MUL | NULL    |                |
| product_id  | bigint(20)   | YES  |     | NULL    |                |
| timestamp   | varchar(100) | YES  |     | NULL    |                |
| status      | tinyint(1)   | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */

public class ProgramModel extends Model<ProgramModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1009744279733513096L;
	
	public static ProgramModel dao = new ProgramModel();
	
	public List<ProgramModel> findAll()
	{
		String sql = "select * from sys_eep_program";
		return ProgramModel.dao.find(sql);
	}
	
	public ProgramModel findByCustomerSn(String sn)
	{
		String sql ="select * from sys_eep_program where customer_sn = ?";
		return ProgramModel.dao.findFirst(sql, sn);
	}
	
	public Page<ProgramModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber,pageSize,"select *","from sys_eep_program order by id desc");
	}
	
	public Page<ProgramModel> paginateByKeyWords(int pageNumber, int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_eep_program "+sql.toString()+"order by id desc");
	}
}
