package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 * 
 * table sys_product
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| id            | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| org           | varchar(50)  | YES  |     | NULL    |                |
| customer_name | varchar(100) | YES  |     | NULL    |                |
| pn            | varchar(100) | YES  |     | NULL    |                |
| customer_pn   | varchar(100) | YES  |     | NULL    |                |
| rev           | varchar(50)  | YES  |     | NULL    |                |
| team          | varchar(50)  | YES  |     | NULL    |                |
| wo            | varchar(100) | YES  |     | NULL    |                |
| sn            | varchar(100) | YES  |     | NULL    |                |
| customer_sn   | varchar(100) | YES  | MUL | NULL    |                |
| timestamp     | varchar(100) | YES  |     | NULL    |                |
| status        | varchar(100) | YES  |     | NULL    |                |
| mac_address   | varchar(100) | YES  |     | NULL    |                |
| pn_label      | varchar(100) | YES  |     | NULL    |                |
| rev_label     | varchar(100) | YES  |     | NULL    |                |
| hasprogram    | tinyint(1)   | YES  |     | NULL    |                |
| program_id    | varchar(50)  | YES  |     | NULL    |                |
| hasverify     | tinyint(1)   | YES  |     | NULL    |                |
| verify_id     | varchar(50)  | YES  |     | NULL    |                |
| onship        | tinyint(1)   | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+

 * @author rocky
 *
 */

public class ProductModel extends Model<ProductModel> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4881003661570807576L;
	
	public static ProductModel dao = new ProductModel();
	
	public List<ProductModel> findAll()
	{
		String sql="select * from sys_product";
		return ProductModel.dao.find(sql);
	}
	
	public Page<ProductModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber,pageSize,"select *","from sys_product order by id desc");
	}
	
	public Page<ProductModel> paginateByKeyWords(int pageNumber, int pageSize,String key,String words)
	{
		StringBuilder sql= new StringBuilder("where 1=1 and ");
		sql.append(key).append(" like '%").append(words).append("%' ");
		return paginate(pageNumber,pageSize,"select *","from sys_product "+sql.toString()+"order by id desc");
	}
	
	public ProductModel findByCustomerSn(String sn)
	{
		String sql="select * from sys_product where customer_sn = ?";
		
		return ProductModel.dao.findFirst(sql, sn);
	}
	
	public Long getProductIdByCustomerSn(String sn)
	{
		if(findByCustomerSn(sn) !=null)
			return findByCustomerSn(sn).getLong("id");
		else
			return null;
	}
}
