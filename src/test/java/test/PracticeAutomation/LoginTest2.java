package test.PracticeAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LoginTest2 {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds('5'));
		driver.manage().window().maximize();
		SoftAssert softAssert = new SoftAssert();
		
		String username = "sourabhsahil";
		String password = getPassword(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys(username);
		driver.findElement(By.cssSelector(".form-container input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("#chkboxOne")).click();
		driver.findElement(By.xpath("(//div[@class='checkbox-container']//input)[2]")).click();

		driver.findElement(By.cssSelector(".submit.signInBtn")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1/following-sibling::p[contains(.,'You are successfully logged in.')]")));

		//Welcome Text Username Validation
		WebElement welcomeText = driver.findElement(By.cssSelector("h2"));
		System.out.println("Introductory text : " + welcomeText.getText());
		softAssert.assertTrue(welcomeText.getText().contains(username));
		
		String actual = driver
				.findElement(By.xpath("//h1/following-sibling::p[contains(.,'logged in.')]"))
				.getText();
		String expected = "You are successfully logged in.";

		softAssert.assertEquals(actual, expected);
		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".logout-btn")).click();

		Thread.sleep(2000);
		
		driver.close();
		softAssert.assertAll();

	}
	
	public static String getPassword(WebDriver driver)
	{
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.linkText("Forgot your password?")).click();	
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form//h2[.='Forgot password']"))));
		
		driver.findElement(By.xpath("//form//input[contains(@placeholder,'Name')]")).sendKeys("Sourabh Sahil");
		driver.findElement(By.xpath("//form//input[contains(@placeholder,'Email')]")).sendKeys("sahil@gmail.com");
		driver.findElement(By.xpath("//form//input[contains(@placeholder,'Phone')]")).sendKeys("1234567890");
		driver.findElement(By.xpath("//form//div//button[@class='reset-pwd-btn']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("form .infoMsg"))));
		
		String pwd = driver.findElement(By.cssSelector("form .infoMsg")).getText();
		
		String[] pwdStr = pwd.split("'");
		String password = pwdStr[1].split("'")[0];
		
		return password;
		
	}

}
