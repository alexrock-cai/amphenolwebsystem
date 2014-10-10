package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class PMRecordModel extends Model<PMRecordModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773709589275569250L;
	
	public static PMRecordModel dao = new PMRecordModel();
	
	public List<PMRecordModel> findAll(){
		String sql = "select * from sys_pm_record";
		return find(sql);
	}
	
	public PMRecordModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_pm_record where equipmentID=?";
		return findFirst(sql, equipmentID);
	}
}
