package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.plugin.activerecord.Model;
/**
 * table sys_user
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| id              | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| organization_id | bigint(20)   | YES  | MUL | NULL    |                |
| username        | varchar(100) | YES  | UNI | NULL    |                |
| password        | varchar(100) | YES  |     | NULL    |                |
| salt            | varchar(100) | YES  |     | NULL    |                |
| role_ids        | varchar(100) | YES  |     | NULL    |                |
| locked          | tinyint(1)   | YES  |     | 0       |                |
+-----------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class UserModel extends Model<UserModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5104436400037984042L;
	
	public static UserModel dao=new UserModel();
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return UserModel
	 */
	public UserModel findByName(String username)
	{
		String sql="select * from sys_user where username=?";
		return UserModel.dao.findFirst(sql,username);
	}
	/**
	 * 查找所有的用户
	 * @return List<UserModel>
	 */
	public List<UserModel> findAll()
	{
		String sql="select * from sys_user";
		return UserModel.dao.find(sql);
	}
	/**
	 * 获取该用户的角色ID
	 * @return
	 */
	public List<Long> getRoleIdList() {
		List<Long> list=new ArrayList<Long>();
		String roleIdsStr=getStr("role_ids");
		String[] roleIds=roleIdsStr.split(",");
		for(String roleId : roleIds)
		{
			list.add(Long.valueOf(roleId));
		}
		return list;
	}
	/**
	 * 获取该用户的角色名称
	 * @return
	 */
	public Set<String>  getRoleNameList()
	{
		Set<String> roleNameList=new HashSet<String>();
		for(Long roleId : getRoleIdList())
		{
			RoleModel role=RoleModel.dao.findById(roleId);
			if(role!=null)
				roleNameList.add(role.getStr("role"));
		}
		return roleNameList;
	}
	/**
	 * 获取该用户的角色
	 * @return
	 */
	public List<RoleModel> getRoleList()
	{
		List<RoleModel> roleList = new ArrayList<RoleModel>();
		for(Long roleId : getRoleIdList())
		{
			RoleModel role=RoleModel.dao.findById(roleId);
			if(role!=null)
				roleList.add(role);
		}
		return roleList;
	}
	/**
	 * 获取该用户的权限清单
	 * @return
	 */
	public List<String> getPermissionNameList()
	{
		List<String> list = new ArrayList<String>();
		for(Long resId : getResourceIdList())
		{
			ResourceModel res=ResourceModel.dao.findById(resId);
			if(res!=null)
			{
				list.add(res.getStr("permission"));
			}
		}
		return list;
	}
	/**
	 * 获取该用户可访问资源ID
	 * @return
	 */
	public List<Long> getResourceIdList()
	{
		List<Long> resIds=new ArrayList<Long>();
		for(RoleModel role : getRoleList())
		{
			for(Long resId : role.getResourceIdList())
			{
				resIds.add(resId);
			}
		}
		
		return resIds;
	}
	
	public String getResourceNames()
	{
		StringBuilder names=new StringBuilder();
		for(Long resId : getResourceIdList())
		{
			ResourceModel res=ResourceModel.dao.findById(resId);
			if(res!=null)
			{
				names.append(res.getStr("name"));
			}
		}
		
		return names.toString();
	}
}
