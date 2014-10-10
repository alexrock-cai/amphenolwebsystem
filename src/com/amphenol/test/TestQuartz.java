package com.amphenol.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestQuartz implements Job
{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.format(date));
		System.out.println("你好 Quartz");
		System.out.print("I can count to 10 ->");
		
		for(int i = 1;i<=10;i++)
		{
			System.out.print(" | "+i+" ");
		}
		System.out.println("<- See I did it.");
		JobDataMap properties = arg0.getJobDetail().getJobDataMap();
		System.out.println("Previous Fire Time: " + arg0.getPreviousFireTime());// 上次执行时间
		System.out.println("Current Fire Time: " + arg0.getFireTime());// 本次执行时间
		System.out.println("Next Fire Time: " + arg0.getNextFireTime());// 下一次执行时间
	}

}
