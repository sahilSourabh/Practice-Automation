package test.TestComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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

	
	public static void sendEmail() throws EmailException {

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

}
