package test.PracticeTests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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
	public void updatedWebTableHandling() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/upload-download-test/");

		String fruitName = "Apple";
		
		//Get rows and columns
		System.out.println("\n"+"WebTable: "+"\n");
		WebElement table = driver.findElement(By.cssSelector(".sc-dmXWDj.iSwdeS"));
		
		List<WebElement> Rows = table.findElements(By.cssSelector(".sc-jsEeTM.itluUR.rdt_TableRow"));
		
		for(WebElement row: Rows)
		{
			List<WebElement> Columns = row.findElements(By.cssSelector(".sc-hLQSwg.sc-eDLKkx.sc-jTQCzO"));
			
			for(WebElement col: Columns)
			{
				System.out.print(col.getText()+"\t");
			}
			System.out.println();
		}
		
		// Get a Particular Column items
		
		/*Steps:
		1) Get the CSS/xpath for Entire table
		2) Navigate to first row
		3) Navigate to any column of the row 
		4) Get the list of items in that column using :nth-child() in CSS and index traversing in Xpath
		5) Then we can get to other Columns from the current Column using following-sibling:: in xpath. */
		
		List<WebElement> list = driver.findElements(By.cssSelector(".sc-hIPBNq.eXWrwD div div:nth-child(2)"));

		List<String> fruitList = list.stream().map(s -> s.getText()).collect(Collectors.toList());

		System.out.println("\n" + "Fruits :" + "\n");
		fruitList.forEach(s->System.out.println(s));
		
		//Get the Price of a Particular Fruit
		List<String> newList = list.stream().filter(s -> s.getText().contains(fruitName)).map(s -> getVeggiePrice(s))
				.collect(Collectors.toList());
		newList.forEach(s->System.out.println("\n"+"Price of "+fruitName+": "+s));
		
		
	}
	
	private static String getVeggiePrice(WebElement s) {
		
//		WebElement price = driver.findElements(By.xpath
//				("//div[contains(@class,'sc-hIPBNq eXWrwD')]/div/div[2]/following-sibling::div[2]"));
		String price = s.findElement(By.xpath("following-sibling::div[2]")).getText();
		return price;
	}

	@Test
	public void getRowsAndColumns() {
		
		driver.get("https://the-internet.herokuapp.com/tables");
		
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
