package test.PracticeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.saucedemo.com/v1/");
		
		String username = "standard_user";
		String password = "secret_sauce";
		
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();
		
		String itemPrice = driver.findElement(By.xpath("//div[4]//div[@class='inventory_item_price']")).getText();
		String itemName = driver.findElement(By.xpath("//div[4]//div[@class=\"inventory_item_name\"]")).getText();
		System.out.println("Price of "+"'"+itemName+"'"+": "+ itemPrice);
		
		driver.findElement(By.cssSelector("div:nth-child(4) button")).click();
		
		//Dealing with SVG elements
		driver.findElement(By.xpath("//*[name()='svg']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Sourabh");
		driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Sahil");
		driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("560011");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn_action.cart_button")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector(".complete-header")).getText());
		driver.close();
		
		
		
		
		
	}

}
