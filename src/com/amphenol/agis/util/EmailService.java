package com.amphenol.agis.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import com.amphenol.agis.config.MailSendToConfig;
import com.amphenol.agis.model.PkgModel;
import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.util.Eml;



public class EmailService 
{
	private MailSendToConfig mailListConfig;
	private String[] eppMailTos;
	private String[] eppMailCCs;
	
	public EmailService(){
		try {
			mailListConfig = new MailSendToConfig("mailto.properties");
			eppMailTos = mailListConfig.getString("EPPMailTo").split(";");
			eppMailCCs = mailListConfig.getString("EPPMailCC").split(";");
			for(String to :eppMailTos){
				//eml.addTo(to);
				System.out.println("NoEpromList to:"+to);
			}
			for(String cc : eppMailCCs){
				//eml.addCc(cc);
				System.out.println("NoEpromList to:"+cc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("mailto.properties read error");
		}
	}
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
	public boolean sendNoEepromList(List<ShipdataModel> list,List<ShipdataModel> notindts)
	{
		System.out.println("发送Epprom邮件,启动");
		try
		{
			//Eml eml=new Eml("smtp.126.com", "amphenolmaster@126.com", "amphenolmaster@126.com", "test123");
			LotusSendMail  eml = new LotusSendMail("Ericsson_EEPROM_ShipCheck@amphenol-tcs.com");

			for(String to :eppMailTos){
				eml.addTo(to);
				System.out.println("NoEpromList to:"+to);
			}
			for(String cc : eppMailCCs){
				eml.addCc(cc);
				System.out.println("NoEpromList to:"+cc);
			}
			
			eml.setSubject("Ericsson EEPROM Shipping Scan 系统提示信息");
			
			StringBuilder sb=new StringBuilder();
			sb.append("<p>没有经过EEPROM的清单</p>");
			for(ShipdataModel ship : list)
			{
				sb.append("<p>customer_sn:"+ship.getStr("customer_sn")+"*******WO#:"+ship.getStr("wo")+"******PN#:"+ship.getStr("pn")+"</p>");
			}
			
			sb.append("<p>如下是可能更换标签的产品</p>");
			
			for(ShipdataModel ship : notindts)
			{
				sb.append("<p>customer_sn:"+ship.getStr("customer_sn")+"*******WO#:"+ship.getStr("wo")+"******PN#:"+ship.getStr("pn")+"</p>");
			}
			
			eml.setBody("总共有："+list.size()+"块产品没有经过EEPROM，总共有"+notindts.size()+"块可能更换标签，因为在DTS中没有发现。清单如下：详情请访问：http://131.101.208.5 查看。谢谢！"+sb.toString());
			eml.send();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
			System.out.println("邮件发送失败---："+e.getMessage());
			return false;
		}
		
		return true;
	}
	public boolean sendNoEEPROMAndNoTagin(List<PkgModel> list,List<PkgModel> noTagin)
	{
		try
		{
			System.out.println("发送NoEEPROMAndNoTagin邮件,启动");
			//Eml eml=new Eml("smtp.126.com", "amphenolmaster@126.com", "amphenolmaster@126.com", "test123");
			LotusSendMail  eml = new LotusSendMail("Ericsson_EEPROM_PkgCheck@amphenol-tcs.com");
			for(String to :eppMailTos){
				eml.addTo(to);
				System.out.println("NoEEPROMAndNoTagin to:"+to);
			}
			for(String cc : eppMailCCs){
				eml.addCc(cc);
				System.out.println("NoEEPROMAndNoTagin to:"+cc);
			}
			eml.setSubject("Ercisson EEPROM Package Scan 系统提示信息");
			
			StringBuilder sb=new StringBuilder();
			sb.append("<p>没有经过EEPROM的清单</p>");
			for(PkgModel ship : list)
			{
				sb.append("<p>customer_sn:"+ship.getStr("customer_sn")+"*******WO#:"+ship.getStr("wo")+"******PN#:"+ship.getStr("pn")+"</p>");
			}
			
			sb.append("<p>如下是可能更换标签的产品</p>");
			
			for(PkgModel ship : noTagin)
			{
				sb.append("<p>customer_sn:"+ship.getStr("customer_sn")+"*******WO#:"+ship.getStr("wo")+"******PN#:"+ship.getStr("pn")+"</p>");
			}
			
			eml.setBody("总共有："+list.size()+"块产品没有经过EEPROM，总共有"+noTagin.size()+"块可能更换标签，因为在DTS中没有发现。清单如下：详情请访问：http://131.101.208.5 查看。谢谢！"+sb.toString());
			eml.send();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
			System.out.println("邮件发送失败---："+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public void sendMail(Map<String, List<String>> mailMap) throws MessagingException{
		Eml eml=new Eml("smtp.126.com", "amphenolmaster@126.com", "amphenolmaster@126.com", "test123");
		eml.addTo("cww.jerry@gmail.com");
		eml.setSubject("PM 提示");
		StringBuilder sb=new StringBuilder();
		Set<String> set=mailMap.keySet();
		for(Iterator<String> it=set.iterator();it.hasNext();){
			String s= it.next();
			sb.append("<p> owner :"+s+"****"+mailMap.get(s).toString()+"</p>");
		}
		eml.setBody(sb.toString());
		eml.send();
	}
	public static void main(String[] args)
	{
		new EmailService();
	}
}
