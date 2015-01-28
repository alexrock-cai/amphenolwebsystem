package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

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

	/**
	 * 增加分页显示
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<RoleModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber, pageSize, "select *", "from sys_role order by id asc");
	}
	
	/**
	 * 关键字查询分页显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @param words
	 * @return
	 */
	public Page<RoleModel> paginateByKeyWords(int pageNumber,int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" = '").append(words).append("' ");
		return paginate(pageNumber,pageSize,"select *","from sys_role "+sql.toString()+"order by id asc");
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Long> getResourceIdList()
	{
		List<Long> list=new ArrayList<Long>();
		String resIdsStr=getStr("resource_ids");
		if(!resIdsStr.trim().equals(""))
		{	
			String[] resIds=resIdsStr.split(",");
		
			for(String resId : resIds)
			{
				list.add(Long.valueOf(resId));
			}
		}
		return list;
	}
	
	public String getResourceNames()
	{
		StringBuilder names=new StringBuilder();
		for(Long resId: getResourceIdList())
		{
			ResourceModel r=ResourceModel.dao.findById(resId);
			if(r!=null)
			{
				names.append(r.getStr("name")).append(";");
			}
		}
		return names.toString();
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
	
	public RoleModel findByRole(String role)
	{
		String sql ="select * from sys_role where role=?";
		return findFirst(sql,role);
	}
	
	public boolean deleteByRole(String role)
	{
		if(findByRole(role)!=null)
		{
			return deleteById(findByRole(role).getLong("id"));
		}
		else
			return false;
	}
	
	public List<RoleModel> findAllWIRole()
	{
		String sql ="select * from sys_role where role like '%WIUser%'";
		return find(sql);
	}
	
	/**
	 * 根据关键字查询WI权限
	 * @param key
	 * @return
	 */
	public List<RoleModel> findWIRoleByString(String key){
		String sql = "select * from (select * from sys_role where role like '%WIUser%') as a where role like '%"+key+"%'";
		return find(sql);
	}
}
