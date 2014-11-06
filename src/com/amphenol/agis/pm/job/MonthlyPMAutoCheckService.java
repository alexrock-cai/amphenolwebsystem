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
import com.amphenol.agis.pm.model.MonthlyPMScheduleModel;
import com.amphenol.agis.util.LotusSendMail;
/**
 * 月度PM计划检查任务
 * 设定日期提前3天开始报警。
 * @author rocky
 *
 */
public class MonthlyPMAutoCheckService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		LotusSendMail sender;
		try {
			sender=new LotusSendMail("PM_AutoCheckJob@amphenol-tcs.com");
			sender.addTo("rocky.cai@amphenol-tcs.com");
			sender.setSubject("MonthlyPM AutoCheck Start");
			sender.setBody("["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"] Monthly PM AutoCheck Start.");
			sender.send();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Monthly PM Auto Check start........");
		String[] isMonthlPMs={"isJanPM","isFebPM","isMarPM","isAprPM","isMayPM","isJunPM","isJulPM",
						"isAugPM","isSepPM","isOctPM","isNovPM","isDecPM"};
		String[] mDays={"janPMDay","febPMDay","marPMDay","aprPMDay","mayPMDay","junPMDay",
						"julPMDay","augPMDay","sepPMDay","octPMDay","novPMDay","decPMDay"};
		//EmailService emailService=new EmailService();
		
		Map<String, List<String>> overTimeMailMap=new HashMap<String, List<String>>();
		Map<String, List<String>> alarmMailMap=new HashMap<String, List<String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		//int nowYear=calendar.get(Calendar.YEAR);
		String nowDate=sdf.format(calendar.getTime());
		List<MonthlyPMScheduleModel> mList=MonthlyPMScheduleModel.dao.findAll();
		//遍历所有的月度PM计划
		for(int i=0;i<mList.size();i++){
			MonthlyPMScheduleModel mScheduleModel=mList.get(i);
			EquipmentPMInfoModel eInfoModel=EquipmentPMInfoModel.dao.findByEquipmentID(mScheduleModel.getStr("equipmentID"));
			//遍历一个设备的当年所有月份的PM计划
			for(int m=0;m<12;m++){
				//判断当月PM标记
				if(!mScheduleModel.getBoolean(isMonthlPMs[m])){
					//获取当前月度PM计划日期
					String tempPMDayString=mScheduleModel.getStr("year")+"-"+mScheduleModel.getStr(mDays[m]);
					try {
						//将获取到的当前PM计划日期字符串转换为Date 对象
						Date pmDate=sdf.parse(tempPMDayString);
						//将当前PM计划日期转换为Calendar
						calendar.setTime(pmDate);
						//将当前PM计划日期减去3天，也就是到期前三天开始报警。
						calendar.add(Calendar.DAY_OF_MONTH, -3);
						//将报警日期转换为 Date 对象。
						
						Date alarmDate=calendar.getTime();
						//将当前日期经过格式化后的字符串转换为Date对象
						Date currentDate=sdf.parse(nowDate);
						//比较PM计划日期和当前日期，如果小于0 则表示该PM计划已经过期，需要发送警告邮件
						System.out.println("pmDateis:"+sdf.format(pmDate)+" currentDate is:"+sdf.format(currentDate)+"alarmDate is:"+sdf.format(alarmDate)+"compare:"+alarmDate.compareTo(currentDate));
						if(pmDate.compareTo(currentDate)<0){
							String msg="<td>["+mScheduleModel.getStr("equipmentID")+"] </td><td>在 ["+tempPMDayString+" ]的月度PM计划还没有执行 系统中没有发现PM记录 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
							if(overTimeMailMap.get(eInfoModel.getStr("ownerEmail"))==null){
								List<String> overTimePMList=new ArrayList<String>();
								overTimePMList.add(msg);
								overTimeMailMap.put(eInfoModel.getStr("ownerEmail"), overTimePMList);
							}else{
								overTimeMailMap.get(eInfoModel.getStr("ownerEmail")).add(msg);
							}
							
						}
						//比较设定的报警日期与当前日期，如果报警日期等于或者超过当前日期，需要发送提醒邮件。
						else if(pmDate.compareTo(currentDate)>0&&alarmDate.compareTo(currentDate)<=0){
							String msg="<td>["+mScheduleModel.getStr("equipmentID")+"]</td><td> 将于 ["+tempPMDayString+"]执行月度PM 请按时执行 责任人是：</td><td>["+eInfoModel.getStr("owner")+"]</td>";
							
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
		}//遍历所有的月度PM计划结束
		System.out.println("Monthly PM Auto Check stop...............");
		//发送过期报警邮件
		
		Set<String> set=overTimeMailMap.keySet();
		if(!set.isEmpty()){
			for(Iterator<String> it=set.iterator();it.hasNext();){
				String ownerEmail= it.next();
				try {
					sender=new LotusSendMail("MonthlyPM_Monitor@amphenol-tcs.com");
					//设定报警邮件的收件人
					sender.addTo(ownerEmail);
					//这是一份过期的PM报警邮件，需要发送给责任人的主管。
					sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
					StringBuilder sb=new StringBuilder();
					sb.append("<p>警告！！你有过期的Monthly PM计划未执行，请及时完成！</p>").append("<table>");
					for(String msg:overTimeMailMap.get(ownerEmail)){
						System.out.println(msg);
						sb.append("<tr>").append(msg).append("</tr>");
					}
					sb.append("</table>");
					sender.setSubject("警告！！！你有MonthlyPM过期未执行");
					sender.setBody(sb.toString());
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]警告邮件发送中...............");
					sender.send();
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]警告邮件发送成功。。。。。");
				} catch (MessagingException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]警告邮件发送失败。。。。。。。。");
				}
				System.out.println("****************************[MonthlyPM]警告邮件**分隔符*********************************");
			}
		}
		//***********发送提醒邮件******************************
		set=alarmMailMap.keySet();
		if(!set.isEmpty()){
			for(Iterator<String> it=set.iterator();it.hasNext();){
				String ownerEmail= it.next();
				try {
					sender=new LotusSendMail("MonthlyPM_Monitor@amphenol-tcs.com");
					//设定邮件的收件人 
					sender.addTo(ownerEmail);
					//这是一份提醒邮件不需要发给主管
					//sender.addCc(EquipmentPMInfoModel.dao.findSuperviorEmailWithOwnerEmail(ownerEmail));
					StringBuilder sb=new StringBuilder();
					sb.append("<p>提醒你！！你有即将到期的Monthly PM计划要执行，请及时完成！</p>").append("<table>");
					for(String msg:alarmMailMap.get(ownerEmail)){
						System.out.println(msg);
						sb.append("<tr>").append(msg).append("</tr>");
					}
					sb.append("</table>");
					sender.setSubject("提醒！！！你有MonthlyPM要到期");
					sender.setBody(sb.toString());
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]提醒邮件发送中...............");
					sender.send();
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]提醒邮件发送成功。。。。。");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("["+nowDate+"][MonthlyPM][收件人："+ownerEmail+"]提醒邮件发送失败。。。。。。。。");
				}
				System.out.println("****************************[MonthlyPM]提醒邮件**分隔符*********************************");
			}
		}
	}

}
