package com.amphenol.agis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public List<DCCListModel> getCustomerList()
	{
		String sql = "select distinct customer from sys_dcc ";
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
	public DCCListModel findByStationAndPn(String station,String pn){
		String sql = "select * from sys_dcc where station='"+station+"' and pn='"+pn+"'";
		return findFirst(sql);
	}
	/**
	 * 2015-1-31新增加根据ID查找。
	 * @param ids
	 * @return
	 */
	public List<DCCListModel> findByIds(List<Long> ids){
		List<DCCListModel> list = new ArrayList<DCCListModel>();
		for(Long id : ids){
			list.add(DCCListModel.dao.findById(id));
		}
		return list;
	}
	/**
	 * 2015-1-31 新增加根据ID和关键字查询WI
	 * @return
	 */
	public List<DCCListModel> findByIdsAndKeyWords(List<Long> ids,String key,String words){
		List<DCCListModel> list = new ArrayList<DCCListModel>();
		Pattern pattern = Pattern.compile(words);
		for(DCCListModel dccListModel : findByIds(ids)){
			Matcher matcher = pattern.matcher(dccListModel.getStr(key));
			if(matcher.find()){
				list.add(dccListModel);
			}
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
		sql.append(key).append(" like '%").append(words).append("%' ");
		System.out.println("from sys_dcc "+sql.toString()+"order by id asc");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
	
	public Page<DCCListModel> paginateByKeyWords(int pageNumber,int pageSize,String key,List<String> words)
	{
		StringBuilder sql= new StringBuilder("where 1=2 ");
		for(String word:words)
		{
			sql.append("or ").append(key).append(" = '").append(word).append("' ");
		}
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
	
	public Page<DCCListModel> paginateByKeyWords(int pageNumber,int pageSize,String column,List<String> stations,String key,String words)
	{
		StringBuilder sql = new StringBuilder("where ");
		sql.append("(").append(key).append(" like '%").append(words).append("%') and (");
		for(String station : stations)
		{
			sql.append(column).append(" = '").append(station).append("' or ");
			
		}
		sql.append(" 1=2 ) ");
		System.out.println("from sys_dcc "+sql.toString()+"order by id asc");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
	
	/**
	 * 2015-2-1添加新方法，根据列名是Id
	 * @param pageNumber
	 * @param pageSize
	 * @param column
	 * @param ids
	 * @param key
	 * @param words
	 * @return
	 */
	public Page<DCCListModel> paginateByIdAndKeyWords(int pageNumber,int pageSize,List<Long> ids,String key,String words)
	{
		StringBuilder sql = new StringBuilder("where ");
		sql.append("(").append(key).append(" like '%").append(words).append("%') and (");
		for(Long id : ids)
		{
			sql.append("id").append("='").append(id).append("' or ");
			
		}
		sql.append(" 1=2 ) ");
		System.out.println("from sys_dcc "+sql.toString()+"order by id asc");
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
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by "+orderField+" "+orderDirection);
	}
	
	/**
	 * 2015-2-11 新增加
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @param words
	 * @return
	 */
	public Page<DCCListModel> paginateByKeyWordsAndUploadTime(int pageNumber,int pageSize,String key,List<String> words){
		StringBuilder sql= new StringBuilder("where (1=2 ");
		for(String word:words)
		{
			sql.append("or ").append(key).append(" = '").append(word).append("' ");
		}
		sql.append(") and (uploadtime = '2015-2-11' ) ");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
	
	public Page<DCCListModel> paginateByKeyWordsAndUploadTime(int pageNumber,int pageSize,String column,List<String> stations,String key,String words){
		StringBuilder sql = new StringBuilder("where ");
		sql.append("(").append(key).append(" like '%").append(words).append("%') and (");
		for(String station : stations)
		{
			sql.append(column).append(" = '").append(station).append("' or ");
			
		}
		sql.append(" 1=2 ) and (uploadtime = '2015-2-11')");
		System.out.println("from sys_dcc "+sql.toString()+"order by id asc");
		return paginate(pageNumber,pageSize,"select *","from sys_dcc "+sql.toString()+"order by id asc");
	}
}
