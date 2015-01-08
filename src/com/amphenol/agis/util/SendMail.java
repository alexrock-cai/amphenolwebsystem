package com.amphenol.agis.util;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
//import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.amphenol.agis.pojo.MailBean;

public class SendMail {

    public String toChinese(String text) {
        try {
            text = MimeUtility.encodeText(new String(text.getBytes(), "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


    public boolean sendMail(MailBean mb) {
        String host = mb.getHost();
       
        String from = mb.getFrom();
        String to = mb.getTo();
        String cc = mb.getCc();
        String subject = mb.getSubject();
        String content = mb.getContent();
        String fileName = mb.getFilename();
        Vector<String> file = mb.getFile();
        
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);                
        
        Session session = Session.getInstance(props);
        
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress ={};
            String[] t=to.split(",");
            for(int i=0;i<t.length;i++){
            	toAddress[i]=new InternetAddress(t[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            InternetAddress[] ccAddresses={};
            String[] c=cc.split(",");
            for(int i=0;i<c.length;i++){
            	ccAddresses[i]=new InternetAddress(c[i]);
            }
            msg.setRecipients(Message.RecipientType.CC, ccAddresses);
            msg.setSubject(toChinese(subject));

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setText(content);
            mp.addBodyPart(mbpContent);

            /*    ���ʼ�����Ӹ���    */
            if(file!=null){
            	Enumeration<String> efile = file.elements();
            	while (efile.hasMoreElements()) {
            		MimeBodyPart mbpFile = new MimeBodyPart();
            		fileName = efile.nextElement().toString();
            		FileDataSource fds = new FileDataSource(fileName);
            		mbpFile.setDataHandler(new DataHandler(fds));
            		mbpFile.setFileName(toChinese(fds.getName()));
            		mp.addBodyPart(mbpFile);
            	}
            }
            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);
            
        } catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
        return true;
    }

}

