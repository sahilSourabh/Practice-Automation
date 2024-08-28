package test.PracticeTests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String fruit ="Kivi";
		
		driver.get("https://rahulshettyacademy.com/upload-download-test/");
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".justify-content-center"))));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='cell-2-undefined'])[2]"))));
		
		//Download File
		//driver.findElement(By.cssSelector("#downloadButton")).click();
		System.out.println("File Downloaded Successfully");
		
		//Upload File
		driver.findElement(By.id("fileinput")).sendKeys("C:\\Users\\Sourabh Sahil\\Downloads\\download.xlsx");
		
		//Wait for Success messsage to Appear and Dissappear
		WebElement uploadSuccessText = driver.findElement(By.cssSelector(".Toastify__toast-body"));
		wait.until(ExpectedConditions.visibilityOf(uploadSuccessText));
		System.out.println(uploadSuccessText.getText());
		Assert.assertEquals("Updated Excel Data Successfully.", uploadSuccessText.getText());
		wait.until(ExpectedConditions.invisibilityOf(uploadSuccessText));
		
		//Get Price of Particular Fruit Dynamically
		String priceSection = driver.findElement(By.xpath("//div[text()='Price']/parent::div")).getAttribute("data-column-id");
		
		String actualPrice = driver
				.findElement(By.xpath("//div[text()='"+fruit+"']/parent::div/parent::div/div[@id='cell-"+priceSection+"-undefined']"))
				.getText();
		System.out.println("Price of "+fruit+": "+actualPrice);
		
		
		
		Thread.sleep(1000);
		driver.quit();
	}

}
