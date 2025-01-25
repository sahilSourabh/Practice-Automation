package test.PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragandDropTest {

	public WebDriver driver; 
	
	@BeforeTest(alwaysRun=true)
	public void setUp() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
	
	@Test
	public void DragandDrop() throws InterruptedException {
		
		driver.get("https://letcode.in/sortable");
		
		WebElement src = driver.findElement(By.xpath("//div[contains(text(),'Go home')]"));
		WebElement dest = driver.findElement(By.xpath("//div[contains(text(),'Brush teeth')]"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//h1[contains(text(),'Sort')]")));
		
		Thread.sleep(1000);
		
		Point point =  dest.getLocation();
		int x= point.getX();
		int y= point.getY();
		
		System.out.println("Source Location: "+ src.getLocation());
		System.out.println("Destination Location: "+ dest.getLocation());
		System.out.println(x+" "+y);
		
		Actions action = new Actions(driver);
		
		action.clickAndHold(src).pause(Duration.ofSeconds(2))
		.moveToElement(dest, x, y).pause(Duration.ofSeconds(2))
		.pause(Duration.ofSeconds(2)).release(dest).build().perform();
		
//		action.dragAndDrop(src, dest).build().perform();
		
//		action.dragAndDropBy(src, point.getX(), point.getY()).build().perform();
		
	}
	
	@Test
	public void DragandDrop2() {
		
		driver.get("https://dhtmlx.com/docs/products/dhtmlxTree/");
		
		WebElement frameElement = driver.findElement(By.cssSelector(".js-iframe.active"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", frameElement);
		
		driver.switchTo().frame(frameElement);
		
		WebElement src = driver.findElement(By.xpath("//span[text()='Lawrence Block']"));
		WebElement dest = driver.findElement(By.xpath("//li[text()='Ajax in Practice']"));
		
		Actions action = new Actions(driver);
		
		action.clickAndHold(src).pause(Duration.ofSeconds(2))
		.moveToElement(dest).pause(Duration.ofSeconds(2)).
		release().build().perform();
		
		//action.dragAndDrop(src, dest).build().perform();
		
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
		
	}

}
