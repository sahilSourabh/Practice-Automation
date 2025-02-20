package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DisabledElementHandling {

public static WebDriver driver; 
	
	@BeforeTest(alwaysRun=true)
	public void setUp() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}

	
	@Test
	public static void disabledTextboxHandling() throws InterruptedException {

		
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
	}

	@Test
	public static void disabledButtonHandling() {
		
		try {
            driver.get("https://letcode.in/button");
 
            WebElement disabledButton = driver.findElement(By.xpath("//button[contains(.,'Disabled')]")); 

			// Check if the button is disabled
			if (disabledButton.getAttribute("disabled") != null) {
				
				System.out.println("Button is disabled.");

				// Enable the button using JavaScript
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].removeAttribute('disabled');", disabledButton);
				Thread.sleep(2000);

				System.out.println("Button has been enabled.");
			} 
			else {
				System.out.println("Button is already enabled.");
			}

			// Click on the button
			disabledButton.click();
			System.out.println("Button clicked.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//@Test
	public static void disabledButtonHandling2() throws InterruptedException {

		driver.get("https://letcode.in/button");
		
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
	            Thread.sleep(2000);	            
	            // Confirm if the button is now enabled
	            System.out.println("Button status after enabling: " + button.isEnabled());

	            // Scroll into view and click the button
	            js.executeScript("arguments[0].scrollIntoView(true);", button);
	            Thread.sleep(1000); // Wait for scrolling animation
	            button.click();
	            System.out.println("Button clicked after enabling.");
	        }
			
		}
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
		
	}

}
