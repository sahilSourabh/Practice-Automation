package test.CalendarHandlingTests;

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

public class DynanicCalendarGoIbibo extends BaseTest{
	
	String calendarDate = "9-November-2025";
	String[] dateValues = calendarDate.split("-");
	String date = dateValues[0];
	String month = dateValues[1];
	String year = dateValues[2];

	@BeforeTest(alwaysRun = true)
	public void setUp() {

		driver = initializeDriver();
		driver.get("https://www.goibibo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void selectCalendarDate() throws InterruptedException {

		System.out.println(driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='auth-flow-section']")));
		// Closing the signIn notification
		driver.findElement(By.xpath("//span[@class='sc-koXPp bDtzaf']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='sc-12foipm-16 wfIEw']")));

		//click on Departure
		WebElement departureDate = driver
				.findElement(By.xpath("//div[@class='sc-12foipm-20 jPzQOy']//span[text()='Departure']"));
		departureDate.click();
		//Wait for Calendar to be visible
		WebElement Calendar = driver.findElement(By.cssSelector(".DayPicker-Caption div"));
		wait.until(ExpectedConditions.visibilityOf(Calendar));

		selectDate(date, month, year);

	}

//	public static String[] getMonthYear(String monthYearValue) {
//		return monthYearValue.split(" "); // e.g. March 2025
//	}

	public void selectDate(String exDate, String exMonth, String exYear) throws InterruptedException {

		WebElement monthYearEle = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div"));
		String currentMonthYear = monthYearEle.getText();          //March 2025

//		while ( !(getMonthYear(currentMonthYear)[0].equals(exMonth) && getMonthYear(currentMonthYear)[1].equals(exYear)) )
		while ( !(currentMonthYear.contains(exMonth) && currentMonthYear.contains(exYear)) ) {

			WebElement nextButton = driver.findElement(By.cssSelector(".DayPicker-NavButton--next"));
			nextButton.click();
			currentMonthYear = monthYearEle.getText();
		}
		sleep(2);
		//Selecting the Date
//		driver.findElement(By.xpath("//div[@class='DayPicker-Day']/p[text()='" + exDate + "']")).click();
		List<WebElement> dates = driver.findElements(By.cssSelector(".DayPicker-Day"));
		
		for (WebElement date : dates) {
			
			if (date.getText().equals(exDate) && !date.getAttribute("aria-disabled").equals("true")) {
				date.click();
				break;
			}
		}
	}
	
	@Test
	public void selectCalendarDateByJS() throws InterruptedException {

		System.out.println(driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='auth-flow-section']")));
		// Closing the signIn notification
		driver.findElement(By.xpath("//span[@class='sc-koXPp bDtzaf']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='sc-12foipm-16 wfIEw']")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
//        String script = "document.querySelector(\"p.czGBLf\").innerText = \"18 Mar'25\";";
//        js.executeScript(script);
		

     // Locate the date element inside the calendar
        WebElement dateElement = driver.findElement(By.xpath("//p[contains(@class, 'fswWidgetTitle')]"));
        String newDate = "20 Mar'25";
        js.executeScript("arguments[0].innerText = arguments[1];", dateElement, newDate);
        sleep(2);

	}

}
