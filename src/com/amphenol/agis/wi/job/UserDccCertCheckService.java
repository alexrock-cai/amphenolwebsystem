package com.amphenol.agis.wi.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.amphenol.agis.util.LotusSendMail;

/**
 * 自动检查已认证的WI是否还在有效期内，如果过期就将该条记录置为失效，并发送邮件提醒相关人员。
 * @author rocky
 *
 */
public class UserDccCertCheckService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		LotusSendMail mailSender;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String currentDay = sdf.format(calendar.getTime());
		//当前日期减去一天，所得日历是昨天的时间。此日期作为条件查询已经过期的认证。
		calendar.add(Calendar.DAY_OF_MONTH,-1);
		String lastDay = sdf.format(calendar.getTime());
		//再减去6天或的报警
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		String alarmDay = sdf.format(calendar.getTime());
	}

}
