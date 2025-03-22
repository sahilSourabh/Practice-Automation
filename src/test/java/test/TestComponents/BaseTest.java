package test.TestComponents;

import java.io.File;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BaseTest extends BasePage {
	
	public static String getScreenshotPath(String testcaseName, WebDriver driver)  {

		String reportPath = System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
		
		
		try {
			TakesScreenshot sc = (TakesScreenshot) driver;
			File src = sc.getScreenshotAs(OutputType.FILE);
			File destPath = new File(reportPath);
			FileUtils.copyFile(src, destPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportPath;
	}

	
	public static void sendSimpleEmail() throws EmailException {

		Email email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465); // 465, 587
		email.setAuthenticator(new DefaultAuthenticator("acekazuki.automation@gmail.com", "pwd"));
		email.setSSLOnConnect(true);
//		email.setStartTLSRequired(true);
		email.setFrom("acekazuki.automation@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("sourabhsahil67@gmail.com");
		email.send();

	}
	
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
	
	public static void selectByVisibleText(WebElement ele, String name) {
		Select options = new Select(ele);
		options.selectByVisibleText(name);
	}
	public static void selectByValue(WebElement ele, String value) {
		Select options = new Select(ele);
		options.selectByValue(value);
	}
	
	
	public static void sleep(long seconds) throws InterruptedException {
		
		Thread.sleep(seconds*1000);
	}

}
