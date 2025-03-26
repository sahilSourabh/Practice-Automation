package test.CalendarHandlingTests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class CalendarDifferentTypesHandling extends BaseTest{
	
	String calendarDate ="25-March-2025";
	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM");

    LocalDate date = LocalDate.parse(calendarDate, inputFormatter);
	int eDay= date.getDayOfMonth();
//	String fullMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	String month = date.format(outputFormatter);    //format(outputFormatter) uses the "MMM" format, converting "December" into "Dec".
	int eYear = date.getYear();
	String day = String.valueOf(eDay);
	String year = String.valueOf(eYear);
	
//	Month monthEnum = Month.valueOf(fullMonth.toUpperCase());
//	String month = monthEnum.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	
	@BeforeTest(alwaysRun = true)
	public static void setUp() {
		
		driver = initializeDriver();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		
	}
	
	@Test
	public void calendarHandlingFourthDate() throws InterruptedException {
		
		driver.findElement(By.cssSelector("#fourth_date_picker")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-datepicker-div")));
		
		selectCalendarMonth(month);
		selectCalendarYear(String.valueOf(year));
		sleep(2);
		
		driver.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='"+day+"']")).click();
		
//		List<WebElement> dateList = driver.findElements(By.xpath(
//				"//table[@class='ui-datepicker-calendar']//tbody/tr/td[not(contains(@class,'ui-datepicker-other-month'))]"));
//		
//		for(WebElement date: dateList) {
//			
//			if(date.getText().equals(day)) {
//				date.click();
//				break;
//			}
//		}
		String calendarDateValue = driver.findElement(By.cssSelector("#fourth_date_picker")).getAttribute("value");
		System.out.println(calendarDateValue);
		
	}
	
	@Test
	public void calendarHandlingFifthDate() throws InterruptedException {
		
		WebElement fifthDate = driver.findElement(By.id("fifth_date_picker"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("sixth_date_picker")));
		sleep(2);
		fifthDate.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-datepicker-div")));
		
		String monthYear = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
		String aMonth = getMonthYear(monthYear)[0];
		String aYear = getMonthYear(monthYear)[1];
		
		String fullMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		
		while( !(aMonth.equals(fullMonth) && aYear.equals(year))) {
			
			WebElement nextButton = driver.findElement(By.cssSelector("a[class*='ui-datepicker-next']"));
			nextButton.click();
			
			monthYear = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
			aMonth = getMonthYear(monthYear)[0];
			aYear = getMonthYear(monthYear)[1];
			
		}
		
		WebElement dateElement = driver.findElement(
				By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='"+ day +"']"));
//		dateElement.click();
		js.executeScript("arguments[0].click();",dateElement );
		
		String calendarDateValue = driver.findElement(By.cssSelector("#fifth_date_picker")).getAttribute("value");
		System.out.println(calendarDateValue);
		
	}
	
	public static String[] getMonthYear(String mnthYr) {
		
		return mnthYr.split(" ");
	}
	
	public static void selectCalendarYear(String year) throws InterruptedException {

		WebElement yrDropdown = driver.findElement(By.cssSelector(".ui-datepicker-year"));
		selectByValue(yrDropdown, year);
		sleep(2);
	}

	public static void selectCalendarMonth(String month) throws InterruptedException {

		WebElement mnthDropdown = driver.findElement(By.cssSelector(".ui-datepicker-month"));
		selectByVisibleText(mnthDropdown, month);
		sleep(2);
	}

}
