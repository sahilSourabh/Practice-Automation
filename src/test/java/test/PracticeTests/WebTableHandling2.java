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

public class WebTableHandling2 {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	public void columnPresenceCheck() {

		String fruit = "apple";
		boolean itemFound = false;

		driver.get("https://rahulshettyacademy.com/upload-download-test/");

		WebElement table = driver.findElement(By.cssSelector(".sc-beySPh.losPks.rdt_Table"));

		List<WebElement> rows = table.findElements(By.cssSelector(".sc-jsEeTM.itluUR.rdt_TableRow"));

		for (WebElement row : rows) {

			List<WebElement> columns = row.findElements(By.cssSelector("div[class*='cJTPDY rdt_TableCell']"));

			// Check if any cell contains the desired item
			for (WebElement column : columns) {

				if (column.getText().equalsIgnoreCase(fruit)) {
					itemFound = true;
					// Print the entire row
					for (WebElement cellData : columns) {
						System.out.print(cellData.getText() + "\t");
					}
					System.out.println();
					break;
				}
			}
			// Break the outer loop if item is found
			if (itemFound) {
				break;
			}

		}
		if (!itemFound) {
			System.out.println("Item not found in the table.");
		}

	}
	@Test
	public void checkParticularColumnValue() throws InterruptedException {

		boolean itemFound = false;
		String fruit = "Banana";
		String columnName = "price";
		
		driver.get("https://rahulshettyacademy.com/upload-download-test/");

		WebElement table = driver.findElement(By.cssSelector(".sc-beySPh.losPks.rdt_Table"));
		
		// Find the header row to get the column index
        WebElement headerRow = table.findElement(By.cssSelector(".sc-dmyCSP.fwQJth.rdt_TableHeadRow"));
        
        List<WebElement> headerColumns  = headerRow.findElements(By.cssSelector(".gfKXFa.kAwGKS.rdt_TableCol"));
        int columnIndex = -1;

        for (int i=0; i<headerColumns.size(); i++) {
       
            if (headerColumns.get(i).getText().equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            System.out.println("Column with name '" + columnName + "' not found.");
            return;
        }
        System.out.println(columnName+" is found at Column index: "+columnIndex);
        
        List<WebElement> rows = table.findElements(By.cssSelector(".sc-jsEeTM.itluUR.rdt_TableRow"));

		for (WebElement row : rows) {
			// Fetching column values within each row
			List<WebElement> cells = row.findElements(By.cssSelector("div[class*='cJTPDY rdt_TableCell']"));

			for (WebElement cell : cells) {

				if (cell.getText().equalsIgnoreCase(fruit)) {
					itemFound = true;

					// Print the value of the target column
					if (columnIndex < cells.size()) {

						// If the item is found, it fetches the value of the specified column index
						String columnValue = cells.get(columnIndex).getText();

//						System.out.println("Item found! Value in column " + (columnIndex + 1) + ": " + targetValue);
						System.out.println(fruit+" is found with " + columnName + ": " + columnValue);
					} else {
						System.out.println("Target column index is out of bounds for the row.");
					}
					break;
				}
			}

		}
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
