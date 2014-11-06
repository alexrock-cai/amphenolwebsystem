package com.amphenol.test;

import java.util.List;

import javax.mail.MessagingException;

import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.util.Eml;
import com.amphenol.agis.util.LotusSendMail;



public class EmailServiceTest 
{
	public void sendModifyPwdEmail(String email)
	{

		try
		{
			Eml eml = new Eml("smtp.126.com", "amphenolmaster@126.com", "amphenolmaster@126.com", "test123");
			eml.addTo(email);
			eml.setSubject("密码修改提示");
			eml.setBody("你最近修改了密码 如非本人操作 请及时 联系管理员 By Jfinal Authority");
			eml.send();
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}

	}
	
	public void sendNoEepromList(List<ShipdataModel> list)
	{
		try
		{
			Eml eml=new Eml("smtp.126.com", "amphenolmaster@126.com", "amphenolmaster@126.com", "test123");
			eml.addTo("rocky.cai@amphenol-tcs.com");
			eml.addTo("cww.jerry@gmail.com");
			eml.addTo("cai.wenwen@hotmail.com");
			eml.setSubject("EEPROM 系统提示信息");
			eml.setBody("总共有："+list.size()+"块产品没有经过EEPROM，清单如下：");
			for(ShipdataModel ship : list)
			{
				eml.setBody("customer_sn:"+ship.getStr("customer_sn")+"\n");
			}
			eml.send();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		//new EmailServiceTest().sendModifyPwdEmail("cww.jerry@gmail.com");
		LotusSendMail sender;
		try {
			sender = new LotusSendMail("test@amphenol-tcs.com");
			sender.addTo("rocky.cai@amphenol-tcs.com");
			sender.addCc("cww.jerry@gmail.com");
			sender.setSubject("test new mail class");
			sender.setBody("<h1>this is a test mail for mac air</h1>");
			System.out.println("邮件发送中.........");
			sender.send();
			System.out.println("邮件发送成功。。。。。。。");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("邮件发送失败..........");
		}

	}
}
