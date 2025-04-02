package test.DataDrivenTests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class DataDrivingUsingArrays extends BasePage {
	
	private ContactPage contactPage;

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
	public void dataDrivenFromArrayTest(String title, String firstName, String lastName, String companyName) {
		
		contactPage.clickOnNewContactLink();
		contactPage.createNewContact(title, firstName, lastName, companyName);
		// Navigate back to "Contacts" for the next iteration
//		driver.switchTo().defaultContent();
//		driver.navigate().refresh();
//		BaseTest.waitForframeAvailablityAndSwitchToIt("mainpanel");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		Object[][] data = { { "Mr.","Ace","Kazuki","OPSCOG"}, { "Mr.","Goku","Son","DBZ"}};
		return BaseTest.getObjectTestData(data);
		
	}

}
