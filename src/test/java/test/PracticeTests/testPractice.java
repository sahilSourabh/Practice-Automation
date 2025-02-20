package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testPractice {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		try {
            // Open the webpage
            driver.get("https://letcode.in/button");

            // Locate the disabled button
            WebElement disabledButton = driver.findElement(By.xpath("//button[contains(.,'Disabled')]")); // Replace "disabled" with the actual ID or locator of the button

            // Check if the button is disabled
            if (disabledButton.getAttribute("disabled") != null) {
                System.out.println("Button is disabled.");

                // Enable the button using JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].removeAttribute('disabled');", disabledButton);
                Thread.sleep(2000);

                System.out.println("Button has been enabled.");
            } else {
                System.out.println("Button is already enabled.");
            }

            // Click on the button
            disabledButton.click();
            System.out.println("Button clicked.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
			
		


	}
}
