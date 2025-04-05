package test.PracticeTests;


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

import test.DataDrivenTests.ContactPage;
import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class testPractice extends BasePage {
    
    private ContactPage contactPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        if (driver == null) {
            driver = initializeDriver();
        }
        driver.get("https://classic.freecrm.com/");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        BaseTest.waitForframeAvailablityAndSwitchToIt("mainpanel");
        contactPage = new ContactPage(driver);
    }

    @Test(priority = 1, dataProvider = "getData")
    public void dataDrivenTest(HashMap<String, String> input) {
        contactPage.clickOnNewContactLink();
        contactPage.createNewContact(input.get("title"), input.get("firstName"), input.get("lastName"), input.get("company"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String filePath = "S://Automation//TestData.xlsx"; // Path to Excel file
        return getExcelData(filePath, "contacts");
    }

    public Object[][] getExcelData(String filePath, String sheetName) throws IOException {
      
    	FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row headerRow = sheet.getRow(0);
        int colCount = headerRow.getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][1];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            HashMap<String, String> map = new HashMap<>();

            for (int j = 0; j < colCount; j++) {
                map.put(headerRow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
            }
            data[i - 1][0] = map;
        }

        workbook.close();
        return data;
    }
}


	

