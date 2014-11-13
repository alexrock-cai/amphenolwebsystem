package com.amphenol.agis.pm.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.amphenol.agis.pm.model.EquipmentPMInfoModel;
import com.amphenol.agis.pm.model.YearlyPMScheduleModel;
import com.amphenol.agis.util.LotusSendMail;

public class YearlyPMAutoCheckService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		LotusSendMail sender;
		/*
		 * 任务运行发送提醒邮件
		 */
		try {
			sender=new LotusSendMail("PM_AutoCheckJob@amphenol-tcs.com");
			sender.addTo("rocky.cai@amphenol-tcs.com");
			sender.setSubject("YearlyPM AutoCheck Start");
			sender.setBody("["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"] Yearly PM AutoCheck Start.");
			sender.send();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Yearly PM Auto Check start........");
		Map<String, List<String>> overTimeMailMap=new HashMap<String, List<String>>();
		Map<String, List<String>> alarmMailMap=new HashMap<String, List<String>>();
		Map<String,List<String>> over3DaysMailMap=new HashMap<String, List<String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		//int nowYear=calendar.get(Calendar.YEAR);
		String nowDate=sdf.format(calendar.getTime());
		//循环扫描年度PM计划列表
		List<YearlyPMScheduleModel> yList = YearlyPMScheduleModel.dao.findAll();
		for(int i=0;i<yList.size();i++){
			
			YearlyPMScheduleModel yModel=yList.get(i);
			if(yModel.getStr("yearPMDay")==null) continue;
			EquipmentPMInfoModel eInfoModel=EquipmentPMInfoModel.dao.findByEquipmentID(yModel.getStr("equipmentID"));
			System.out.println("EquipmentPMInfo:"+eInfoModel.toJson());
			if(!yModel.getBoolean("isYearPM")){
				String currentPMDayString=yModel.getStr("year")+"-"+yModel.getStr("yearPMDay");
				try {
					//将PMday字符串转换为Date 格式
					Date currentPMDate=sdf.parse(currentPMDayString);
					//将当前PM计划日期转换为Calendar
					calendar.setTime(currentPMDate);
					//将当前PM计划日期减去7天，也就是到期前7天开始报警。
					calendar.add(Calendar.DAY_OF_MONTH, -7);
					//将报警日期转换为 Date 对象。
					Date alarmDate=calendar.getTime();
					
					calendar.setTime(currentPMDate);
					//获取PM日期加3天的日期
					calendar.add(Calendar.DAY_OF_MONTH, 3);
					Date over3DaysDate= calendar.getTime();
					//获取当前日期
					Date currentDate = sdf.parse(nowDate);
					//如果PM过期超过三天，需要将报警邮件抄送Site Manager
					System.out.println("overPM3day:["+sdf.format(over3DaysDate)+"]pmdate: ["+sdf.format(currentPMDate)+"] alarmDate is :["+sdf.format(alarmDate)+"] over3days compare before: ["+over3DaysDate.before(currentDate));
					if(over3DaysDate.before(currentDate)){
						String msg="<td>["+yModel.getStr("equipmentID")+"]</td><td> 在 ["+currentPMDayString+" ]的年度PM计划已经过期三天还没有执行 系统中没有发现PM记录 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
						//从待发送的邮件列表中查找是否有该责任的人的邮件地址，如果有则添加一条信息，若干没有则新建一个。
						if(over3DaysMailMap.get(eInfoModel.getStr("ownerEmail"))==null){
							List<String> over3DaysList=new ArrayList<String>();
							over3DaysList.add(msg);
							over3DaysMailMap.put(eInfoModel.getStr("ownerEmail"), over3DaysList);
						}
						else{
							over3DaysMailMap.get(eInfoModel.getStr("ownerEmail")).add(msg);
						}
					}
					else if(currentPMDate.compareTo(currentDate)<0){
						
						String msg="<td>["+yModel.getStr("equipmentID")+"]</td><td> 在 ["+currentPMDayString+" ]的年度PM计划还没有执行 系统中没有发现PM记录 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
						if(overTimeMailMap.get(eInfoModel.getStr("ownerEmail"))==null){
							List<String> overTimePMList=new ArrayList<String>();
			
							overTimePMList.add(msg);
							overTimeMailMap.put(eInfoModel.getStr("ownerEmail"), overTimePMList);
							
						}else{
							overTimeMailMap.get(eInfoModel.getStr("ownerEmail")).add(msg);
							
						}
						
					}else if(currentPMDate.compareTo(currentDate)>=0&&alarmDate.compareTo(currentDate)<=0){
						String msg="<td>["+yModel.getStr("equipmentID")+"]</td><td> 年度PM将于 ["+currentPMDayString+" ]到期 请及时保养 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
						if(alarmMailMap.get(eInfoModel.getStr("ownerEmail"))==null){
							List<String> alarmPMList= new ArrayList<String>();
							alarmPMList.add(msg);
							alarmMailMap.put(eInfoModel.getStr("ownerEmail"), alarmPMList);
						}else{
							alarmMailMap.get(eInfoModel.getStr("ownerEmail")).add(msg);
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//遍历结束
		System.out.println("Yearly PM Auto Check stop........");
		//发送邮件
				Set<String> set=over3DaysMailMap.keySet();
				if(!set.isEmpty()){
					for(Iterator<String> it=set.iterator();it.hasNext();){
						String ownerEmail=it.next();
						try {
							sender=new LotusSendMail("YearlyPM_Monitor@amphenol-tcs.com");
							sender.addTo(ownerEmail);
							sender.addCc("Elma.dan@amphenol-tcs.com");
							sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
							sender.addCc("Hudson.zhang@amphenol-tcs.com");
							sender.addBcc("rocky.cai@amphenol-tcs.com");
							StringBuilder sb=new StringBuilder();
							sb.append("<p>严重警告！！有过期3天的YearlyPM计划未执行，请及时完成！</p>").append("<table>");
							for(String msg:over3DaysMailMap.get(ownerEmail)){
								System.out.println(msg);
								sb.append("<tr>").append(msg).append("</tr>");
							}
							sb.append("</table>");
							sender.setSubject("严重警告！！！有YearlyPM过期超过3天未执行");
							sender.setBody(sb.toString());
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]过期3天警告邮件发送中...............");
							sender.send();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]过期3天警告邮件发送成功...............");
						} catch (MessagingException e) {
							// TODO: handle exception
							e.printStackTrace();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]过期3天警告邮件发送失败...............");
						}
					}
				}
				
				//***************发送小于3天的过期报警邮件**********************
				set=overTimeMailMap.keySet();				
				if(!set.isEmpty()){	
					
					for(Iterator<String> it=set.iterator();it.hasNext();){
						String ownerEmail= it.next();
						try {
							sender=new LotusSendMail("YearlyPM_Monitor@amphenol-tcs.com");
							//设定报警邮件的收件人
							sender.addTo(ownerEmail);
							//这是一份过期的PM报警邮件，需要发送给责任人的主管。
							sender.addCc("Elma.dan@amphenol-tcs.com");
							sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
							sender.addBcc("rocky.cai@amphenol-tcs.com");
							StringBuilder sb=new StringBuilder();
							sb.append("<p>警告！！有过期的YearlyPM计划未执行，请及时完成！</p>").append("<table>");
							for(String msg:overTimeMailMap.get(ownerEmail)){
								System.out.println(msg);
								sb.append("<tr>").append(msg).append("</tr>");
							}
							sb.append("</table>");
							sender.setSubject("警告！！！有YearlyPM过期未执行");
							sender.setBody(sb.toString());
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]警告邮件发送中...............");
							sender.send();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]警告邮件发送成功。。。。。");
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]警告邮件发送失败。。。。。。。。");
						}
						//System.out.println("Quartly Check owner:***"+ownerEmail+"*****");
						
						
						System.out.println("****************************[YearlyPM]警告邮件分隔符*********************************");
					}
					
				}
					//***********发送提醒邮件******************************
					set=alarmMailMap.keySet();
				if(!set.isEmpty()){
					for(Iterator<String> it=set.iterator();it.hasNext();){
						String ownerEmail= it.next();
						try {
							sender=new LotusSendMail("YearlyPM_Monitor@amphenol-tcs.com");
							//设定邮件的收件人 
							sender.addTo(ownerEmail);
							//这是一份提醒邮件不需要发给主管
							sender.addCc("Elma.dan@amphenol-tcs.com");
							//添加IE部门邮件提醒
							sender.addCc("barry.cheng@amphenol-tcs.com");
							sender.addCc("Ivy.ding@amphenol-tcs.com");
							sender.addCc("Fang-Lin.wu@amphenol-tcs.com");
							sender.addCc("xi.zheng@amphenol-tcs.com");
							//sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
							sender.addBcc("rocky.cai@amphenol-tcs.com");
							StringBuilder sb=new StringBuilder();
							sb.append("<p>提醒你！！有即将到期的Yearly PM计划要执行，请及时完成！</p>").append("<table>");
							for(String msg:alarmMailMap.get(ownerEmail)){
								System.out.println(msg);
								sb.append("<tr>").append(msg).append("</tr>");
							}
							sb.append("</table>");
							sender.setSubject("提醒！！！有YearlyPM要到期");
							sender.setBody(sb.toString());
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]提醒邮件发送中...............");
							sender.send();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]提醒邮件发送成功。。。。。");
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("["+nowDate+"][YearlyPM][收件人："+ownerEmail+"]提醒邮件发送失败。。。。。。。。");
						}
						System.out.println("****************************[YearlyPM]提醒邮件**分隔符*********************************");
					}
				}
				
	}

}
