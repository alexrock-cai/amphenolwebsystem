package com.amphenol.agis.controller;

//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.StationModel;
import com.amphenol.agis.model.UserDccCertModel;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.pojo.UserDccCert;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class UserDccCertController extends Controller {
	/**
	 * 2015-2-1 增加打开已经认证用户清单
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void openDccUserCertList(){
		//搜索字段
		//String key = getPara("key");
		String words = getPara("words");
		int pageNumber = getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		Page<UserDccCertModel> pages ;
		List<UserDccCert> dccCerts = new ArrayList<UserDccCert>();
	
		if(words!=null && !"".equals(words)){//如果是这两个查询选项，先查询User表
			UserModel userModel = UserModel.dao.findByName(words);
			if(userModel !=null)
			{
				pages = UserDccCertModel.dao.paginateAllByUserId(pageNumber, pageSize, userModel.getLong("id"));
			}else{
				
				pages = UserDccCertModel.dao.paginateAllByUserId(pageNumber, pageSize, 0);
			}
		}else {
			pages = UserDccCertModel.dao.paginate(pageNumber, pageSize);
		}
			
		
		
		
		for(UserDccCertModel dccCertModel : pages.getList()){
			dccCerts.add(new UserDccCert(dccCertModel));
		}
		
		setAttr("words", words);
		setAttr("dccCerts", dccCerts);
		setAttr("totalCount",pages.getTotalRow());
		setAttr("numPerPage",pages.getPageSize());
		setAttr("currentPage",pages.getPageNumber());
		render("/dwzpage/authwi/dcc-user-cert-list.jsp");
	}
	/**
	 * 2015-2-1 新增加打开用户认证对话框
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void openUserCert(){
		List<StationModel> permittedStations = getPermittedStation();
		setAttr("station", permittedStations);
		render("/dwzpage/authwi/dcc-cert.jsp");
	}
	

	
	/**
	 * 2015-2-1 新增用户认证保存
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void dccCertificate(){
		Subject currentUser = SecurityUtils.getSubject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String certTime = sdf.format(new Date());
		String username = getPara("username");
		String station = getPara("station");
		String pn = getPara("pn");
		int validity = getParaToInt("validity");
		UserModel user = UserModel.dao.findByName(username);
		UserModel certUserModel = (UserModel)currentUser.getSession(true).getAttribute("user");
		DCCListModel dccModel = DCCListModel.dao.findByStationAndPn(station, pn);

		if(user == null){
			setAttr("statusCode", "300");
			setAttr("message","工号不存在!");
		}else if(dccModel == null){
			setAttr("statusCode", "300");
			setAttr("message","产品不存在，或者该产品没有此站别");
		}else{
			List<UserDccCertModel> dccCertModels = UserDccCertModel.dao.findByUserId(user.getLong("id"));
			boolean hasDuplicate = false;
			for(UserDccCertModel udcm : dccCertModels){
				
				if(udcm.getLong("dccId").equals(dccModel.getLong("id"))){
					//判断该条认证是快到期，如果是将该条记录置为过期，重新添加新的记录
					/* 取消有效期
					try {
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DAY_OF_MONTH, 7);
						Date alarmDay = sdf.parse(sdf.format(calendar.getTime()));
						Date valiDate = sdf.parse(udcm.getStr("validDate"));
						if(alarmDay.before(valiDate)){
							hasDuplicate=true;
							break;
						}else{
							udcm.set("available", false);
							udcm.update();
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						hasDuplicate=true;
					}
					*/
					
				}
			}
			if(hasDuplicate){
				setAttr("statusCode", "300");
				setAttr("message","不可以重复认证！");
			}else{
				UserDccCertModel userDccCertModel = new UserDccCertModel();
				userDccCertModel.set("userId", user.getLong("id"));
				userDccCertModel.set("dccId", dccModel.getLong("id"));
				userDccCertModel.set("certTime",certTime);
				userDccCertModel.set("validity", validity);
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH,validity);
				userDccCertModel.set("validDate", sdf.format(calendar.getTime()));
				userDccCertModel.set("certUser", certUserModel.getLong("id"));
				userDccCertModel.set("available", true);
				
				if(userDccCertModel.save()){
					setAttr("statusCode", "200");
					setAttr("message","保存成功");
					setAttr("callbackType","closeCurrent");
					setAttr("navTabId","dccUserCert_list");
				}else{
					setAttr("statusCode", "300");
					setAttr("message","认证失败，请重新认证!");
				}
			}
			
		}
		
		renderJson();
	}

	/**
	 * 2015-2-10新增加打开新的员工WI认证页面
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void openNewWiCert(){
		DCCListModel dccListModel = DCCListModel.dao.findById(getParaToLong("id"));
		List<UserDccCertModel> userDccCertModels = UserDccCertModel.dao.findByDccId(getParaToLong("id"));
		List<String> hascertedUsers = new ArrayList<String>();
		if(userDccCertModels!=null){
			for(UserDccCertModel userDccCertModel : userDccCertModels){
				UserModel userModel = UserModel.dao.findById(userDccCertModel.getLong("userId"));
				hascertedUsers.add(userModel.getStr("username")+" "+userModel.getStr("name")+" [已经通过认证]");
			}
			setAttr("hascertedUsers", hascertedUsers);
		}
		setAttr("filepath", dccListModel.getStr("filepath"));
		setAttr("currentWiId", dccListModel.getLong("id"));
		setAttr("currentWiPn", dccListModel.getStr("pn"));
		setAttr("currentWiStation", dccListModel.getStr("station"));
		setAttr("currentWiRev", dccListModel.getStr("rev"));
		render("/dwzpage/authwi/wi-cert.jsp");
	}
	
	/**
	 * 2015-2-10新增加，确认工号没有输错，并且未认证过。
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void confirmUser(){
		String empNum = getPara("empNum");
		long currentWiId = getParaToLong("currentWiId");
		if(empNum !=null){
			UserModel userModel = UserModel.dao.findByName(empNum);
			if(userModel !=null){
				List<UserDccCertModel> userDccCertModels = UserDccCertModel.dao.findByDccId(currentWiId);
				boolean flag = false;
				for(UserDccCertModel userDccCertModel : userDccCertModels){
					if(userModel.getLong("id").equals(userDccCertModel.getLong("userId"))){
						flag = true;
						break;
					}else{
						flag = false;
					}
				}
				
				if(flag){
					setAttr("message", "该员工已经认证过此WI，不能重复认证!");
				}else{
					setAttr("result", true);
				}
			}else {
				setAttr("message", "工号不存在");
			}
			
		}else {
			setAttr("message", "工号不能为空");
		}
		renderJson();
	}
	/**
	 * 2015-2-10 新增加，认证处理
	 */
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void newDccCertificate(){
		Subject currentUser = SecurityUtils.getSubject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String certTime = sdf.format(new Date());
		UserModel certUserModel = (UserModel)currentUser.getSession(true).getAttribute("user");
		List<String> certedUsers = new ArrayList<String>();
		try{
			String[] users = getParaValues("users[]");
			long currentWiId = getParaToLong("currentWiId");
			for(String user : users){
				UserModel userModel = UserModel.dao.findByName(user);
				if(userModel !=null){
					UserDccCertModel userDccCertModel = new UserDccCertModel();
					userDccCertModel.set("userId", userModel.getLong("id"));
					userDccCertModel.set("dccId", currentWiId);
					userDccCertModel.set("certTime",certTime);
					userDccCertModel.set("validity", 0);
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MONTH,0);
					userDccCertModel.set("validDate", sdf.format(calendar.getTime()));
					userDccCertModel.set("certUser", certUserModel.getLong("id"));
					userDccCertModel.set("available", true);
					
					if(userDccCertModel.save()){
						
						certedUsers.add(userModel.getStr("username")+" "+userModel.getStr("name")+"[认证成功]");
					}else{
						
						certedUsers.add(userModel.getStr("username")+" "+userModel.getStr("name")+"[认证失败]");
					}
				}
			}
			setAttr("message","认证处理成功！");
			
		}catch (Exception e) {
			// TODO: handle exception
			setAttr("message", "认证出错，请重新认证");
		}
		
		setAttr("certedUsers", certedUsers);
		
		renderJson();
	}
	
	@RequiresPermissions(value={"wicert:supervisor","wicert:test","wicert:atb"},logical=Logical.OR)
	public void openNewDccUserCertList(){
		List<StationModel> permittedStationModels = getPermittedStation();
		int pageNumber=getParaToInt("pageNum");
		int pageSize = getParaToInt("numPerPage");
		String key = getPara("key");
		String words = getPara("words");
		int totalCount;
		int numPerPage;
		int currentPage;
		List<DCCListModel> dccList = new ArrayList<DCCListModel>();
		List<String> stations = new ArrayList<String>();
		for(StationModel stationModel : permittedStationModels){
			stations.add(stationModel.getStr("station"));
		}
		//判断是否有关键字查询
		if(words!=null && !"".equals(words)){
			Page<DCCListModel> pages = DCCListModel.dao.paginateByKeyWords(pageNumber, pageSize, "station", stations, key, words);
			dccList=pages.getList();
			totalCount=pages.getTotalRow();
			numPerPage=pages.getPageSize();
			currentPage=pages.getPageNumber();
		}else{
			Page<DCCListModel> pages = DCCListModel.dao.paginateByKeyWords(pageNumber, pageSize, "station", stations);
			dccList=pages.getList();
			totalCount=pages.getTotalRow();
			numPerPage=pages.getPageSize();
			currentPage=pages.getPageNumber();
		}
		setAttr("words",words);
		setAttr("key",key);
		setAttr("totalCount",totalCount);
		setAttr("numPerPage",numPerPage);
		setAttr("currentPage",currentPage);
		setAttr("wilist",dccList);
		render("/dwzpage/authwi/cert-wilist.jsp");
	}
	/**
	 * 获取当前用户获得许可的站别
	 * @return
	 */
	private List<StationModel> getPermittedStation() {
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
		return permittedStations;
	}
}
