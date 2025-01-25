package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VeggieCart {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		String veggieName = "Brocolli";
		
		WebElement veggie = driver.findElement(
				By.xpath("//h4[contains(text(),'"+veggieName+"')]/following-sibling::div[@class='product-action']/button"));
		veggie.click();
		
		String productPrice = driver.findElement(
				By.xpath("//h4[contains(text(),'"+veggieName+"')]/parent::div/p[@class='product-price']")).getText();
		System.out.println("Price of "+veggieName+": "+productPrice);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By cartAddedText = By.xpath(
				"//h4[contains(text(),'"+veggieName+"')]/following-sibling::div[@class='product-action']/button");
		//wait.until(ExpectedConditions.textToBePresentInElement(cartText, "✔ ADDED"));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(cartAddedText, "✔ ADDED"));
		
		System.out.println(driver.findElement(cartAddedText).getText());
//		System.out.println(driver.findElement(By.xpath("//h4[.='Cauliflower - 1 Kg']/following-sibling::div[@class='product-action']/button")).getText());

		driver.findElement(By.cssSelector(".cart-icon")).click();
		
		Thread.sleep(3000);
		driver.close();

	}

}
