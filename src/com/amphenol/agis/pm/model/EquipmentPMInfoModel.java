package com.amphenol.agis.pm.model;

import java.util.ArrayList;
import java.util.List;


import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * table sys_equipment_pm_info
 * +-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| id              | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| equipmentID     | varchar(100) | NO   | UNI | NULL    |                |
| model           | varchar(100) | YES  |     | NULL    |                |
| equipmentName   | varchar(100) | YES  |     | NULL    |                |
| owner           | varchar(100) | YES  |     | NULL    |                |
| ownerEmail      | varchar(200) | YES  |     | NULL    |                |
| supervisor      | varchar(100) | YES  |     | NULL    |                |
| supervisorEmail | varchar(200) | YES  |     | NULL    |                |
| isMonthlyPM     | tinyint(1)   | YES  |     | NULL    |                |
| isQuerterlyPM   | tinyint(1)   | YES  |     | NULL    |                |
| isYearlyPM      | tinyint(1)   | YES  |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class EquipmentPMInfoModel extends Model<EquipmentPMInfoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040346885185758972L;
	private String[] keys={"id","equipmentID","model","equipmentName",
			"owner","ownerEmail","supervisor","supervisorEmail","isMonthlyPM","isQuarterlyPM","isYearlyPM"};  
	
	public static EquipmentPMInfoModel dao = new EquipmentPMInfoModel();
	
	public List<EquipmentPMInfoModel> findAll(){
		String sql="select * from sys_equipment_pm_info";
		return find(sql);
	}
	
	public EquipmentPMInfoModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_equipment_pm_info where equipmentID=?";
		return findFirst(sql, equipmentID);
	}
	
	public List<String> findEquipmentIDs(String pmtype){
		String sql = "select equipmentID from sys_equipment_pm_info where "+pmtype+" = true";
		List<EquipmentPMInfoModel> list= find(sql);
		List<String> equipmentIDs=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			equipmentIDs.add(list.get(i).getStr("equipmentID"));
		}
		return equipmentIDs;
	}
	
	public List<String> findEquipmentIDs(){
		String sql = "select equipmentID from sys_equipment_pm_info ";
		List<EquipmentPMInfoModel> list= find(sql);
		List<String> equipmentIDs=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			equipmentIDs.add(list.get(i).getStr("equipmentID"));
		}
		return equipmentIDs;
	}
	public Page<EquipmentPMInfoModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber, pageSize,"select *","from sys_equipment_pm_info order by id asc");
	}
	
	public Page<EquipmentPMInfoModel> paginateWithOrder(int pageNumber , int pageSize ,String order)
	{
		return paginate(pageNumber, pageSize,"select *","from sys_equipment_pm_info order by "+order+" asc");
	}
	
	public Page<EquipmentPMInfoModel> paginateByWords(int pageNumber , int pageSize ,String words){
		StringBuilder sql=new StringBuilder("where 1=2 ");
		for(int i=0;i<keys.length;i++){
			sql.append(" or ").append(keys[i]).append(" like '%").append(words).append("%' ");
		}
		return paginate(pageNumber,pageSize,"select *","from sys_equipment_pm_info "+sql.toString()+"order by id asc");
	}
	
	public Page<EquipmentPMInfoModel> paginateByKeyWords(int pageNumber,int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_equipment_pm_info "+sql.toString()+"order by id asc");
	}
	

}
