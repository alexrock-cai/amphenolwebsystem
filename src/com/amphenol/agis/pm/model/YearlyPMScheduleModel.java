package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;


public class YearlyPMScheduleModel extends Model<YearlyPMScheduleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243551961304849612L;

	public static YearlyPMScheduleModel dao = new YearlyPMScheduleModel();
	
	public List<YearlyPMScheduleModel> findAll(){
		String sql="select * from sys_yearly_pm_schedule";
		return find(sql);
	}
	
	public YearlyPMScheduleModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_yearly_pm_schedule where equipmentID=?";
		return findFirst(sql, equipmentID);
	}
}
