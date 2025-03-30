package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class JSExecutorTest extends BaseTest{
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		driver = initializeDriver();
		driver.get("https://classic.freecrm.com/");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
	}
	
	@Test
	public void javascriptExecutorTests() throws InterruptedException {
		
		WebElement loginButton = driver.findElement(By.cssSelector("input[value*='Login']"));
		// Highlighting an element
		changeColor(loginButton, "rgb(0,100,0)");
		// Drawing border around a WebElement
		drawBorderAroundElementTest(loginButton);
		// Custom Alert Generation
		custmAlertGenerationTest("The URL of the page: ");
		// Prompt alert Generation
		customPromptAlertGenerationTest();
		// Get Entire page texts by using JavaScript
		String pageText = getEntirePageInnerTextByJS();
		System.out.println(pageText);
		// Get text of an WebElement
		WebElement eleText = driver.findElement(By.xpath("//h3[contains(.,'Deals & Sales Pipeline')]/following-sibling::p"));
		String elementText = getElementInnerTextByJS(eleText);
		System.out.println(elementText);
		// Scroll down to the bottom of the Page
		scrollPageDown();
		
		
	}
	
	public static void changeColor(WebElement element, String color) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.background='"+color+"';", element);
		sleep(2);	
	}
	
	public static void drawBorderAroundElementTest(WebElement element) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red';", element);	
		sleep(2);
	}
	
	public static void custmAlertGenerationTest(String alert) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('"+ alert +"');");
		js.executeScript("alert('"+ alert +"' + window.location.href);");   //get current page URL
//		js.executeScript("confirm('"+ alert +"');");
		sleep(2);
		driver.switchTo().alert().accept();	
		
	}
	
	public static void customPromptAlertGenerationTest() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("prompt('What is your name?', 'Default Name');");
		sleep(2);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
		String enteredValue = (String) js.executeScript("return window.promptResult;");
        System.out.println("Entered value: " + enteredValue);
	}
	
	public static void refreshBrowserByJS() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");	
		sleep(2);
	}
	
	public static String getEntirePageInnerTextByJS() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Prints all the text mentioned in the page
		String pageText = js.executeScript("return document.documentElement.innerText").toString();	
		sleep(2);
		return pageText;
	}
	
	public static String getElementInnerTextByJS(WebElement element) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Prints all the text mentioned in the page
		String pageText = js.executeScript(" return arguments[0].innerText", element).toString();	
		sleep(2);
		return pageText;
	}
	
	public static void scrollPageDown() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		sleep(2);	
	}

}
