package com.amphenol.agis.pm.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class PMRecordModel extends Model<PMRecordModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773709589275569250L;
	private String[] keys={"id","equipmentID","PMOperator","PMType","PMTime","uploadBy","uploadTime","file"};
	
	public static PMRecordModel dao = new PMRecordModel();
	
	public List<PMRecordModel> findAll(){
		String sql = "select * from sys_pm_record";
		return find(sql);
	}
	
	public PMRecordModel findByEquipmentID(String equipmentID){
		String sql = "select * from sys_pm_record where equipmentID=?";
		return findFirst(sql, equipmentID);
	}
	

	
	public Page<PMRecordModel> paginate(int pageNumber , int pageSize){
		return paginate(pageNumber, pageSize,"select *","from sys_pm_record order by id asc");
	}
	
	public Page<PMRecordModel> paginateWithOrder(int pageNumber , int pageSize ,String order){
		return paginate(pageNumber, pageSize,"select *","from sys_pm_record "+order+" by id asc");
	}
	
	public Page<PMRecordModel> paginateByWords(int pageNumber , int pageSize ,String words){
		StringBuilder sql=new StringBuilder("where 1=2 ");
		for(int i=0;i<keys.length;i++){
			sql.append(" or ").append(keys[i]).append(" like '%").append(words).append("%' ");
		}
		return paginate(pageNumber,pageSize,"select *","from sys_pm_record "+sql.toString()+"order by id asc");
	}
	
	public Page<PMRecordModel> paginateByKeyWords(int pageNumber,int pageSize,String key,String words){
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_pm_record "+sql.toString()+"order by id asc");
	}
}
