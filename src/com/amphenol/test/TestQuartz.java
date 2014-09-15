package com.amphenol.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestQuartz implements Job
{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println("你好 Quartz");
	}

}
