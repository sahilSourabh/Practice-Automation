package test.DataDrivenTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class DataDrivingFromHashmap extends BasePage{
	
	private ContactPage contactPage;
	private String filePath = "S://Automation//TestData.xlsx";
	private String sheetName = "contacts";
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		if (driver == null) {  // Ensure driver is initialized
            driver = initializeDriver();
        }
		driver.get("https://classic.freecrm.com/");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
		driver.findElement(By.cssSelector("input[value*='Login']")).click();
		// Switch to frame with proper wait
        BaseTest.waitForframeAvailablityAndSwitchToIt("mainpanel");
		contactPage = new ContactPage(driver);
	}
	
	
	@Test(priority = 1, dataProvider = "getData")
	public void dataDrivingFromHashmapTest(HashMap<String, String> input) {
		
		contactPage.clickOnNewContactLink();
		contactPage.createNewContact(input.get("title"), input.get("firstName"), input.get("lastName"), input.get("companyName"));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		HashMap<String, String> map =  new HashMap<>();
		map.put("title", "Mr.");
		map.put("firstName", "Portgas");
		map.put("lastName", "D Ace");
		map.put("companyName", "OP");
		
		HashMap<String, String> map1 =  new HashMap<>();
		map1.put("title", "Mr.");
		map1.put("firstName", "Kim");
		map1.put("lastName", "Jiwoo");
		map1.put("companyName", "SCOG");
		
		Object[][] data = { {map},{map1} };
		
		return BaseTest.getObjectTestData(data);
	}
	
	
//	-------------------------------------------------------------------------------------------------------------
	/*
	@DataProvider
	public Object[][] getData() throws IOException{
		
		return getExcelDataUsingHashmap(filePath, sheetName);	
	}
	
	// Getting the data from Excel to the Hashmap
	public Object[][] getExcelDataUsingHashmap(String filePath, String sheetName) throws IOException {
	      
    	FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row headerRow = sheet.getRow(0);
        int colCount = headerRow.getPhysicalNumberOfCells();    //Returns the number of columns/cells in the header row

        Object[][] data = new Object[rowCount - 1][1];     //[1]:Each test case will have exactly one parameter(HashMap)

        for (int i = 1; i < rowCount; i++) {
            
//        	Row row = sheet.getRow(i);
            HashMap<String, String> map = new HashMap<>();

           // Uses the header row values as keys and Puts the corresponding cell values into the HashMap
            for (int j = 0; j < colCount; j++) {
            	
               map.put(headerRow.getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
            }
            //[i-1] because we skipped the header row, the first row of data should start from index 0 in your array
            data[i - 1][0] = map;
        }

        workbook.close();
        return data;
    }
    */

}
