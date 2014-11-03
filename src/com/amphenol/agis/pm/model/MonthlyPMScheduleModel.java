package com.amphenol.agis.pm.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 * 
 * table sys_monthly_pm_schedule
 * +-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| equipmentID | varchar(100) | YES  | MUL | NULL    |                |
| year        | varchar(100) | YES  |     | NULL    |                |
| janPMDay    | varchar(100) | YES  |     | NULL    |                |
| febPMDay    | varchar(100) | YES  |     | NULL    |                |
| marPMDay    | varchar(100) | YES  |     | NULL    |                |
| aprPMDay    | varchar(100) | YES  |     | NULL    |                |
| mayPMDay    | varchar(100) | YES  |     | NULL    |                |
| junPMDay    | varchar(100) | YES  |     | NULL    |                |
| julPMDay    | varchar(100) | YES  |     | NULL    |                |
| augPMDay    | varchar(100) | YES  |     | NULL    |                |
| sepPMDay    | varchar(100) | YES  |     | NULL    |                |
| octPMDay    | varchar(100) | YES  |     | NULL    |                |
| novPMDay    | varchar(100) | YES  |     | NULL    |                |
| decPMDay    | varchar(100) | YES  |     | NULL    |                |
| isJanPM     | tinyint(1)   | YES  |     | NULL    |                |
| isFebPM     | tinyint(1)   | YES  |     | NULL    |                |
| isMarPM     | tinyint(1)   | YES  |     | NULL    |                |
| isAprPM     | tinyint(1)   | YES  |     | NULL    |                |
| isMayPM     | tinyint(1)   | YES  |     | NULL    |                |
| isJunPM     | tinyint(1)   | YES  |     | NULL    |                |
| isJulPM     | tinyint(1)   | YES  |     | NULL    |                |
| isAugPM     | tinyint(1)   | YES  |     | NULL    |                |
| isSepPM     | tinyint(1)   | YES  |     | NULL    |                |
| isOctPM     | tinyint(1)   | YES  |     | NULL    |                |
| isNovPM     | tinyint(1)   | YES  |     | NULL    |                |
| isDecPM     | tinyint(1)   | YES  |     | NULL    |                |
| contents    | varchar(500) | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class MonthlyPMScheduleModel extends Model<MonthlyPMScheduleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233211937759339749L;
	private String[] keys={"id","equipmentID","year","janPMDay","febPMDay","marPMDay","aprPMDay","mayPMDay","junPMDay",
			"julPMDay","augPMDay","sepPMDay","octPMDay","novPMDay","decPMDay"};
	
	public static MonthlyPMScheduleModel dao = new MonthlyPMScheduleModel();
	
	public List<MonthlyPMScheduleModel> findAll(){
		String sql = "select * from sys_monthly_pm_schedule";
		return find(sql);
	}
	
	public List<String> findMonthlyScheduleYears(){
		List<String> years=new ArrayList<String>();
		String sql = "select distinct year from sys_monthly_pm_schedule";
		List<MonthlyPMScheduleModel> list=find(sql);
		for(int i=0;i<list.size();i++){
			years.add(list.get(i).getStr("year"));
		}
		return years;
	}
	
	public List<String> findEquipmentIDsByYear(String year){
		List<String> equipmentIDs=new ArrayList<String>();
		String sql = "select equipmentID from sys_monthly_pm_schedule where year = ?";
		List<MonthlyPMScheduleModel> list=find(sql, year);
		for(int i=0;i<list.size();i++){
			equipmentIDs.add(list.get(i).getStr("equipmentID"));
		}
		return equipmentIDs;
	}
	
	public MonthlyPMScheduleModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_monthly_pm_schedule where equipmentID=?";
		return findFirst(sql,equipmentID);
	}
	
	public MonthlyPMScheduleModel findByEquipmentIDAndYear(String equipmentID,String year){
		String sql = "select * from sys_monthly_pm_schedule where equipmentID=? and year=?";
		return findFirst(sql,equipmentID,year);
	}
	
	public Page<MonthlyPMScheduleModel> paginate(int pageNumber , int pageSize){
		return paginate(pageNumber, pageSize,"select *","from sys_monthly_pm_schedule order by id asc");
	}
	
	public Page<MonthlyPMScheduleModel> paginateWithOrder(int pageNumber , int pageSize ,String order){
		return paginate(pageNumber, pageSize,"select *","from sys_monthly_pm_schedule "+order+" by id asc");
	}
	
	public Page<MonthlyPMScheduleModel> paginateByWords(int pageNumber , int pageSize ,String words){
		StringBuilder sql=new StringBuilder("where 1=2 ");
		for(int i=0;i<keys.length;i++){
			sql.append(" or ").append(keys[i]).append(" like '%").append(words).append("%' ");
		}
		return paginate(pageNumber,pageSize,"select *","from sys_monthly_pm_schedule "+sql.toString()+"order by id asc");
	}
	
	public Page<MonthlyPMScheduleModel> paginateByKeyWords(int pageNumber,int pageSize,String key,String words){
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_monthly_pm_schedule "+sql.toString()+"order by id asc");
	}
}
