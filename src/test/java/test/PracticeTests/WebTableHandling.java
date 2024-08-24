package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WebTableHandling {
	
	public WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		
		ChromeOptions option =  new ChromeOptions();
		option.addArguments("--headless");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
	}
	
	@Test
	public void getRowsAndColumns() {
		
		driver.get("https://the-internet.herokuapp.com/tables");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement table = driver.findElement(By.cssSelector("#table1"));
		
		List<WebElement> Rows = table.findElements(By.tagName("tr"));
		
		for(WebElement row: Rows) {
			
			List<WebElement> Columns = row.findElements(By.tagName("td"));
			
			for(WebElement column: Columns) {
				
				System.out.print(column.getText()+ "\t");
			}
			System.out.println();
		}
		
	}
	
	@Test
	public void getRowsAndColumnsExceptLast() {
		
		driver.get("https://the-internet.herokuapp.com/tables");
	
		WebElement table = driver.findElement(By.cssSelector("#table1"));
		
		List<WebElement> Rows = table.findElements(By.tagName("tr"));
		
		for(WebElement row: Rows) {
			
			List<WebElement> Columns = row.findElements(By.tagName("td"));
			
		//Getting all Columns except Last column
			for(int i=0; i<Columns.size()-1; i++) {
				
				System.out.print(Columns.get(i).getText()+ "\t");
			}
			System.out.println();
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}

}
