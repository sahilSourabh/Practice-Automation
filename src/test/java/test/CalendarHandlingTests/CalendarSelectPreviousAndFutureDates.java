package test.CalendarHandlingTests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class CalendarSelectPreviousAndFutureDates extends BaseTest{

	String calendarDate = "06-07-2026";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate eDate = LocalDate.parse(calendarDate, formatter);
	int date = eDate.getDayOfMonth();
	int month = eDate.getMonthValue();
	int year = eDate.getYear();
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		driver.get("https://seleniumpractise.blogspot.com/search?q=calendar");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
//	Selecting previous month dates and future dates
	@Test
	public void calendarSelectingPreviousAndFutureDates() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='datepicker' and @class='hasDatepicker']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-datepicker-div"))));
		
		selectDate(date, month, year);
		
		
	}
	
	
	public static String[] getMonthYear(String monthYearValue) {
		return monthYearValue.split(" ");    //e.g. March 2025
	}
	
	public static void selectDate(int exDate, int exMonth, int exYear) throws InterruptedException {
		
		String monthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		
		String aMonthText = getMonthYear(monthYear)[0];
		//Convert Month into Integer value
//		int aMonth = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH).parse(aMonthText).get(ChronoField.MONTH_OF_YEAR);
		int aMonth = Month.valueOf(aMonthText.toUpperCase()).getValue();
		
		String aYearText = getMonthYear(monthYear)[1];
		int aYear = Integer.parseInt(aYearText);
		
		while(exMonth < aMonth || exYear < aYear) {
			
			WebElement prevButton = driver.findElement(By.cssSelector("a[class*='ui-datepicker-prev'][title='Prev']"));
			prevButton.click();
			
			monthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			aMonthText = getMonthYear(monthYear)[0];
//			aMonth = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH).parse(aMonthText).get(ChronoField.MONTH_OF_YEAR);
			aMonth = Month.valueOf(aMonthText.toUpperCase()).getValue();
			aYearText = getMonthYear(monthYear)[1];
			aYear = Integer.parseInt(aYearText);
		}
		
		while (exMonth > aMonth || exYear > aYear) {

			WebElement nextButton = driver.findElement(By.cssSelector("a[class*='ui-datepicker-next'][title='Next']"));
			nextButton.click();
			
			monthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			aMonthText = getMonthYear(monthYear)[0];
//			aMonth = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH).parse(aMonthText).get(ChronoField.MONTH_OF_YEAR);
			aMonth = Month.valueOf(aMonthText.toUpperCase()).getValue();
			aYearText = getMonthYear(monthYear)[1];
			aYear = Integer.parseInt(aYearText);
		}
		
		driver.findElement(By.xpath("//a[text()='"+exDate+"']")).click();
	}
	
	

	
}
