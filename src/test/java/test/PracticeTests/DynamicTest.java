package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DynamicTest {

	public WebDriver driver;

	@BeforeTest(alwaysRun = true)
	public void setUp() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	public void DynamicTest1() {
		try {
			// Open the URL
			driver.get("https://www.saucedemo.com/v1/inventory.html");

			String itemName = "Sauce Labs Bike Light";
			// Find all product elements
			List<WebElement> productElements = driver.findElements(By.className("inventory_item"));

			// Iterate through the products to find "Sauce Labs Bike Light"
			for (WebElement product : productElements) {
				
				String productName = product.findElement(By.className("inventory_item_name")).getText();

				// Check if the product name matches "Sauce Labs Bike Light"
				if (productName.equals(itemName)) {
					
					// Find and print the price of the product
					String productPrice = product.findElement(By.className("inventory_item_price")).getText();
					System.out.println("Price of "+itemName+": " + productPrice);

					// Find and click the "Add to Cart" button for this product
					WebElement addToCartButton = product.findElement(By.xpath(".//button[text()='ADD TO CART']"));
					addToCartButton.click();
					
					System.out.println("Clicked 'Add to Cart' for " + itemName);
					System.out.println();
					break; // Exit the loop once the product is found and clicked
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void DynamicTest2() throws InterruptedException {

		driver.get("https://www.saucedemo.com/v1/");

		String username = "standard_user";
		String password = "secret_sauce";

		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();

		String productName = "Sauce Labs Bike Light";

//		String itemPrice = driver.findElement(By.xpath("//div[text()='" + productName
//				+ "']/parent::a/parent::div/following-sibling::div[@class='pricebar']//div[@class='inventory_item_price']"))
//				.getText();
		String itemPrice = driver
				.findElement(By.xpath("//div[text()='" + productName + "']"
						+ "/parent::a/parent::div/parent::div[@class='inventory_item']//div[@class='pricebar']/div"))
				.getText();
		// String itemPrice =
		// driver.findElement(By.xpath("//div[4]//div[@class='inventory_item_price']")).getText();
		// String itemName =
		// driver.findElement(By.xpath("//div[4]//div[@class='inventory_item_name']")).getText();
		System.out.println("Price of " + "'" + productName + "'" + ": " + itemPrice);

		// Add Product to the Cart

//		driver.findElement(By.xpath(
//				"//div[text()='"+productName+"']/parent::a/parent::div/following-sibling::div//button[.='ADD TO CART']"))
//				.click();

		driver.findElement(By.xpath("//div[text()='" + productName
				+ "']/parent::a/parent::div/parent::div[@class='inventory_item']//div[@class='pricebar']//button[.='ADD TO CART']"))
				.click();

		// Dealing with SVG elements
		WebElement cartButton = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='shopping-cart']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", cartButton);
		Thread.sleep(2000);

		cartButton.click();
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
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();

	}
}
