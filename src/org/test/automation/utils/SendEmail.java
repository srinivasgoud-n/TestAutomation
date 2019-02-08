package org.test.automation.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.test.automation.base.BrowserManager;

public class SendEmail extends BrowserManager {

	public static void sendTestReports(String subject, String toField, String CCField, String BCCField) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testautomation988@gmail.com", "@1234five");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("testautomation988@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toField));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CCField));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(BCCField));
			message.setSubject(subject);

			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(CURRENTDIR + "//TestReport.html");

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "text/html");

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done. Sent Email");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
