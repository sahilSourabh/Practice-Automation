package test.PracticeTests;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MiscTests {

	public WebDriver driver;
	ChromeOptions options;

	@BeforeTest
	public void setUp() {

		options =  new ChromeOptions();
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void flexElementHandling() throws InterruptedException {

		driver.manage().window().setSize(new Dimension(1024,768));
		System.out.println("Height: "+driver.manage().window().getSize().getHeight());
		System.out.println("Width: "+driver.manage().window().getSize().getWidth());
		
		
		driver.get("https://demo.competethemes.com/");

		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		
		//Switching to the frame to access the flex element
		WebElement frameelement = driver.findElement(By.xpath("//*[@id='iframe']"));
		driver.switchTo().frame(frameelement);
		//driver.switchTo().frame("iframe");
		System.out.println("Switch To Frame");
		
		//Click on the Shop Now Button
		driver.findElement(By.xpath("//a[contains(text(),'Shop Now')]")).click();
		System.out.println("Click on the Shop Now button");
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();

	}

	@Test
	public void disabledElementHandling() throws InterruptedException {

		driver.get("https://the-internet.herokuapp.com/dropdown");
		Thread.sleep(2000);

		WebElement dropdownElement = driver.findElement(By.id("dropdown"));
		dropdownElement.click();
		Thread.sleep(2000);
		Select dropdown = new Select(dropdownElement);
		// dropdownElement.selectByValue("1");
		
		SoftAssert softAssert =  new SoftAssert();
		System.out.println("Option 1 Enabled: " + dropdown.getOptions().get(0).isEnabled());
		softAssert.assertTrue(dropdown.getOptions().get(0).isEnabled());

		System.out.println("Option 2 Enabled: " + dropdown.getOptions().get(1).isEnabled());
		Thread.sleep(2000);
		
		WebElement web = dropdown.getOptions().get(0);
		
		//Make the disabled option enabled by changing attributes
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','enabled:enabled:');", web);
		
		System.out.println("After changing the attribute of disabled option :- " );
		System.out.println("Option 1 Enabled: " + dropdown.getOptions().get(1).isEnabled());
		Thread.sleep(1000);
		web.click();
		
		softAssert.assertAll();
	}

	@Test
	public void svgElementHandling() throws InterruptedException {

		driver.get("https://www.saucedemo.com/v1/");

		String username = "standard_user";
		String password = "secret_sauce";

		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();

		String itemPrice = driver.findElement(By.xpath("//div[4]//div[@class='inventory_item_price']")).getText();
		String itemName = driver.findElement(By.xpath("//div[4]//div[@class=\"inventory_item_name\"]")).getText();
		System.out.println("Price of " + "'" + itemName + "'" + ": " + itemPrice);

		driver.findElement(By.cssSelector("div:nth-child(4) button")).click();
		Thread.sleep(2000);

		// Dealing with SVG elements
		 driver.findElement(By.xpath("//*[name()='svg']")).click();
		// driver.findElement(By.id("shopping_cart_container")).click();
		// driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='path' and @fill='currentColor']")).click();
		// driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='path']")).click();
		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();
		Thread.sleep(2000);

	}
	
	@Test
	public void flex() throws InterruptedException {
		
		
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".justify-content-center"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='cell-2-undefined'])[2]"))));
		
		String fruit = driver.findElement(By.xpath("(//div[@id='cell-2-undefined'])[2]")).getText();
		System.out.println("Fruit: "+fruit);
 
		Thread.sleep(1000);
	}
	

	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}

}
