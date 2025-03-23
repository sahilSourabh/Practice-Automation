package test.CalendarHandlingTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class CalendarDifferentDatePicker extends BaseTest{
	
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		driver.get("https://demo.guru99.com/test/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
//	Selecting date and time into a different type of date picker calendar: DOB Calendar
	@Test
	public void calendarDOBDatePickerHandling() {
		
		WebElement calendarElement = driver.findElement(By.cssSelector("input[type='datetime-local'][name='bdaytime']"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Check if the element is clickable
        try {
            wait.until(ExpectedConditions.elementToBeClickable(calendarElement));
            System.out.println("Element is clickable.");
        } catch (Exception e) {
            System.out.println("Element is NOT clickable.");
        }
        
        calendarElement.sendKeys("21122002"+ Keys.TAB+ "1215");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
		
	}
	

}
