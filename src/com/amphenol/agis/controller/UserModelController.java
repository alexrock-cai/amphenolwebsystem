package com.amphenol.agis.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import com.amphenol.agis.model.ResourceModel;
import com.amphenol.agis.model.RoleModel;
import com.amphenol.agis.model.StationModel;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.pojo.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * sys_user 表进行 CRUD 操作类
 * sys_station 表进行CRUD 操作控制类
 * @author rocky
 *
 */
@RequiresAuthentication
public class UserModelController extends Controller 
{
	public void index()
	{
		render("hello User");
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void openUserForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/user/usercreate.jsp");
		}
		else
		{
			setAttr("u", new User(UserModel.dao.findById(getParaToLong("id"))));
			render("/dwzpage/user/useredit.jsp");
		}
	}
	/**
	 * 修改密码
	 */
	public void changepwd()
	{
		UserModel user=(UserModel)getSessionAttr("user");
		user.set("password", getPara("password"));
		try
		{
			user.update();
			setAttr("statusCode", "200");
			setAttr("message","修改密码成功");
			setAttr("callbackType","closeCurrent");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			setAttr("statusCode", "300");
			setAttr("message","修改密码失败");
		}
		renderJson();
	}
	/**
	 * 新建用户
	 */
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void create()
	{
		UserModel u=getModel(UserModel.class);
		u.set("username", getPara("username"));
		u.set("password",getPara("password"));
		u.set("name", getPara("name"));
		u.set("organization_id", 1);
		u.set("role_ids",getPara("roleLookup.id"));
		try{
			u.save();
			setAttr("statusCode", "200");
			setAttr("message","创建成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","user_list");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","创建失败");
		}
		
		renderJson();
	}
	/**
	 * 更新用户
	 */
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void update()
	{
		UserModel u=UserModel.dao.findById(getPara("id"));
		System.out.println(getPara("username"));
		u.set("username", getPara("username"));
		u.set("name", getPara("name"));
		u.set("password",getPara("password"));
		u.set("organization_id", 1);
		u.set("role_ids", getPara("roleLookup.id"));
		try{
			u.update();
			setAttr("statusCode", "200");
			setAttr("message","更新成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","user_list");
		}
		catch(ActiveRecordException e)
		{
			e.printStackTrace();
			setAttr("statusCode", "300");
			setAttr("message","更新失败");
		}
		renderJson();
	}
	/**
	 * 删除用户
	 */
	@RequiresRoles("root")
	public void delete()
	{
		if(getParaToLong("id")==1)
		{
			setAttr("statusCode", "300");
			setAttr("message","root 账号不能删除");
		}
		else
		{	
			if(UserModel.dao.deleteById(getParaToLong("id")))
			{
				setAttr("statusCode", "200");
				setAttr("message","删除成功");
				setAttr("navTabId","user_list");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","删除失败");
			}
		}
		renderJson();
	}
	/**
	 * 查看用户
	 */
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void view()
	{
		List<UserModel> list=UserModel.dao.findAll();
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void userView()
	{
		
		getUser();
		renderJsp("/dwzpage/user/userlist.jsp");
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	private void getUser()
	{
		List<User> list=new ArrayList<User>();
		Subject curretUser= SecurityUtils.getSubject();
		Page<UserModel> pages=UserModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		for(UserModel u: pages.getList())
		{
			if(u.getStr("username").equals("root"))
			{
				if(curretUser.hasRole("root"))
				{
					list.add(new User(u));
				}
				else
				{
					continue;
				}
			}
			else
			{
				list.add(new User(u));
			}
			
			
		}
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("userlist",list);
	}
	/**
	 * 站别设置
	 */
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void stationView()
	{
		
		Page<StationModel> pages= StationModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"));
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		setAttr("stationlist",pages.getList());
		
		render("/dwzpage/user/stationlist.jsp");
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void openStationForm()
	{
		if(getPara("id")==null)
		{
			render("/dwzpage/user/stationcreate.jsp");
		}
		else
		{
			setAttr("s", StationModel.dao.findById(getParaToLong("id")));
			render("/dwzpage/user/stationedit.jsp");
		}
	}
	@Before(Tx.class)
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void stationCreate()
	{
		StationModel station = new StationModel();
		station.set("station", getPara("station").toUpperCase());
		station.set("discription",getPara("discription"));
		ResourceModel res= new ResourceModel();
		res.set("name",getPara("discription") );
		res.set("permission", "wi:"+getPara("station").toUpperCase());
		res.set("parent_id", 61);
		res.set("parent_ids","0/1/61");
		res.set("type","button");
		RoleModel role = new RoleModel();
		role.set("role", "WIUser:"+getPara("station").toUpperCase());
		role.set("description",getPara("discription"));
		
		
		if(station.save())
		{
			if(res.save())
			{
				//System.out.println(res.getLong("id"));
				role.set("resource_ids",res.getLong("id"));
				role.save();
				setAttr("statusCode", "200");
				setAttr("message","创建成功");
				setAttr("callbackType","closeCurrent");
				setAttr("navTabId","station_list");
			}
			else
			{
				station.delete();
				setAttr("statusCode", "300");
				setAttr("message","创建失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","创建失败");
		}
		
		renderJson();
		
	}
	@Before(Tx.class)
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void deleteStation()
	{
		StationModel s=StationModel.dao.findById(getParaToLong("id"));
		if(s!=null)
		{
			if(ResourceModel.dao.deleteByPermission("wi:"+s.getStr("station")))
			{
				RoleModel.dao.deleteByRole("WIUser:"+s.getStr("station"));
				if(StationModel.dao.deleteById(getParaToLong("id")))
				{
					setAttr("statusCode", "200");
					setAttr("message","删除成功");
					setAttr("navTabId","station_list");
				}
				else
				{
					setAttr("statusCode", "300");
					setAttr("message","删除失败");
				}
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","删除失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
		}
		
		renderJson();
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void stationUpdate()
	{
		StationModel s=StationModel.dao.findById(getParaToLong("id"));
		ResourceModel res=ResourceModel.dao.findByPermission("wi:"+s.getStr("station"));
		if(s!=null&res!=null)
		{
			s.set("station", getPara("station").toUpperCase());
			s.set("discription", getPara("discription"));
			
			res.set("name",getPara("discription") );
			res.set("permission", "wi:"+getPara("station").toUpperCase());
			
			if(s.update() & res.update())
			{
				setAttr("statusCode", "200");
				setAttr("message","保存成功");
				setAttr("callbackType","closeCurrent");
				setAttr("navTabId","station_list");
			}
			else
			{
				setAttr("statusCode", "300");
				setAttr("message","删除失败");
			}
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","删除失败");
		}
		renderJson();
	}
	@RequiresRoles(value={"root","admin_leader","admin_widatauser"},logical=Logical.OR)
	public void authenticationDialog()
	{
		UserModel user= UserModel.dao.findById(getParaToLong("id"));
		List<RoleModel> roles= RoleModel.dao.findAllWIRole();
		setAttr("u",user);
		setAttr("roles",roles);
		setAttr("roleids",user.getRoleIdList());
		render("/dwzpage/user/authenticationuser.jsp");
		
	}
	@RequiresRoles("root,admin_leader,admin_widatauser")
	public void saveAuthentica()
	{
		UserModel user= UserModel.dao.findById(getParaToLong("id"));
		StringBuilder roleids=new StringBuilder();
		roleids.append("2");
		if(getParaValues("role_ids")!=null)
		{
			for(String roleid : getParaValues("role_ids"))
			{
				roleids.append(",").append(roleid);
			}
		}
		user.set("role_ids", roleids.toString());
		if(user.update())
		{
			setAttr("statusCode", "200");
			setAttr("message","保存成功");
			setAttr("callbackType","closeCurrent");
			setAttr("navTabId","user_list");
		}
		else
		{
			setAttr("statusCode", "300");
			setAttr("message","保存失败");
		}
		renderJson();
	}
}
