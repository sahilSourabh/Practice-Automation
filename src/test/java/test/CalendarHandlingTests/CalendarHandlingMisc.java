package test.CalendarHandlingTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class CalendarHandlingMisc extends BaseTest {

	String calendarDate ="27-March-2026";
	String[] dateValues = calendarDate.split("-");
	String date= dateValues[0];
	String month= dateValues[1];
	String year = dateValues[2];
	
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {

		driver = initializeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

//	Selecting dates from different type of Date Picker Calendar - Contains Previous and Next Month dates
	@Test
	public void calendarPreviousAndFutureDates() throws InterruptedException {

		driver.get("https://www.path2usa.com/travel-companion/");

		WebElement datePicker = driver.findElement(By.cssSelector("#form-field-travel_comp_date[placeholder='Date of travel']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(datePicker));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", datePicker);
		sleep(2);
		
		datePicker.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flatpickr-innerContainer")));
		
		selectDate(date, month, year);
	
	}
//	Selecting previous month dates and future dates
	public void calendarSelectingPreviousAndFutureDates() {
		
		
		
		
		
	}
	public static void selectDate(String exDate, String exMonth, String exYear) throws InterruptedException {

		String monthVal= driver.findElement(By.xpath("//div[@class='flatpickr-current-month']")).getText();
		WebElement yearElement = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
        String yearVal = yearElement.getAttribute("value");
		
		System.out.println(monthVal+" "+yearVal);

		while (!(monthVal.equals(exMonth) && yearVal.equals(exYear) )) {

			WebElement nextButton = driver.findElement(By.cssSelector(".flatpickr-next-month"));
			nextButton.click();
			
			 monthVal= driver.findElement(By.xpath("//div[@class='flatpickr-current-month']")).getText();
			 yearElement = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
	         yearVal = yearElement.getAttribute("value");
		}

//		.flatpickr-day:not(.prevMonthDay):not(.nextMonthDay):not(.flatpickr-day.disabled)
		driver.findElement(By.xpath("//div[@class='flatpickr-days']//span[@class='flatpickr-day today' or @class='flatpickr-day ']"
				+ "[contains(text(),'"+ exDate + "')]")).click();

	}

}
