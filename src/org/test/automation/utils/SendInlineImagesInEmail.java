package org.test.automation.utils;

import java.io.IOException;
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


public class SendInlineImagesInEmail {
	public static void sendGreetings(String subject, String toField, String CCField, String BCCField,String filePath) throws IOException {
		Properties props = new Properties();
      String from = "testautomation988@gmail.com";
      final String username = "testautomation988@gmail.com";//change accordingly
      final String password = "@1234five";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {

         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);
         
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         message.setReplyTo(new javax.mail.Address[]
        		 {
        		     new javax.mail.internet.InternetAddress("1srinivas.java@gmail.com")
        		 });

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toField));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CCField));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(BCCField));

         // Set Subject: header field
         message.setSubject(subject);

         // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText = "<H1>"+subject+"</H1><img src=\"cid:image\">";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);

         // second part (the image)
         messageBodyPart = new MimeBodyPart();
         DataSource fds = new FileDataSource(filePath);

         messageBodyPart.setDataHandler(new DataHandler(fds));
         messageBodyPart.setHeader("Content-ID", "<image>");

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         message.setContent(multipart);
         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
