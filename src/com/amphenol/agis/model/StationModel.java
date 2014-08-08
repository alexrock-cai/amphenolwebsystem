package com.amphenol.agis.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 *
 * table sys_station
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| station     | varchar(100) | NO   | UNI | NULL    |                |
| discription | varchar(250) | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 * 
 * @author rocky
 *
 */
public class StationModel extends Model<StationModel> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3719125769354405260L;
	
	public static StationModel dao = new StationModel();
	
	public List<StationModel> findAll()
	{
		String sql="select * from sys_station";
		return StationModel.dao.find(sql);
	}
	
	public Page<StationModel> paginate(int pageNumber , int pageSize)
	{
		return paginate(pageNumber, pageSize, "select *", "from sys_station order by id asc");
	}
}
