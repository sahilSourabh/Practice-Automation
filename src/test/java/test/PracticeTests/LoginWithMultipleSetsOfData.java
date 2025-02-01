package test.PracticeTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import test.TestComponents.BasePage;

public class LoginWithMultipleSetsOfData extends BasePage{
	
	private static Logger logger = LogManager.getLogger(LoginWithMultipleSetsOfData.class);

	
	@Test(dataProvider="credentials")
	public void verifyLoginCredentials(String scenario, String username, String password) {
		
		driver = initializeDriver();
		driver.get("https://demowebshop.tricentis.com");
		driver.findElement(By.xpath("//a[@class='ico-login' and contains(text(),'Log in')]")).click();
		
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value*='Log in']")).click();
		
		if(scenario.equals("bothcorrect")) {
			
			WebElement mailText = driver.findElement(By.cssSelector(".header-links ul li a[class='account']"));
			Assert.assertTrue(mailText.isDisplayed(), "Login not success");
			logger.info("login is success");
		}
		else if(scenario.equals("bothincorrect")) {
			
			String errorText = driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText();
			Assert.assertEquals(errorText, "No customer account found");	
			logger.error("Error message different");
		}
		else if(scenario.equals("usernameincorrect")) {
			
			String errorText = driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText();
			Assert.assertEquals(errorText, "The credentials provided are incorrect");	
			logger.info("The credentials provided are incorrect");
		}
		else if(scenario.equals("passwordincorrect")){
			
			String errorText = driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText();
			Assert.assertEquals(errorText, "The credentials provided are incorrect");	
			logger.info(errorText);
		}	
	}
	
	@DataProvider(name="credentials")
	public Object[][] getData() {
		
		return new Object[][] { 
			{"bothcorrect", "sakinala@gmail.com", "p@ssword"},
			{"bothincorrect", "abc@gmail.com", "passwd"},
			{"usernameincorrect", "abc@gmail.com", "p@ssword"},
			{"passwordincorrect", "sakinala@gmail.com", "passwd"},		
		};
		
	}
	

}
