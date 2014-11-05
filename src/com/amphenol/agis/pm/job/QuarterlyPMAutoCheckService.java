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
import com.amphenol.agis.pm.model.QuarterlyPMScheduleModel;
import com.amphenol.agis.util.LotusSendMail;

public class QuarterlyPMAutoCheckService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("Quarterly PM Auto Check start........");
		String[] isQuarterlyPM={"isQ1PM","isQ2PM","isQ3PM","isQ4PM"};
		String[] qDays={"q1PMDay","q2PMDay","q3PMDay","q4PMDay"};
		Map<String, List<String>> overTimeMailMap=new HashMap<String, List<String>>();
		Map<String, List<String>> alarmMailMap=new HashMap<String, List<String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		//int nowYear=calendar.get(Calendar.YEAR);
		String nowDate=sdf.format(calendar.getTime());
		List<QuarterlyPMScheduleModel> qList=QuarterlyPMScheduleModel.dao.findAll();
		for(int i=0;i<qList.size();i++){
			QuarterlyPMScheduleModel qModel=qList.get(i);
			EquipmentPMInfoModel eInfoModel=EquipmentPMInfoModel.dao.findByEquipmentID(qModel.getStr("equipmentID"));
			for(int q=0;q<4;q++){
				if(!qModel.getBoolean(isQuarterlyPM[q])){
					//获取当前季度PM计划日期
					String currentPMDayString=qModel.getStr("year")+"-"+qModel.getStr(qDays[q]);
					try {
						Date currentPMDate=sdf.parse(currentPMDayString);
						calendar.add(Calendar.DAY_OF_MONTH, -3);
						Date alarmDate=calendar.getTime();
						Date currentDate = sdf.parse(nowDate);
						if(currentPMDate.compareTo(currentDate)<0){
							String msg="<td>["+qModel.getStr("equipmentID")+"]</td><td> 在 ["+currentPMDayString+" ]的季度PM计划还没有执行 系统中没有发现PM记录 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
							if(overTimeMailMap.get(eInfoModel.getStr("ownerEmail"))==null){
								List<String> overTimePMList=new ArrayList<String>();
								overTimePMList.add(msg);
								overTimeMailMap.put(eInfoModel.getStr("ownerEmail"), overTimePMList);
							}else{
								overTimeMailMap.get(eInfoModel.getStr("ownerEmail")).add(msg);
							}
							
						}else if(currentPMDate.compareTo(currentDate)>0&&alarmDate.compareTo(currentDate)<=0){
							String msg="<td>["+qModel.getStr("equipmentID")+"]</td><td> 将于 ["+currentPMDayString+" ]执行季度PM计划 请及时保养 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
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
			}
		}//遍历结束
		System.out.println("Quarterly PM Auto Check stop........");
		//发送邮件
		Set<String> set=overTimeMailMap.keySet();
		LotusSendMail sender;
		
			
			
			for(Iterator<String> it=set.iterator();it.hasNext();){
				String ownerEmail= it.next();
				try {
					sender=new LotusSendMail("QuarterlyPM_Monitor@amphenol-tcs.com");
					//设定报警邮件的收件人
					sender.addTo(ownerEmail);
					//这是一份过期的PM报警邮件，需要发送给责任人的主管。
					sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
					StringBuilder sb=new StringBuilder();
					sb.append("<p>警告！！你有过期的QuarterlyPM计划未执行，请及时完成！</p>").append("<table>");
					for(String msg:overTimeMailMap.get(ownerEmail)){
						System.out.println(msg);
						sb.append("<tr>").append(msg).append("</tr>");
					}
					sb.append("</table>");
					sender.setSubject("警告！！！你有QuarterlyPM过期未执行");
					sender.setBody(sb.toString());
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]警告邮件发送中...............");
					sender.send();
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]警告邮件发送成功。。。。。");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]警告邮件发送失败。。。。。。。。");
				}
				System.out.println("Quartly Check owner:***"+ownerEmail+"*****");
				
				
				System.out.println("****************************[QuarterlyPM]分隔符*********************************");
			}
			
		
			//***********发送提醒邮件******************************
			set=alarmMailMap.keySet();
			
			for(Iterator<String> it=set.iterator();it.hasNext();){
				String ownerEmail= it.next();
				try {
					sender=new LotusSendMail("QuarterlyPM_Monitor@amphenol-tcs.com");
					//设定邮件的收件人 
					sender.addTo(ownerEmail);
					//这是一份提醒邮件不需要发给主管
					//sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
					StringBuilder sb=new StringBuilder();
					sb.append("<p>提醒你！！你有即将到期的Quarterly PM计划要执行，请及时完成！</p>").append("<table>");
					for(String msg:alarmMailMap.get(ownerEmail)){
						System.out.println(msg);
						sb.append("<tr>").append(msg).append("</tr>");
					}
					sb.append("</table>");
					sender.setSubject("提醒！！！你有QuarterlyPM要到期");
					sender.setBody(sb.toString());
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]提醒邮件发送中...............");
					sender.send();
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]提醒邮件发送成功。。。。。");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("["+nowDate+"][QuarterlyPM][收件人："+ownerEmail+"]提醒邮件发送失败。。。。。。。。");
				}
				System.out.println("****************************[QuarterlyPM]提醒邮件**分隔符*********************************");
			}
		
		
	}

}
