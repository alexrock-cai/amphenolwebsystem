package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 * 2015-1-31 新增加
 * 记录用户WI认证信息的数据库
字段说明：
userId 用户表中的ID，与用户表建立关联
dccId  WI文档路径表ID，与Wi文档建立关联
certTime  记录认证时间
validity  认证有效期
certUser  认证人员ID，
 * @author rocky
 *
 */
public class UserDccCertModel extends Model<UserDccCertModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4898441517150737234L;
	
	public static UserDccCertModel dao = new UserDccCertModel();
	
	public List<UserDccCertModel> findAll(){
		String sql = "select * from sys_dcc_user_cert";
		return find(sql);
	}
	
	public List<UserDccCertModel> findByUserId(Long userid){
		String sql = "select * from sys_dcc_user_cert where available is true and userId="+userid;
		return find(sql);
	}
	
	public List<UserDccCertModel> findAllByUserId(Long userid){
		String sql = "select * from sys_dcc_user_cert where userId="+userid;
		return find(sql);
	}
	public List<UserDccCertModel> findByDccId(Long dccid){
		String sql = "select * from sys_dcc_user_cert where available is true and dccId="+dccid;
		return find(sql);
	}
	
	public List<UserDccCertModel> findByCertTime(String certTime){
		String sql = "select * from sys_dcc_user_cert where available is true and certTime='"+certTime+"'";
		return find(sql);
	}
	
	public Page<UserDccCertModel> paginate(int pageNumber,int pageSize){
		return paginate(pageNumber,pageSize,"select *","from sys_dcc_user_cert order by id asc");
	}
	
	public Page<UserDccCertModel> paginateByKeyWord(int pageNumber,int pageSize,String key,String word){
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" = '").append(word).append("' ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc_user_cert "+sql.toString()+"order by id asc");
	}
	
	public Page<UserDccCertModel> paginateByUserId(int pageNumber,int pageSize,long id){
		StringBuilder sql= new StringBuilder("where available is true and ");
		sql.append("userId").append(" = ").append(id).append(" ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc_user_cert "+sql.toString()+"order by id asc");
	}
	
	public Page<UserDccCertModel> paginateAllByUserId(int pageNumber,int pageSize,long id){
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append("userId").append(" = ").append(id).append(" ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc_user_cert "+sql.toString()+"order by id asc");
	}
	public List<DCCListModel> paginateDccList(int pageNumber,int pageSize,long userid){
		Page<UserDccCertModel> userDccPage = paginateByUserId(pageNumber, pageSize, userid);
		List<Long> ids = new ArrayList<Long>();
		for(UserDccCertModel userDccCertModel : userDccPage.getList()){
			ids.add(userDccCertModel.getLong("dccId"));
		}
		return DCCListModel.dao.findByIds(ids);
	}
	
	/**
	 * 根据关键字查询并返回WI数据
	 * @param pageNumber
	 * @param pageSize
	 * @param userid
	 * @param key
	 * @param words
	 * @return
	 */
	public List<DCCListModel> findDccBykeyWords(int pageNumber,int pageSize,long userid,String key,String words){
		Page<UserDccCertModel> userDccPage = paginateByUserId(pageNumber, pageSize, userid);
		List<Long> ids = new ArrayList<Long>();
		for(UserDccCertModel userDccCertModel : userDccPage.getList()){
			ids.add(userDccCertModel.getLong("dccId"));
		}
		
		return DCCListModel.dao.findByIdsAndKeyWords(ids, key, words);
	}
	
	/**
	 * 2015-2-1
	 * @param pageNumber
	 * @param pageSize
	 * @param userid
	 * @param key
	 * @param words
	 * @return Page<DCCListModel>
	 */
	public Page<DCCListModel> paginateDccBykeyWords(int pageNumber,int pageSize,long userid,String key,String words){
		List<UserDccCertModel> userDccCertModels = findByUserId(userid);
		List<Long> ids = new ArrayList<Long>();
		for(UserDccCertModel userDccCertModel : userDccCertModels){
			ids.add(userDccCertModel.getLong("dccId"));
		}
		return DCCListModel.dao.paginateByIdAndKeyWords(pageNumber, pageSize, ids, key, words);
	}
}
