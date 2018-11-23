package com.demo.flashSale.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.demo.flashSale.entity.EmailIdEntity;

@Service
public class SendMail {

	private static String fromAddress = "parvise.md20@gmail.com";

	private static String fromPassword = "05f61a0544";

	private static String clbMailhost = "smtp.gmail.com";

	private static String clbFromAddress = fromAddress;

	private static String subject = "Buy A Product for One Rupee";

	public String sendMail(EmailIdEntity entity, String content) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", clbMailhost);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", fromPassword);

			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(fromAddress, fromPassword);
				}
			});
			Message msg = new MimeMessage(session);

			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content, "text/html");

			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(mbp);

			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(clbFromAddress));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(entity.getEmailAddress()));

			msg.setSubject(subject);
			msg.setContent(multiPart);
			System.out.println(content);
			mbp.setText(content, "UTF-8", "html");

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");
			transport.connect(clbMailhost, fromAddress, fromPassword);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return "mailSent ok";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "mail not sent";
		}
	}

}
