package test.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest extends BasePage {
	
	public static WebDriverWait wait;
	public static long TIMEOUT_IN_SECONDS = 10;
	public static String TESTSHEET_PATH = "S://Automation//TestData.xlsx";
	
	
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
	
	// Wait for element to be visible
    public static WebElement waitForElementVisiblity(By locator) {
    	
		wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static WebDriver waitForframeAvailablityAndSwitchToIt(String frame) {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
//   ----------------------------------------------------------------------------------------------------------------
    
    public static Object[][] getObjectTestData(Object [][] data) throws IOException {	
		return data;	
	} 
    
    // Get data from Excel File into your DataProvider array
    public static Object[][] getExcelTestData(String sheetName) throws IOException {
    	
    	FileInputStream fis = new FileInputStream(TESTSHEET_PATH);
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);
    	XSSFSheet sheet = workbook.getSheet(sheetName);
    	
    	int totalRows = sheet.getLastRowNum()+1;                   // Convert index to count
    	int totalColumns = sheet.getRow(0).getLastCellNum();
    	
    	Object[][] data = new Object[totalRows-1][totalColumns];         //(totalRows-1) since we are not taking header row
    	
    	for(int i=1; i<totalRows ;i++ ) {              // Start from 1 to skip the header row
    		
    		for(int j=0; j<totalColumns; j++) {
    			
    			Cell cell = sheet.getRow(i).getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);     // Handle NULL cells
    			data[i-1][j] = getCellType(cell);
    			
    			// [i-1] because we skipped the header row, the first row of data should start from index 0 in your array
    		}
    	}
    	workbook.close();
    	fis.close();
    	
    	return data;
    }
    
  // Handles different cell types (e.g., STRING, NUMERIC, BOOLEAN, FORMULA, BLANK)
    public static Object getCellType(Cell cell) {
    	
    	switch(cell.getCellType()) {
    	
    	case STRING :
    		return cell.getStringCellValue();
    		
    	case NUMERIC:
    		// Check if a cell contains a date, since dates are stored internally in Excel as double values.
    		if(DateUtil.isCellDateFormatted(cell)) {  
    			return cell.getDateCellValue();
    		}
    		else {
    			return cell.getNumericCellValue();
    		}
    		
    	case BOOLEAN:
    		return cell.getBooleanCellValue();
    		
    	case FORMULA:
    		return cell.getCellFormula();
    		
    	case BLANK:
    		return "";                   // Return empty string for blank cells
    		
    	default:
    		return "";                  // Default to empty string for unknown types
    		
    	}
    }

}
