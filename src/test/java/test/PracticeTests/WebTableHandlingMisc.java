package test.PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class WebTableHandlingMisc extends BaseTest {

	String columnName = "Discount Price";
	String itemName = "Rice";
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("w3c", true);  // Forces W3C mode
		options.addArguments("headless");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");	
	}
	// Print all the rows and columns values
	// Check if a particular column is present in the web table and print all its values .
	// Print column values for heading row
	// Search a particular item in the web table and print all its column values for the row
	/* check if some particular item is present or not, if item is present 
	   then we will print the value of a Particular column for that item. */
	// Search for particular item in all pages of Web Table and print its certain column value (Click Next until item is found)

	@Test
	public void getRowsAndColumns() throws InterruptedException {

		List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
		
		for(WebElement row: Rows) {
			// Starts with a dot (.) to indicate it should search relative to the current row element
			List<WebElement> Columns = row.findElements(By.xpath(".//td"));
			
			for(WebElement column: Columns) {
				System.out.print(column.getText()+ "\t");
			}
			System.out.println();
		}	
		
	}
	
	@Test
	public void getParticularColumnValues() {
		
		WebElement headerRow = driver.findElement(By.xpath("//table[@class='table table-bordered']/thead/tr"));
		List<WebElement> headerColumns = headerRow.findElements(By.xpath(".//th"));
		int columnIndex = -1, k=0;
		
		for(WebElement headerCell:headerColumns ) {
			
			if(headerCell.getText().equalsIgnoreCase(columnName)) {
				columnIndex = k;
				break;	
			}
			k++;
		}
		
		if(columnIndex==-1) {
			System.out.println("column is not present in the table");
			return;
		}
		System.out.println("Column index for "+columnName+": "+ (columnIndex+1));
		System.out.println("Column values for "+columnName+": ");
		
		//Getting column values of a particular column from every rows
		List<WebElement> particularColValues = driver.findElements(
				By.xpath("//table[@class='table table-bordered']/tbody/tr/td["+(columnIndex+1)+"]"));
		
		for(WebElement value:particularColValues ) {
			System.out.println(value.getText());
		}
		
//		List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
//		
//		for(WebElement row: Rows) {
//			
//			WebElement Columns = row.findElement(By.xpath(".//td["+columnIndex+"]"));
//			System.out.println(Columns.getText()+"\t");		
//		}	
	}	
	
	@Test
	public void getHeadingRowValues() {
		
		List<WebElement> headerColumns = driver.findElements(By.xpath("//table[@class='table table-bordered']/thead/tr/th"));
		
		System.out.println("Heading Column Values:");
		for(WebElement headerCells: headerColumns) {
			System.out.print(headerCells.getText()+"\t");
		}
		System.out.println();
	}
	
	@Test
	public void getColumnValuesForParticularRow() {
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
		int rowIndex = 1;
		for(WebElement row: rows) {
			
			List<WebElement> cellValues = row.findElements(By.xpath(".//td"));
			for(WebElement cell: cellValues) {
				
				if(cell.getText().equalsIgnoreCase(itemName)) {
					System.out.println(itemName+" is found at index: "+rowIndex);
					
					for(WebElement cells: cellValues) {
						System.out.print(cells.getText()+"\t");
					}
				}	
			}
			rowIndex++;
		}
		
	}
	
	@Test
	public void getParticularColumnValuesForItem() {
		
		// Find the header row to get the column index
		WebElement headerRow = driver.findElement(By.xpath("//table[@class='table table-bordered']/thead/tr"));		
		List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
		int columnIndex=-1, k=0;
		// 0-based indexing
		for(WebElement headerCell: headerCells) {
			if(headerCell.getText().equalsIgnoreCase(columnName)) {
				columnIndex=k;
				break;
			}
			k++;
		}
		
		if(columnIndex==-1) {
			System.out.println("The column is not present in the table");
			return;	
		}
		// Column index value will be (columnIndex+1) since its following 0-based indexing
		System.out.println(columnName+" is found at Column index: "+ (columnIndex+1) );
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
		int rowIndex = 1;
		for(WebElement row: rows) {
			
			List<WebElement> cellValues = row.findElements(By.xpath("td"));
			for(WebElement cell: cellValues) {
				
				if(cell.getText().equalsIgnoreCase(itemName)) {
					System.out.println(itemName+" is found at Row index: "+ rowIndex);
					// Print the value of the target column
					if(columnIndex < cellValues.size()) {
						
//						WebElement priceCell = row.findElement(By.xpath("td["+(columnIndex+1)+"]"));
//						String columnValue = priceCell.getText();
						String columnValue = cellValues.get(columnIndex).getText();
						System.out.println(columnName+" Column value for "+itemName+": "+ columnValue);
					}
					else {
						System.out.println("Column index is out of bounds");
					}
			}
			
		}rowIndex++;
	}	
}
	
	@Test
	public void searchItemInAllPages() throws InterruptedException {

		driver.findElement(By.xpath("//table[@class='table table-bordered']/thead/tr/th[1]")).click();
		boolean itemFound = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement nextButton = driver.findElement(By.cssSelector("a[aria-label='Next']"));
	
		while(true) {
			
			List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
			for (WebElement row : rows) {

				List<WebElement> columns = row.findElements(By.xpath("td[1]"));
				for (WebElement column : columns) {

					if (column.getText().equalsIgnoreCase(itemName)) {

						WebElement priceCell = row.findElement(By.xpath("td[2]"));
						System.out.println("Item: " + itemName + ", Price: " + priceCell.getText());
						itemFound = true;
						break;
					}
				}
			}
			
			if (itemFound) {
				break;
			}

			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			nextButton.click();
		}
	}
	
	

}

	

