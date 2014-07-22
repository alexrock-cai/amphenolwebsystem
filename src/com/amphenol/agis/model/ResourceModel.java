package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * table: sys_resource
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| name       | varchar(100) | YES  |     | NULL    |                |
| type       | varchar(50)  | YES  |     | NULL    |                |
| url        | varchar(200) | YES  |     | NULL    |                |
| parent_id  | bigint(20)   | YES  | MUL | NULL    |                |
| parent_ids | varchar(100) | YES  | MUL | NULL    |                |
| permission | varchar(100) | YES  |     | NULL    |                |
| available  | tinyint(1)   | YES  |     | 0       |                |
+------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */

public class ResourceModel extends Model<ResourceModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823863819605328778L;
	
	public static ResourceModel dao=new ResourceModel();

	public List<ResourceModel> findAll() {
		String sql="select * from sys_resource";
		return ResourceModel.dao.find(sql);
	}

}