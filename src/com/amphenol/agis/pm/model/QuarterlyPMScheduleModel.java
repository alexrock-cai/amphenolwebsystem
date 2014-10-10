package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class QuarterlyPMScheduleModel extends Model<QuarterlyPMScheduleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4547693931304590241L;
	
	public static QuarterlyPMScheduleModel dao = new QuarterlyPMScheduleModel();
	
	public List<QuarterlyPMScheduleModel> findAll(){
		String sql="select * from sys_quarterly_pm_schedule";
		return find(sql);
	}
	
	public QuarterlyPMScheduleModel findByEquipmentID(String equipmentID){
		String sql="select * from sys_quarterly_pm_schedule where equipmentID=?";
		return findFirst(sql, equipmentID);
	}

}
