package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * 	 * table: sys_role
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| role         | varchar(100) | YES  |     | NULL    |                |
| description  | varchar(100) | YES  |     | NULL    |                |
| resource_ids | varchar(100) | YES  | MUL | NULL    |                |
| available    | tinyint(1)   | YES  |     | 0       |                |
+--------------+--------------+------+-----+---------+----------------+
 * 
 * @author rocky
 *
 */
public class RoleModel extends Model<RoleModel> 
{

	/**


	 * 
	 */
	private static final long serialVersionUID = -5430815974948529887L;

	public static RoleModel dao = new RoleModel();
	
	public List<RoleModel> findAll()
	{
		String sql="select * from sys_role";
		return RoleModel.dao.find(sql);
	}

	public List<Long> getResourceIdList()
	{
		List<Long> list=new ArrayList<Long>();
		String resIdsStr=getStr("resource_ids");
		String[] resIds=resIdsStr.split(",");
		for(String resId : resIds)
		{
			list.add(Long.valueOf(resId));
		}
		return list;
	}
	public Collection<String> getPermissionNameList() {
		List<String> list=new ArrayList<String>();
		for(Long resId : getResourceIdList())
		{
			ResourceModel res=ResourceModel.dao.findById(resId);
			if(res != null)
			{
				list.add(res.getStr("permission"));
			}
		}
		return list;
	}
}
