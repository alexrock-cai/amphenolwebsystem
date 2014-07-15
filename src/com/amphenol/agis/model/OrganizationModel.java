package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * table: sys_organization.
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| name       | varchar(100) | YES  |     | NULL    |                |
| parent_id  | bigint(20)   | YES  | MUL | NULL    |                |
| parent_ids | varchar(100) | YES  | MUL | NULL    |                |
| available  | tinyint(1)   | YES  |     | 0       |                |
+------------+--------------+------+-----+---------+----------------+

 * @author rocky
 *
 */

public class OrganizationModel extends Model<OrganizationModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3365359036801866222L;

	public static OrganizationModel dao = new OrganizationModel();
	
	public List<OrganizationModel> findAll()
	{
		String sql="select * from sys_organization";
		return OrganizationModel.dao.find(sql);
	}
}
