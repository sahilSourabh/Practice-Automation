package test.DataDrivenTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class DataDrivingFromJSON extends BaseTest{
	
	private ContactPage contactPage;
	private String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\test\\data\\TestData.json";
	
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
        waitForframeAvailablityAndSwitchToIt("mainpanel");
		contactPage = new ContactPage(driver);
	}
	
	
	@Test(priority = 1, dataProvider = "getData")
	public void dataDrivingFromJSONTest(HashMap<String, String> input) throws InterruptedException {
		
		contactPage.clickOnNewContactLink();
		contactPage.createNewContact(input.get("title"), input.get("firstName"), input.get("lastName"), input.get("companyName"));
		sleep(2);
	}	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		List<HashMap<String,String>> jsonData = getJSONData(filePath);
//		Object[][] data = { {jsonData.get(0)},{jsonData.get(1)} };
		Object[][] data = getJSONData(filePath);
		
		return BaseTest.getObjectTestData(data);
	}

}
