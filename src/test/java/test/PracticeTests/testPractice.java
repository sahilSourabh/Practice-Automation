package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
		driver.get("https://selectorshub.com/xpath-practice-page/");

		WebElement FirstName = driver
				.findElement(By.xpath("//input[@class='nameFld' and @placeholder='First Enter name']"));
		
		if(!FirstName.isEnabled()) {
			
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("document.querySelector(\"input[placeholder='First Enter name']\").removeAttribute('disabled')");
			Thread.sleep(1000);
			FirstName.sendKeys("Sourabh");
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions);
		
		
		
		
		
		Thread.sleep(2000);
		driver.close();
	}

}
