package test.DataDrivenTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class DataDrivingUsingExcel extends BasePage{
	
	private ContactPage contactPage;
	private String sheetName = "contacts";

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		if (driver == null) {  // Ensure driver is initialized
            driver = initializeDriver();
        }
		driver.get("https://classic.freecrm.com/");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
		driver.findElement(By.cssSelector("input[value*='Login']")).click();
		// Switch to frame with proper wait
        BaseTest.waitForframeAvailablityAndSwitchToIt("mainpanel");
		contactPage = new ContactPage(driver);
	}
	
	
	@Test(priority = 1, dataProvider = "getData")
	public void dataDrivingFromExcelTest(String title, String firstName, String lastName, String companyName) {
		
		contactPage.clickOnNewContactLink();
		contactPage.createNewContact(title, firstName, lastName, companyName);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		return BaseTest.getExcelTestData(sheetName);
		
	}
	

}
