package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testPractice {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();


		        try {
		            // Open the URL
		            driver.get("https://www.saucedemo.com/v1/inventory.html");

		            // Find all product elements
		            List<WebElement> productElements = driver.findElements(By.className("inventory_item"));

		            // Iterate through the products to find "Sauce Labs Bike Light"
		            for (WebElement product : productElements) {
		                String productName = product.findElement(By.className("inventory_item_name")).getText();

		                // Check if the product name matches "Sauce Labs Bike Light"
		                if (productName.equals("Sauce Labs Fleece Jacket")) {
		                    // Find and print the price of the product
		                    String productPrice = product.findElement(By.className("inventory_item_price")).getText();
		                    System.out.println("Price of Sauce Labs Bike Light: " + productPrice);

		                    // Find and click the "Add to Cart" button for this product
		                    WebElement addToCartButton = product.findElement(By.xpath(".//button[text()='ADD TO CART']"));
		                    addToCartButton.click();
		                    System.out.println("Clicked 'Add to Cart' for Sauce Labs Bike Light");
		                    break; // Exit the loop once the product is found and clicked
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            // Close the browser
		        	Thread.sleep(2000);
		            driver.quit();
		        }
		    }
		}
