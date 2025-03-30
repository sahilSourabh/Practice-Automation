package test.PracticeTests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String fruit ="Mango";
		String fileName = "C:\\Users\\Sourabh Sahil\\Downloads\\download.xlsx";
		String columnName = "price";
		String updatedValue = "360";
		
		driver.get("https://rahulshettyacademy.com/upload-download-test/");
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".justify-content-center"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='cell-2-undefined'])[2]"))));
		
		//Download File
		driver.findElement(By.cssSelector("#downloadButton")).click();
		System.out.println("File Downloaded Successfully");
		Thread.sleep(2000);
		
		//Edit excel-> Get column number of "price"-> Get row number of "Apple"-> Update Excel with row and column
		
		//Edit excel->Update Excel for Price of a fruit
		/* 1) Get the row of the Fruit
		   2) Get the cell/column value of price in that row  */
		 
		int column = getColumnNumber(fileName,columnName);
		System.out.println("Column number of "+columnName+" : "+column);
		int row = getrowNumber(fileName,fruit);
		System.out.println("Row number of "+fruit+" : "+row);
		
		updateCell(fileName,row,column,updatedValue);
		System.out.println("Values Updated");
		Assert.assertTrue(updateCell(fileName,row,column,updatedValue));
		
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
	
		//String actualPrice =driver.findElement(By.xpath("//div[text()='" + fruitName+"']/""parent::div/following-sibling::div[@id='cell-" + price+ "-undefined']")).getText();
		String actualPrice = driver
				.findElement(By.xpath("//div[text()='"+fruit+"']/parent::div/parent::div/div[@id='cell-"+priceSection+"-undefined']"))
				.getText();
		System.out.println("Price of "+fruit+": "+actualPrice);

		Thread.sleep(2000);
		driver.quit();
	}

	//Edit excel-> Get column number of "price"-> Get row number of "Apple"-> Update Excel with row and column
	
	private static boolean updateCell(String fileName, int row, int column, String updatedValue) throws IOException {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		//rows and columns are kept one less because getRow() and getCell() follows 0-based indexing.
		Row rowField = sheet.getRow(row-1);
		Cell cellField = rowField.getCell(column-1);
		cellField.setCellValue(updatedValue);
		
		FileOutputStream fos =  new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		workbook.close();
		
		return true;
	
	}

	private static int getrowNumber(String fileName, String fruitName) throws IOException {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rows = sheet.rowIterator();
		int k=1, rowNumber=0;

		while(rows.hasNext()) {
			
			Row row = rows.next();
			
			Iterator<Cell> cells = row.cellIterator();	
			while(cells.hasNext()) {
				
				Cell cell = cells.next();
				if( cell.getCellType()==CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(fruitName) ) {
					
					rowNumber = k;
					break;
				} 
			} 
			k++;

		} 
		
		return rowNumber;	
		}
		

	private static int getColumnNumber(String fileName, String column) throws IOException {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		Iterator<Row> rows = sheet.rowIterator();
		Row firstRow = rows.next();
		//Row firstRow = sheet.getRow(0);
		
		int k=1, columnIndex=0;
		Iterator<Cell> cells = firstRow.cellIterator();
		while(cells.hasNext()) {
			
			if(cells.next().getStringCellValue().equalsIgnoreCase(column)) {
				
				columnIndex = k;
				break;
			} 
			k++;
		}
		
		return (columnIndex);
	}

}
