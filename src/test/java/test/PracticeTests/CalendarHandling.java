package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class CalendarHandling extends BaseTest{
	
	WebDriver driver;
	String calendarDate ="21-December-2002";
	String[] dateValues = calendarDate.split("-");
	String day= dateValues[0];
	String month= dateValues[1];
	String year = dateValues[2];
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		driver.get("https://classic.freecrm.com/");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
		driver.findElement(By.cssSelector("input[value*='Login']")).click();
		driver.switchTo().frame("mainpanel");
	}
	
	@Test
	public void handlingCalendar() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Calendar']")).click();
		sleep(2);
		
		selectCalendarMonth(month);
		selectCalendarYear(year);
		//Store all the dates in a list and iterate to select a particular date
		List<WebElement> dayValues = driver
				.findElements(By.xpath("//div[@id='crmcalendar']/table[@class='crmcalendar']//td/table/tbody/tr/td"));
		
		for(WebElement dayVal: dayValues) {
			
			if(dayVal.getText().equalsIgnoreCase(day)) {
				dayVal.click();
				sleep(2);
			}
		}	
	}
	
	@Test
	public void handlingCalendar2() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Calendar']")).click();
		sleep(2);
		selectCalendarMonth(month);
		selectCalendarYear(year);
//		String beforeXpath = "//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[";
//		String aftereXpath = "]/td[";
		
		int totalWeekDays = 7;
		boolean flag = false;
		WebElement dateElement;
		String dateValue="";
		
		for(int rowNum=2; rowNum<=7; rowNum++) {
			
			for(int column=1; column<=totalWeekDays; column++) {
				
				try {
//					WebElement dateElement = driver.findElement(By.xpath(beforeXpath+ rowNum +aftereXpath+ column +"]"));
					dateElement = driver.findElement
							(By.xpath("//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr["+rowNum+"]/td["+column+ "]"));
					dateValue = dateElement.getText();
				} 
				catch(NoSuchElementException e) {
					System.out.println("The date is not present, please enter correct date value");
					flag=false;
					break;
				}
				
				if(dateValue.equals(day)) {
					
					dateElement.click();
					flag=true;
					break;
				}	
			}
			if(flag) {
				break;
			}
		}	
	}
	
}
