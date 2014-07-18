package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * table sys_eep_config
 * 
 *存储EEPROM 检查程序扫描文件的根路径，默认扫描文件从此路径开始
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| id       | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| filepath | varchar(500) | YES  |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class EepConfigModel extends Model<EepConfigModel> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134377216080521590L;
	public static EepConfigModel dao = new EepConfigModel();
	
	public List<EepConfigModel> findAll()
	{
		String sql="select * from sys_eep_config";
		return EepConfigModel.dao.find(sql);
	}
	
	public String getHandledPath()
	{
		String sql="select * from sys_eep_config where function = 'handled' ";
		return EepConfigModel.dao.findFirst(sql).getStr("filepath");
		
	}
	
	public String getWorkPath()
	{
		String sql= "select * from sys_eep_config where function = 'work'";
		return EepConfigModel.dao.findFirst(sql).getStr("filepath");
	}
}
