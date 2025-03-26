package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class testPractice extends BaseTest {

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		
		
	}
	
	@Test
	public void handlingDynamicCalendarJS() throws InterruptedException {
		
		driver.get("https://www.google.com/");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("prompt('Please enter the name:');"); 
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        
		driver.switchTo().alert().sendKeys("Sahil");
		sleep(2);
		driver.switchTo().alert().accept();
		String enteredValue = (String) js.executeScript("return window.promptResult;");
        System.out.println("Entered value: " + enteredValue);
		
		
			
	}
	

	

}
