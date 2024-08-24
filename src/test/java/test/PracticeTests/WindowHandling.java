package test.PracticeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowHandling {
	
	@Test
	public void newWindowHandling() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com/");
		Thread.sleep(1000);
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.navigate().to("https://rahulshettyacademy.com/");
		driver.findElement(By.xpath("(//a[@href='practice-project'])[1]")).click();
		
		Thread.sleep(500);
		
		driver.quit();
	}

}
