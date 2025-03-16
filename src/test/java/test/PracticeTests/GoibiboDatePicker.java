package test.PracticeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoibiboDatePicker {
	public static void main(String[] args) throws InterruptedException {

		// Initialize the WebDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to the Goibibo website
		driver.get("https://www.goibibo.com/");

		// Wait for the page to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='auth-flow-section']")));
		//Closing the signIn notification
		driver.findElement(By.xpath("//span[@class='sc-koXPp bDtzaf']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='sc-12foipm-16 wfIEw']")));
		
		
		// Click on the Departure date field
		WebElement departureDate = driver.findElement(By.xpath("//div[@class='sc-12foipm-20 jPzQOy']//span[text()='Departure']"));
		departureDate.click();

		// Define the target month and year
		String targetMonth = "December";
		String targetYear = "2025";
		String targetDate = "25";

		// Loop until the desired month and year are found
		while (true) {
			// Get the current month and year displayed on the calendar
			WebElement currentMonthYearElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".DayPicker-Caption div")));
			String currentMonthYear = currentMonthYearElement.getText();

			// Check if the current month and year match the target
			if (currentMonthYear.contains(targetMonth) && currentMonthYear.contains(targetYear)) {
				break;
			}

			// Click the "Next" button to go to the next month
			WebElement nextButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".DayPicker-NavButton--next")));
			nextButton.click();
		}

		// Select the target date
		List<WebElement> dates = driver.findElements(By.cssSelector(".DayPicker-Day"));
		for (WebElement date : dates) {
			if (date.getText().equals(targetDate) && !date.getAttribute("aria-disabled").equals("true")) {
				date.click();
				break;
			}
		}

		// Close the browser
		Thread.sleep(2000);
		driver.quit();
	}
}
