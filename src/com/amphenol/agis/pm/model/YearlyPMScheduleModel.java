package com.amphenol.agis.pm.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


public class YearlyPMScheduleModel extends Model<YearlyPMScheduleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243551961304849612L;
	private String[] keys={"id","equipmentID","year","yearPMDay","isYearPM","contents"};

	public static YearlyPMScheduleModel dao = new YearlyPMScheduleModel();
	
	public List<YearlyPMScheduleModel> findAll(){
		String sql="select * from sys_yearly_pm_schedule";
		return find(sql);
	}
	
	public List<String> findYearlyScheduleYears(){
		List<String> years=new ArrayList<String>();
		String sql = "select distinct year from sys_yearly_pm_schedule";
		List<YearlyPMScheduleModel> list=find(sql);
		for(int i=0;i<list.size();i++){
			years.add(list.get(i).getStr("year"));
		}
		return years;
	}
	
	public List<String> findEquipmentIDsByYear(String year){
		List<String> equipmentIDs=new ArrayList<String>();
		String sql = "select equipmentID from sys_yearly_pm_schedule where year = ?";
		List<YearlyPMScheduleModel> list=find(sql, year);
		for(int i=0;i<list.size();i++){
			equipmentIDs.add(list.get(i).getStr("equipmentID"));
		}
		return equipmentIDs;
	}
	
	public YearlyPMScheduleModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_yearly_pm_schedule where equipmentID=?";
		return findFirst(sql, equipmentID);
	}
	
	public YearlyPMScheduleModel findByEquipmentIDAndYear(String equipmentID,String year){
		String sql = "select * from sys_yearly_pm_schedule where equipmentID=? and year=?";
		return findFirst(sql, equipmentID,year);
	}
	
	public Page<YearlyPMScheduleModel> paginate(int pageNumber , int pageSize){
		return paginate(pageNumber, pageSize,"select *","from sys_yearly_pm_schedule order by id asc");
	}
	
	public Page<YearlyPMScheduleModel> paginateWithOrder(int pageNumber , int pageSize ,String order){
		return paginate(pageNumber, pageSize,"select *","from sys_yearly_pm_schedule "+order+" by id asc");
	}
	
	public Page<YearlyPMScheduleModel> paginateByWords(int pageNumber , int pageSize ,String words){
		StringBuilder sql=new StringBuilder("where 1=2 ");
		for(int i=0;i<keys.length;i++){
			sql.append(" or ").append(keys[i]).append(" like '%").append(words).append("%' ");
		}
		return paginate(pageNumber,pageSize,"select *","from sys_yearly_pm_schedule "+sql.toString()+"order by id asc");
	}
	
	
	public Page<YearlyPMScheduleModel> paginateByKeyWords(int pageNumber,int pageSize,String key,String words){
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_yearly_pm_schedule "+sql.toString()+"order by id asc");
	}
	
}
