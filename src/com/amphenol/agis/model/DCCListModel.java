package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 表 sys_dcc
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| customer   | varchar(100) | YES  |     | NULL    |                |
| pn         | varchar(100) | YES  |     | NULL    |                |
| type       | varchar(50)  | YES  |     | NULL    |                |
| rev        | varchar(50)  | YES  |     | NULL    |                |
| filepath   | varchar(100) | YES  |     | NULL    |                |
| filename   | varchar(100) | YES  | MUL | NULL    |                |
| lastmodify | varchar(100) | YES  |     | NULL    |                |
| operate    | varchar(100) | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
 * @author rocky
 *
 */
public class DCCListModel extends Model<DCCListModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297483891924775352L;
	
	public static DCCListModel dao = new DCCListModel();
	
	public List<DCCListModel> findAll()
	{
		String sql="select * from sys_dcc";
		return DCCListModel.dao.find(sql);
	}

	public List<DCCListModel> findByCustomer(String customer)
	{
		String sql="select * from sys_dcc where customer = ?";
		return DCCListModel.dao.find(sql, customer);
	}
	
	public List<DCCListModel> findByCustomer(List<String> customerList)
	{
		List<DCCListModel> list = new ArrayList<DCCListModel>();
		for(String customer : customerList)
		{
			list.addAll(DCCListModel.dao.findByCustomer(customer));
		}
		
		return list;
	}
	/**
	 * 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<DCCListModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber,pageSize,"select *","from sys_dcc order by id asc");
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @param words
	 * @return
	 */
	public Page<DCCListModel> paginateByKeyWords(int pageNumber, int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" = '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @param words
	 * @param orderDirection
	 * @param orderField
	 * @return
	 */
	public Page<DCCListModel> paginateByKeyWords(int pageNumber , int pageSize ,String key ,String words ,String orderDirection , String orderField)
	{
		StringBuilder sql=new StringBuilder("where 1=1 and ");
		sql.append(key).append(" = '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by "+orderField+" "+orderDirection);
	}
}
