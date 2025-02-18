package test.mails;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class SendMailUsingJavaAPI {

	public static void sendMailWithAttachments() {

		// Email credentials
		final String username = "acekazuki.automation@gmail.com";
		final String password = "pwd";

		// Setting up SMTP server configuration
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Session with authentication
		Session session = Session.getInstance(props, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create email
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("acekazuki.automation@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sourabhsahil67@gmail.com"));
			message.setSubject("Automated Test Report");

			// Email body
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi Team,\r\n"
					+ "\r\n"
					+ "Please find the Automation Report attached.\r\n"
					+ "\r\n"+ "\r\n"
					+ "Thanks & Regards,\r\n"
					+ "Sourabh");

			// Attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filePath = System.getProperty("user.dir") + "//reports//Report.html";
			attachmentPart.attachFile(new File(filePath));

			// Combine parts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);

			// Set content
			message.setContent(multipart);

			// Send email
			Transport.send(message);

			System.out.println("Email sent successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
