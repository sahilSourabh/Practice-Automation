package test.PracticeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DisabledElementHandling {

	public static WebDriver driver = new ChromeDriver();

	
	@Test
	public static void disabledTextboxHandling() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get("https://selectorshub.com/xpath-practice-page/");

		WebElement FirstName = driver
				.findElement(By.xpath("//input[@class='nameFld' and @placeholder='First Enter name']"));

		System.out.println("Textbox initially enabled: " + FirstName.isEnabled());

		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("document.querySelector(\"input[placeholder='First Enter name']\").disabled=false");
		js.executeScript("document.querySelector(\"input[placeholder='First Enter name']\").removeAttribute('disabled')");

		System.out.println("Textbox enabled: " + FirstName.isEnabled());
		
		if (FirstName.isEnabled()) {

			FirstName.sendKeys("Sourabh");
		}
		
		String boxSizeCssValue = FirstName.getCssValue("box-sizing");
		String fontFamilyCssValue = FirstName.getCssValue("font-family");
		System.out.println("Box Size: "+ boxSizeCssValue);
		System.out.println("Font Family: "+ fontFamilyCssValue);

		Thread.sleep(2000);
		driver.close();
	}

	@Test
	public static void disabledButtonHandling() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get("https://letcode.in/buttons");
		//
		WebElement button = driver.findElement(By.xpath("//button[contains(.,'Disabled')]"));

		System.out.println("Button enabled: " + button.isEnabled());

		
		try {
			
				button.click();
				System.out.println("Button Clicked Successfully");
		} 
		catch (ElementClickInterceptedException e) {
			System.out.println("Error Message: "+e.getMessage());
			
			if (!button.isEnabled()) {
	            // If the button is disabled, enable it using JavascriptExecutor
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("document.querySelector(\"button[title='Disabled button']\").disabled=false");
//	            js.executeScript("document.querySelector(\"button[title='Disabled button']\").removeAttribute('disabled')");

	            // Confirm if the button is now enabled
	            System.out.println("Button status after enabling: " + button.isEnabled());

	            // Scroll into view and click the button
	            js.executeScript("arguments[0].scrollIntoView(true);", button);
	            Thread.sleep(1000); // Wait for scrolling animation
	            button.click();
	            System.out.println("Button clicked after enabling.");
	        }
			
		}
		Thread.sleep(2000);
		driver.close();
		
		
	}

}
