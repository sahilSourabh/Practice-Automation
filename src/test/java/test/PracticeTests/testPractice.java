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
		driver.get("https://seleniumpractise.blogspot.com/search?q=calendar");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void handlingDynamicCalendarJS() throws InterruptedException {
		
		WebElement date = driver.findElement(By.id("datepicker"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String dateValue = "18/10/2025";
		js.executeScript("arguments[0].value=arguments[1];", date,dateValue); 
		sleep(2);
		driver.findElement(By.xpath("//input[@id='datepicker' and @class='hasDatepicker']")).click();
		
			
	}
	

	

}
