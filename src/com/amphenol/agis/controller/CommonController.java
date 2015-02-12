package com.amphenol.agis.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import com.amphenol.UrlConfig;
import com.amphenol.agis.model.StationModel;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.util.BatchCreateUser;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class CommonController extends Controller 

{
	@RequiresAuthentication
	public void index()
	{
		Subject currentUser = SecurityUtils.getSubject();
		List<StationModel> stationList=StationModel.dao.findAll();
		List<StationModel> permittedStations = new ArrayList<StationModel>();
		String temp;
		
		if(currentUser.isPermitted("wicert:supervisor")){
			for(int i=0;i<stationList.size();i++){
				temp = stationList.get(i).getStr("station");
				if(temp.equalsIgnoreCase("ATB")||temp.equalsIgnoreCase("TOG")||temp.equalsIgnoreCase("TEST")||temp.equalsIgnoreCase("TST")){
					continue;
				}
				permittedStations.add(stationList.get(i));
			}
		}
		if(currentUser.isPermitted("wicert:test")){
			
			for(int i=0;i<stationList.size();i++){
				temp = stationList.get(i).getStr("station");
				if(temp.equalsIgnoreCase("TEST") || temp.equalsIgnoreCase("TST")){
					permittedStations.add(stationList.get(i));
				}
			}
			
		}
		if(currentUser.isPermitted("wicert:atb")){
			
			for(int i=0;i<stationList.size();i++){
				temp = stationList.get(i).getStr("station");
				if(temp.equalsIgnoreCase("ATB") || temp.equalsIgnoreCase("TOG")){
					permittedStations.add(stationList.get(i));
				}
			}
			
		}
		setAttr("station", permittedStations);
		render(UrlConfig.INDEX_URL);
	}
	
	public void tt(){
		render("/bootstrap/dashboard.jsp");
	}
	
	public void pm(){
		render("/bootstrap/pmdashboard.jsp");
	}
	public void bootgrid(){
		int current=getParaToInt("current");
		int rowCount=getParaToInt("rowCount");
		Page<UserModel> pages=UserModel.dao.paginate(current, rowCount);
		
		setAttr("current", current);
		setAttr("rowCount", rowCount);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	public void timeout()
	{
		setAttr("statusCode",301);
		setAttr("message","会话超时请重新登录");
	}
	
	/**
	 * 从excel中批量创建用户
	 */
	public void initUser()
	{
		
		try {
			BatchCreateUser.createUser(new File("/Users/rocky/Desktop/userlist.xls"));
			setAttr("statusCode","200");
			setAttr("message","批量创建用户成功");
			setAttr("navTabId","user_list");
			
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			setAttr("statusCode","300");
			setAttr("message","批量创建用户失败");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setAttr("statusCode","300");
			setAttr("message","批量创建用户失败");
		}
		renderJson();
	}

}
