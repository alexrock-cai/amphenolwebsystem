package com.amphenol.agis.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.amphenol.agis.pm.model.EquipmentPMInfoModel;
import com.amphenol.agis.pm.model.MonthlyPMScheduleModel;
import com.amphenol.agis.pm.model.PMRecordModel;
import com.amphenol.agis.pm.model.QuarterlyPMScheduleModel;
import com.amphenol.agis.pm.model.YearlyPMScheduleModel;
import com.amphenol.agis.util.FileKit;
import com.amphenol.agis.util.FileUtil;
import com.amphenol.agis.util.LotusSendMail;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
@RequiresPermissions("pm:view")
public class PMController extends Controller {
	/**
	 * PM 监控系统初始界面，为完成待设计
	 */
	
	public void index(){
		render("/bs-admin-bcore/pages/index.jsp");
	}
	
	
	/**
	 * 获取PM info 页面
	 */
	public void getPmInfoPage(){
		render("/bs-admin-bcore/pages/pm/pmindex.jsp");
	}
	
	/**
	 * ajax 获取PmInfo 编辑页面，将相应的数据填进去。
	 */
	@RequiresPermissions("pm:update")
	public void getEditPmInfoPage(){
		setAttr("pmInfo", EquipmentPMInfoModel.dao.findById(getParaToLong("id")));
		render("/bs-admin-bcore/pages/pm/editPmInfo.jsp");
	}
	
	/**
	 * PM Info 页面 表格 ajax 数据准备。
	 */
	public void pmInfo(){
		int pageNumber=getParaToInt("current");
		int pageSize=getParaToInt("rowCount");
		String words=getPara("searchPhrase");
		Page<EquipmentPMInfoModel> pages;
		if(pageSize==-1){
			pageSize=EquipmentPMInfoModel.dao.findAll().size();
		}
		
		
		if(words!=null){
			pages=EquipmentPMInfoModel.dao.paginateByWords(pageNumber, pageSize, words);
		}else {
			pages=EquipmentPMInfoModel.dao.paginate(pageNumber, pageSize);
		}
		
		setAttr("current", pageNumber);
		setAttr("rowCount", pageSize);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
		
	}
	/**
	 * 删除Pm Info 根据ID删除
	 */
	@RequiresPermissions("pm:delete")
	public void deletePmInfo(){
		if(getPara("id")!=null){
			if(EquipmentPMInfoModel.dao.deleteById(getParaToLong("id")))
				setAttr("msg","删除成功！");
			else {
				setAttr("msg","删除失败！");
			}
		}
		else {
			setAttr("msg","删除失败！");
		}
		renderJson();
	}
	
	/**
	 * 保存新建的设备信息
	 */
	@RequiresPermissions("pm:newMachine")
	public void createPmInfo(){
		
		EquipmentPMInfoModel pmInfoModel= new EquipmentPMInfoModel();
		pmInfoModel.set("equipmentID", getPara("equipmentID"));
		pmInfoModel.set("model", getPara("model"));
		pmInfoModel.set("equipmentName", getPara("equipmentName"));
		pmInfoModel.set("owner", getPara("owner"));
		pmInfoModel.set("ownerEmail", getPara("ownerEmail"));
		pmInfoModel.set("supervisor", getPara("supervisor"));
		pmInfoModel.set("supervisorEmail", getPara("supervisorEmail"));
		pmInfoModel.set("isMonthlyPM", getPara("isMonthlyPM")==null ? false : true);
		pmInfoModel.set("isQuarterlyPM", getPara("isQuarterlyPM")==null ? false : true);
		pmInfoModel.set("isYearlyPM", getPara("isYearlyPM")==null ? false : true );
		try {
			pmInfoModel.save();
			LotusSendMail sender=new LotusSendMail("PM_Monitor@amphenol-tcs.com");
			sender.addTo("Elma.dan@amphenol-tcs.com");
			sender.addBcc("rocky.cai@amphenol-tcs.com");
			sender.setSubject("PM 系统有新设备添加");
			sender.setBody("<table><tr><td>[ "+getPara("equipmentID")+" ]</td><td>已经添加到系统中请尽快设置PM Due Day</td></tr></table>");
			sender.send();
			redirect("/pm/getPmInfoPage");
		} catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getPmInfoPage");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
		
		
	}
	/**
	 * 更新设备信息到数据库中
	 */
	@RequiresPermissions("pm:update")
	public void updatePmInfo(){
		EquipmentPMInfoModel pmInfoModel=EquipmentPMInfoModel.dao.findById(getParaToLong("id"));
		pmInfoModel.set("equipmentID", getPara("equipmentID"));
		pmInfoModel.set("model", getPara("model"));
		pmInfoModel.set("equipmentName", getPara("equipmentName"));
		pmInfoModel.set("owner", getPara("owner"));
		pmInfoModel.set("ownerEmail", getPara("ownerEmail"));
		pmInfoModel.set("supervisor", getPara("supervisor"));
		pmInfoModel.set("supervisorEmail", getPara("supervisorEmail"));
		pmInfoModel.set("isMonthlyPM", getPara("isMonthlyPM")==null ? false : true);
		pmInfoModel.set("isQuarterlyPM", getPara("isQuarterlyPM")==null ? false : true);
		pmInfoModel.set("isYearlyPM", getPara("isYearlyPM")==null ? false : true );
		pmInfoModel.update();
		redirect("/pm/getPmInfoPage");
	}
	
	/**
	 * ***********************如下方法是用于处理 月度PM计划********************************
	 */
	/**
	 * 返回月度PM计划页面
	 */
	public void getMonthlyPM(){
		List<String> equipmentIDs=EquipmentPMInfoModel.dao.findEquipmentIDs("isMonthlyPM");
		setAttr("equipmentIDs", equipmentIDs);
		render("/bs-admin-bcore/pages/pm/monthlyPMSchedule.jsp");
	}
	
	/**
	 * datagrid ajax 获取月度PM计划数据
	 */
	
	public void monthlyPM(){
		int pageNumber=getParaToInt("current");
		int pageSize=getParaToInt("rowCount");
		String words=getPara("searchPhrase");
		Page<MonthlyPMScheduleModel> pages;
		if(pageSize==-1){
			pageSize=MonthlyPMScheduleModel.dao.findAll().size();
		}
		if(words!=null){
			pages=MonthlyPMScheduleModel.dao.paginateByWords(pageNumber, pageSize, words);
		}else{
			pages=MonthlyPMScheduleModel.dao.paginate(pageNumber, pageSize);
		}
		setAttr("current", pageNumber);
		setAttr("rowCount", pageSize);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	
	/**
	 * ajax 获取月度PM计划创建表单
	 */
	@RequiresPermissions("pm:create")
	public void getNewMonthlyPMPage(){
		List<String> equipmentIDs=EquipmentPMInfoModel.dao.findEquipmentIDs("isMonthlyPM");
		setAttr("equipmentIDs", equipmentIDs);
		render("/bs-admin-bcore/pages/pm/newMonthlyPM.jsp");
	}
	@RequiresPermissions("pm:create")
	public void createMonthlyPM(){
		MonthlyPMScheduleModel monthlySchedule=new MonthlyPMScheduleModel();
		monthlySchedule.set("equipmentID", getPara("equipmentID"));
		monthlySchedule.set("year", getPara("year"));
		monthlySchedule.set("janPMDay",getPara("janPMDay"));
		monthlySchedule.set("febPMDay",getPara("febPMDay"));
		monthlySchedule.set("marPMDay",getPara("marPMDay"));
		monthlySchedule.set("aprPMDay",getPara("aprPMDay"));
		monthlySchedule.set("mayPMDay", getPara("mayPMDay"));
		monthlySchedule.set("junPMDay",getPara("junPMDay"));
		monthlySchedule.set("julPMDay",getPara("julPMDay"));
		monthlySchedule.set("augPMDay",getPara("augPMDay"));
		monthlySchedule.set("sepPMDay",getPara("sepPMDay"));
		monthlySchedule.set("octPMDay", getPara("octPMDay"));
		monthlySchedule.set("novPMDay", getPara("novPMDay"));
		monthlySchedule.set("decPMDay", getPara("decPMDay"));
		try {
			monthlySchedule.save();
			redirect("/pm/getMonthlyPM");
		} catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getMonthlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:update")
	public void getEditMonthlyPMPage(){
		MonthlyPMScheduleModel monthlyPM=MonthlyPMScheduleModel.dao.findById(getParaToLong("id"));
		
		setAttr("monthlyPM", monthlyPM);
		render("/bs-admin-bcore/pages/pm/editMonthlyPM.jsp");
	}
	@RequiresPermissions("pm:update")
	public void updateMonthlyPM(){
		MonthlyPMScheduleModel monthlySchedule=MonthlyPMScheduleModel.dao.findById(getPara("id"));
		monthlySchedule.set("year", getPara("year"));
		monthlySchedule.set("janPMDay",getPara("janPMDay"));
		monthlySchedule.set("febPMDay",getPara("febPMDay"));
		monthlySchedule.set("marPMDay",getPara("marPMDay"));
		monthlySchedule.set("aprPMDay",getPara("aprPMDay"));
		monthlySchedule.set("mayPMDay", getPara("mayPMDay"));
		monthlySchedule.set("junPMDay",getPara("junPMDay"));
		monthlySchedule.set("julPMDay",getPara("julPMDay"));
		monthlySchedule.set("augPMDay",getPara("augPMDay"));
		monthlySchedule.set("sepPMDay",getPara("sepPMDay"));
		monthlySchedule.set("octPMDay", getPara("octPMDay"));
		monthlySchedule.set("novPMDay", getPara("novPMDay"));
		monthlySchedule.set("decPMDay", getPara("decPMDay"));
		try {
			monthlySchedule.update();
			redirect("/pm/getMonthlyPM");
		} catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getMonthlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:delete")
	public void deleteMonthlyPM(){
		if(getPara("id")!=null){
			if(MonthlyPMScheduleModel.dao.deleteById(getParaToLong("id")))
				setAttr("msg","删除成功！");
			else {
				setAttr("msg","删除失败！");
			}
		}
		else {
			setAttr("msg","删除失败！");
		}
		renderJson();
	}
	/**
	 * ***********************如下方法是用于处理 季度PM计划********************************
	 */
	/**
	 * 返回季度PM计划 页面
	 */
	public void getQuarterlyPM(){
		List<String> equipmentIDs=EquipmentPMInfoModel.dao.findEquipmentIDs("isQuarterlyPM");
		setAttr("equipmentIDs", equipmentIDs);
		render("/bs-admin-bcore/pages/pm/quarterlyPMSchedule.jsp");
	}
	
	public void quarterlyPM(){
		int pageNumber=getParaToInt("current");
		int pageSize=getParaToInt("rowCount");
		String words=getPara("searchPhrase");
		Page<QuarterlyPMScheduleModel> pages;
		if(pageSize==-1){
			pageSize=QuarterlyPMScheduleModel.dao.findAll().size();
		}
		if(words!=null){
			pages=QuarterlyPMScheduleModel.dao.paginateByWords(pageNumber, pageSize, words);
		}else{
			pages=QuarterlyPMScheduleModel.dao.paginate(pageNumber, pageSize);
		}
		setAttr("current", pageNumber);
		setAttr("rowCount", pageSize);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	@RequiresPermissions("pm:create")
	public void createQuarterlyPM(){
		QuarterlyPMScheduleModel quarterlySchedule= new QuarterlyPMScheduleModel();
		quarterlySchedule.set("equipmentID", getPara("equipmentID"));
		quarterlySchedule.set("year",getPara("year"));
		quarterlySchedule.set("q1PMDay",getPara("q1PMDay"));
		quarterlySchedule.set("q2PMDay",getPara("q2PMDay"));
		quarterlySchedule.set("q3PMDay",getPara("q3PMDay"));
		quarterlySchedule.set("q4PMDay", getPara("q4PMDay"));
		try{
			quarterlySchedule.save();
			redirect("/pm/getQuarterlyPM");
		}catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getQuarterlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:update")
	public void getEditQuarterlyPMPage(){
		QuarterlyPMScheduleModel quarterlySchedule=QuarterlyPMScheduleModel.dao.findById(getParaToLong("id"));
		setAttr("quarterlyPM", quarterlySchedule);
		render("/bs-admin-bcore/pages/pm/editQuarterlyPM.jsp");
	}
	@RequiresPermissions("pm:update")
	public void updateQuarterlyPM(){
		QuarterlyPMScheduleModel quarterlySchedule=QuarterlyPMScheduleModel.dao.findById(getPara("id"));
		quarterlySchedule.set("year",getPara("year"));
		quarterlySchedule.set("q1PMDay",getPara("q1PMDay"));
		quarterlySchedule.set("q2PMDay",getPara("q2PMDay"));
		quarterlySchedule.set("q3PMDay",getPara("q3PMDay"));
		quarterlySchedule.set("q4PMDay", getPara("q4PMDay"));
		try{
			quarterlySchedule.update();
			redirect("/pm/getQuarterlyPM");
		}catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getQuarterlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:delete")
	public void deleteQuarterlyPM(){
		if(getPara("id")!=null){
			if(QuarterlyPMScheduleModel.dao.deleteById(getParaToLong("id")))
				setAttr("msg","删除成功！");
			else {
				setAttr("msg","删除失败！");
			}
		}
		else {
			setAttr("msg","删除失败！");
		}
		renderJson();
	}
	
	/**
	 * *************************年度 PM 计划******************************
	 */
	/*
	 * 返回年度计划页面
	 */
	public void getYearlyPM(){
		List<String> equipmentIDs=EquipmentPMInfoModel.dao.findEquipmentIDs("isYearlyPM");
		setAttr("equipmentIDs", equipmentIDs);
		render("/bs-admin-bcore/pages/pm/yearlyPMSchedule.jsp");
	}
	/*
	 * 返回年度季度 Data Grid 数据
	 */
	public void yearlyPM(){
		int pageNumber=getParaToInt("current");
		int pageSize=getParaToInt("rowCount");
		String words=getPara("searchPhrase");
		Page<YearlyPMScheduleModel> pages;
		if(pageSize==-1){
			pageSize=YearlyPMScheduleModel.dao.findAll().size();
		}
		if(words!=null){
			pages=YearlyPMScheduleModel.dao.paginateByWords(pageNumber, pageSize, words);
		}else{
			pages=YearlyPMScheduleModel.dao.paginate(pageNumber, pageSize);
		}
		setAttr("current", pageNumber);
		setAttr("rowCount", pageSize);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	@RequiresPermissions("pm:update")
	public void getEditYearlyPMPage(){
		YearlyPMScheduleModel yearlyModel=YearlyPMScheduleModel.dao.findById(getParaToLong("id"));
		setAttr("yearlyPM", yearlyModel);
		render("/bs-admin-bcore/pages/pm/editYearlyPM.jsp");
	}
	@RequiresPermissions("pm:create")
	public void createYearlyPM(){
		YearlyPMScheduleModel yearlyModel=new YearlyPMScheduleModel();
		yearlyModel.set("equipmentID", getPara("equipmentID"));
		yearlyModel.set("year", getPara("year"));
		yearlyModel.set("yearPMDay", getPara("yearPMDay"));
		try{
			yearlyModel.save();
			redirect("/pm/getYearlyPM");
		}catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getYearlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:update")
	public void updateYearlyPM(){
		YearlyPMScheduleModel yearlyModel=YearlyPMScheduleModel.dao.findById(getParaToLong("id"));
		yearlyModel.set("year", getPara("year"));
		yearlyModel.set("yearPMDay", getPara("yearPMDay"));
		try{
			yearlyModel.update();
			redirect("/pm/getYearlyPM");
		}catch (Exception e) {
			// TODO: handle exception
			setAttr("msg", e);
			setAttr("href", "/pm/getYearlyPM");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
	}
	@RequiresPermissions("pm:delete")
	public void deleteYearlyPM(){
		if(getPara("id")!=null){
			if(YearlyPMScheduleModel.dao.deleteById(getParaToLong("id")))
				setAttr("msg","删除成功！");
			else {
				setAttr("msg","删除失败！");
			}
		}
		else {
			setAttr("msg","删除失败！");
		}
		renderJson();
	}
	//*********************PM 记录上传*****************************
	@RequiresPermissions("pm:record")
	public void getRecord(){
		List<String> equipmentIDs=EquipmentPMInfoModel.dao.findEquipmentIDs();
		setAttr("equipmentIDs", equipmentIDs);
		render("/bs-admin-bcore/pages/pm/pmRecord.jsp");
	}
	@RequiresPermissions("pm:record")
	public void pmRecord(){
		int pageNumber=getParaToInt("current");
		int pageSize=getParaToInt("rowCount");
		String words=getPara("searchPhrase");
		Page<PMRecordModel> pages;
		if(pageSize==-1){
			pageSize=PMRecordModel.dao.findAll().size();
		}
		if(words!=null){
			pages=PMRecordModel.dao.paginateByWords(pageNumber, pageSize, words);
		}else{
			pages=PMRecordModel.dao.paginate(pageNumber, pageSize);
		}
		setAttr("current", pageNumber);
		setAttr("rowCount", pageSize);
		setAttr("rows", pages.getList());
		setAttr("total", pages.getTotalRow());
		renderJson();
	}
	@RequiresPermissions("pm:record")
	public void getSelectJsonData(){
		EquipmentPMInfoModel equipmentPMInfoModel=EquipmentPMInfoModel.dao.findByEquipmentID(getPara("equipmentID"));
		setAttr("equipmentInfo", equipmentPMInfoModel);
		renderJson();
	}
	/*
	 * 返回Ajax 页面，PM记录上传页面
	 * 获取参数 PMType 类型：MontlyPM,QuarterlyPM,YearlyPM
	 */
	@RequiresPermissions("pm:record")
	public void getCreatePMRecord(){
		String pmtype=getPara("PMType");
		if(pmtype.equals("MonthlyPM")){
			List<String> monthlyYears=MonthlyPMScheduleModel.dao.findMonthlyScheduleYears();
			List<String> eIDs=MonthlyPMScheduleModel.dao.findEquipmentIDsByYear(monthlyYears.get(0));
			System.out.println(eIDs.toString());
			setAttr("myears", monthlyYears);
			setAttr("meIDs", eIDs);
			render("/bs-admin-bcore/pages/pm/newMonthlyPMRecord.jsp");
		}else if(pmtype.equals("QuarterlyPM")){
			List<String> quarterlyYears=QuarterlyPMScheduleModel.dao.findQuarterlyScheduleYears();
			List<String> eIDs=QuarterlyPMScheduleModel.dao.findEquipmentIDsByYear(quarterlyYears.get(0));
			setAttr("qyears",quarterlyYears);
			setAttr("qeIDs",eIDs);
			render("/bs-admin-bcore/pages/pm/newQuarterlyPMRecord.jsp");
		}else if (pmtype.equals("YearlyPM")) {
			List<String> years=YearlyPMScheduleModel.dao.findYearlyScheduleYears();
			List<String> eIDs=YearlyPMScheduleModel.dao.findEquipmentIDsByYear(years.get(0));
			setAttr("years",years);
			setAttr("yeIDs", eIDs);
			render("/bs-admin-bcore/pages/pm/newYearlyPMRecord.jsp");
		}else{
			renderHtml("出错啦！<a href='/pm/getRecord'>返回</a>");
		}
	}
	/*
	 * ajax获取设备ID
	 * 获取参数 PMType 类型：MonthlyPM，QuarterlyPM，YearlyPM
	 * 参数 mYear，选中的年度数据
	 */
	@RequiresPermissions("pm:record")
	public void getEquipmentIDs(){
		String pmType=getPara("PMType");
		String mYear=getPara("mYear");
		if(pmType.equals("MonthlyPM")){
			List<String> eIDs=MonthlyPMScheduleModel.dao.findEquipmentIDsByYear(mYear);
			
			setAttr("meIDs", eIDs);
			renderJson();
			
		}else if(pmType.equals("QuarterlyPM")){
			List<String> eIDs=QuarterlyPMScheduleModel.dao.findEquipmentIDsByYear(mYear);
			setAttr("qeIDs", eIDs);
			renderJson();
		}else {
			
		}
	}
	@RequiresPermissions("pm:record")
	@Before(Tx.class)
	public void createPMRecord(){
		PMRecordModel recordModel=new PMRecordModel();
		if(getFile("record")!=null){
			UploadFile upfile=getFile("record");
			File file=upfile.getFile();
			String filename=file.getName();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String path=PathKit.getWebRootPath()+File.separator+"PMRecord";
			String newFileName = getPara("equipmentID")+"_"+getPara("PMType")+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			//重命名文件
			File newFile = FileKit.renameFile(file, newFileName);
			System.out.println(newFile.getName());
			System.out.println(file.getAbsolutePath()+"  "+filename);
			FileUtil.copyFile(newFile, path);
			//file.delete();
			recordModel.set("equipmentID", getPara("equipmentID"));
			recordModel.set("PMType", getPara("PMType"));
			recordModel.set("PMTime",getParaToDate("PMTime"));
			recordModel.set("PMOperator",getPara("PMOperator"));
			recordModel.set("uploadBy",getPara("uploadBy"));
			recordModel.set("uploadTime", f.format(new Date()));
			recordModel.set("file", newFile.getName());
			try {
				System.out.println("start save");
				recordModel.save();
				if(getPara("PMType").equals("MonthlyPM")){
					MonthlyPMScheduleModel m= MonthlyPMScheduleModel.dao.findByEquipmentIDAndYear(getPara("equipmentID"), getPara("year"));
					m.set(getPara("monthlyPM"), true);
					m.update();
				}else if(getPara("PMType").equals("QuarterlyPM")){
					QuarterlyPMScheduleModel q=QuarterlyPMScheduleModel.dao.findByEquipmentIDAndYear(getPara("equipmentID"), getPara("year"));
					q.set(getPara("quarterlyPM"),true);
					q.update();
				}else if(getPara("PMType").equals("YearlyPM")){
					YearlyPMScheduleModel y=YearlyPMScheduleModel.dao.findByEquipmentIDAndYear(getPara("equipmentID"), getPara("year"));
					y.set("isYearPM",true);
					y.update();
				}
				redirect("/pm/getRecord");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				setAttr("msg", e);
				setAttr("href", "/pm/getRecord");
				render("/bs-admin-bcore/pages/pm/errors_500.jsp");
			}
			
		}
		else {
			setAttr("msg", "文件上传失败请重新上传");
			setAttr("href", "/pm/getRecord");
			render("/bs-admin-bcore/pages/pm/errors_500.jsp");
		}
		
	}
}
