package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class MonthlyPMScheduleModel extends Model<MonthlyPMScheduleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233211937759339749L;
	
	public static MonthlyPMScheduleModel dao = new MonthlyPMScheduleModel();
	
	public List<MonthlyPMScheduleModel> findAll(){
		String sql = "select * from sys_monthly_pm_schedule";
		return find(sql);
	}
	
	public MonthlyPMScheduleModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_monthly_pm_schedule where equipmentID=?";
		return findFirst(sql,equipmentID);
	}

}
