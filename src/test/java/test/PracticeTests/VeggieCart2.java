package test.PracticeTests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VeggieCart2 {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

		// To select mutiple items, store the items you wanna select in an array
		// Select multiple items
		// Convert array into arrayList for easy search
		// Check whether product name you extracted is present in array or not
		// Add items to cart
		// Verify how many times code is running inside loop for break statement
		// Click the "Proceed" button without selecting the checkbox first and handle the error.
		// If the checkbox is not selected then Select the checkbox and successfully click the "Proceed" button.
		try {
			String[] veggieNames = { "Carrot", "Pumpkin", "Beetroot" };

			List<String> products = Arrays.asList(veggieNames);
			int veggieArraySize = products.size();
			int count = 0;

			List<WebElement> listItems = driver.findElements(By.xpath("//h4[@class='product-name']"));

			for (WebElement p : listItems) {

				String[] productName = p.getText().split("-");
				String formattedProductName = productName[0].trim();

				if (products.contains(formattedProductName)) {

					count++;
					
					// Selecting Quantity for each veggie
					WebElement productQuantityIncrease = driver.findElement(By.xpath("//h4[contains(text(),'"+formattedProductName
									+"')]/parent::div/div[@class='stepper-input']/a[@class='increment']"));

					for (int i=1; i<4; i++) {

						productQuantityIncrease.click();
					}
					WebElement veggiesCart = driver.findElement(By.xpath("//h4[contains(text(),'" + formattedProductName
							+ "')]/following-sibling::div[@class='product-action']/button"));
					veggiesCart.click();

					// Wait for all elements to be added
//				By cartAddedText = By.xpath(
//						"//h4[contains(text(),'"+formattedProductName+"')]/following-sibling::div[@class='product-action']/button");
//				wait.until(ExpectedConditions.invisibilityOfElementWithText(cartAddedText, "✔ ADDED"));
//				
					if (count == veggieArraySize) {
						break;
					}
				}
			}

			driver.findElement(By.cssSelector(".cart-icon")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.='PROCEED TO CHECKOUT']")).click();
			// Wait till promoCode textbox is visible
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".promoCode"))));

			driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
			driver.findElement(By.cssSelector(".promoBtn")).click();
			// Wait till code is applied
			By promoText = By.cssSelector(".promoInfo");
			wait.until(ExpectedConditions.visibilityOfElementLocated(promoText));

			driver.findElement(By.xpath("//button[text()='Place Order']")).click();

			WebElement dropdown = driver.findElement(By.cssSelector(".wrapperTwo div select"));
			wait.until(ExpectedConditions.elementToBeClickable(dropdown));

			Select options = new Select(dropdown);
			options.selectByValue("India");

			WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox' and @class='chkAgree']"));
			WebElement proceedButton = driver.findElement(By.cssSelector(".wrapperTwo button"));

			// Scenario 1: Attempt to click "Proceed" without selecting the checkbox
			//checkBox.click();
			proceedButton.click();
			
			Thread.sleep(2000);

			// Check if an error message is displayed
			try {
				WebElement errorMessage = driver.findElement(By.xpath("//span[@class='errorAlert']/b"));
				if (errorMessage.isDisplayed()) {

					System.out.println("Error message: " + errorMessage.getText());
				}

			} catch (Exception e) {
				System.out.println("No Error message displayed");

			}
			// Scenario 2: If checkbox is not selected -> Select the checkbox and click on "Proceed" button
			if (!checkBox.isSelected()) {
				checkBox.click();
				System.out.println("checkbox selected.");
			}
			Thread.sleep(1000);
			proceedButton.click();
			
		} catch (Exception e) {
			System.out.println("Error message: "+e.getMessage());

		} finally {

			//Thread.sleep(2000);
			driver.quit();
		}
	}

}
