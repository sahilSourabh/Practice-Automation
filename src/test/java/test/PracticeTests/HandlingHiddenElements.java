package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingHiddenElements {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://sripriyakulkarni.com/");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement automationPracticeView = driver.findElement(By.xpath("//span[normalize-space()='Automation Practice']"));
		
		// Wait for the link to be click-able and Navigate to Automation Practice
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(automationPracticeView)).click();
		
		//Scrolling to the Automation
		/*js.executeScript("arguments[0].scrollIntoView(true)", automationPracticeView);
		automationPracticeView.click(); */
		Thread.sleep(1000); 

		// click on Hide Button
		driver.findElement(By.cssSelector("#hide-textbox")).click();

		// Changing attribute to make the textbox Visible
		WebElement textBox = driver.findElement(By.cssSelector(".inputs.displayed-class"));
		
		js.executeScript("arguments[0].setAttribute('style','visibility:visible:');", textBox);

		// Enter the text in the Box
		
		//textBox.sendKeys("Test Testing !!");
		//js.executeScript("arguments[0].value=arguments[1];", textBox, "Test Testing !!");
		js.executeScript("document.getElementById('displayed-text').value='Test Testing !!';");
		
		//Get the text entered in the box
		System.out.println("Entered text in box: "+ driver.findElement(By.id("displayed-text")).getAttribute("value"));
		Thread.sleep(2000);
		driver.quit();

	}

}
