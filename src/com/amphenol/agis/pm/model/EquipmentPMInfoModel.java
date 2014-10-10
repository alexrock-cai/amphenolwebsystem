package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;


public class EquipmentPMInfoModel extends Model<EquipmentPMInfoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040346885185758972L;
	
	public static EquipmentPMInfoModel dao = new EquipmentPMInfoModel();
	
	public List<EquipmentPMInfoModel> findAll(){
		String sql="select * from sys_equipment_pm_info";
		return find(sql);
	}
	
	public EquipmentPMInfoModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_equipment_pm_info where equipmentID=?";
		return findFirst(sql, equipmentID);
	}

}
