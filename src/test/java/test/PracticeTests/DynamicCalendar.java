package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class DynamicCalendar extends BaseTest {

	String calendarDate ="15-December-2025";
	String[] dateValues = calendarDate.split("-");
	String date= dateValues[0];
	String month= dateValues[1];
	String year = dateValues[2];
	
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		driver.get("https://seleniumpractise.blogspot.com/search?q=calendar");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void handlingDynamicCalendar() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='datepicker' and @class='hasDatepicker']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-datepicker-div"))));
		
		selectDate(date, month, year);	
	}
	
	
	public static String[] getMonthYear(String monthYearValue) {
		return monthYearValue.split(" ");    //e.g. March 2025
	}
	
	public static void selectDate(String exDate, String exMonth, String exYear) throws InterruptedException {
		
		if(exMonth.equals("February") && Integer.parseInt(exDate) >29 ) {
			System.out.println("Wrong date :"+exDate+"-"+exMonth);
			return;	
		}
		
		if(Integer.parseInt(exDate) > 31) {
			System.out.println("Wrong date :"+exDate+"-"+exMonth);
			return;
		}

		String monthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		
		while ( !(getMonthYear(monthYear)[0].equals(exMonth) && getMonthYear(monthYear)[1].equals(exYear))) {

			WebElement nextButton = driver.findElement(By.cssSelector("a[class*='ui-datepicker-next'][title='Next']"));
			nextButton.click();
			monthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		}
		
		driver.findElement(By.xpath("//a[text()='"+exDate+"']")).click();
	}
}
