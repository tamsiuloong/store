package com.yaorange.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * �����ʼ��Ĺ�����:
 * @author admin
 *
 */
public class MailUtils {

	public static void sendMail(String to,String code){
		
		try {
			// �������:
			Properties props = new Properties();
			Session session = Session.getInstance(props, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("service@estore.com", "111");
				}
				
			});
			// �����ʼ�:
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("service@estore.com"));
			// �����ռ���:
			// TO:�ռ���   CC:����   BCC:����,����.
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ����:
			message.setSubject("�����컢�ٷ��̳ǵļ����ʼ�!");
			// ����:
			message.setContent("<h1>�����컢�ٷ��̳ǵļ����ʼ�:�����������Ӽ���!</h1><h3><a href='http://localhost:8888/store/user.do?method=active&code="+code+"'>http://localhost:8888/store/user.do?method=active&code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
			// �����ʼ�:
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MailUtils.sendMail("aaa@store.com", "123sdfjklsdkljrsiduoi1123");
	}
}
