package test.TestComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;

public class BasePage {
	
	public static WebDriver driver;  // Changed from static to instance variable

	public static WebDriver initializeDriver() {

		driver = new ChromeDriver();
//		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
//		driver.quit();
		if (driver != null) {
	        driver.quit();
	        driver = null;   // Prevent using stale driver reference
	    }
	}

}
