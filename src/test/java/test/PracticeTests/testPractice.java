package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPractice {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver =  new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/v1/");
		
		String username = "standard_user";
		String password = "secret_sauce";
		
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();
		
		//Dynamically locate product and add to the cart
		String productName = "Sauce Labs Onesie";

		String itemPrice = driver.findElement(By.xpath("//div[text()='"+productName+"']"
				+ "/parent::a/parent::div/parent::div[@class='inventory_item']//div[@class='pricebar']/div"))
				.getText();
		System.out.println("Price of "+productName+" : "+itemPrice);
		
		driver.findElement(By.xpath(
				"//div[text()='"+productName+"']/parent::a/parent::div/following-sibling::div//button[.='ADD TO CART']"))
				.click();
		WebElement cartButton = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='shopping-cart']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",cartButton);
		Thread.sleep(2000);
		
		cartButton.click();

		Thread.sleep(2000);
		driver.close();
	}

}
