package test.CalendarHandlingTests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerExample {
    public static void main(String[] args) {
     

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.path2usa.com/travel-companion/");
            driver.manage().window().maximize();
    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Click on the Date Of Travel field to open the calendar
            WebElement datePicker = driver
    				.findElement(By.cssSelector("#form-field-travel_comp_date[placeholder='Date of travel']"));
 

    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("arguments[0].scrollIntoView(true);", datePicker);
    		Thread.sleep(2);
    		
    		datePicker.click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flatpickr-innerContainer")));
            // Define the target date
            String targetDateStr = "2023-12-25"; // Format: YYYY-MM-DD
            LocalDate targetDate = LocalDate.parse(targetDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String targetMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));

            // Loop until the correct month and year are found
            while (true) {
                // Get the current month and year displayed on the calendar
                WebElement currentMonthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("flatpickr-current-month")));
                String currentMonthYear = currentMonthYearElement.getText();

                if (currentMonthYear.equals(targetMonthYear)) {
                    break; // Exit the loop if the correct month and year are found
                }

                // Click the "Next" button to go to the next month
                WebElement nextButton = driver.findElement(By.className("flatpickr-next-month"));
                nextButton.click();
            }

            // Select the target date
            List<WebElement> days = driver.findElements(By.cssSelector(".flatpickr-day:not(.prevMonthDay):not(.nextMonthDay)"));
            for (WebElement day : days) {
                if (day.getText().equals(String.valueOf(targetDate.getDayOfMonth()))) {
                    day.click();
                    break;
                }
            }

            // Add any additional actions after selecting the date

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}