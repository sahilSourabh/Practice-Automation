package test.DataDrivenTests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;

public class DataDrivingFromDatabase extends BaseTest{

	private ContactPage contactPage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException {
		
		if (driver == null) {  // Ensure driver is initialized
            driver = initializeDriver();
        }
		driver.get("https://classic.freecrm.com/");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("groupautomation");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Test@12345");
		driver.findElement(By.cssSelector("input[value*='Login']")).click();
		// Switch to frame with proper wait
        waitForframeAvailablityAndSwitchToIt("mainpanel");
		contactPage = new ContactPage(driver);
	}
	
	
	@Test(priority = 1, dataProvider = "getData")
	public void dataDrivingFromJSONTest(HashMap<String, String> input) throws InterruptedException {
		
		contactPage.clickOnNewContactLink();
		contactPage.createNewContact(input.get("title"), input.get("firstName"), input.get("lastName"), input.get("companyName"));
		sleep(2);
	}	
	
	@DataProvider
	public Object[][] getData() throws IOException, SQLException {
		
		Object[][] data = getDatabaseData();
		return getObjectTestData(data);
	}
	
	// Getting the data from the Database
	public static Object[][] getDatabaseData() throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts_db", "root", "root1234");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from contactInfo");
		
		List<HashMap<String, String>> dataList = new ArrayList<>();
		// rs.next() moves the cursor to the next row (returns false when no more rows)
	    // next() positions the cursor before the first row initially
		while(rs.next()) {
			
			HashMap<String, String> map = new HashMap<>();
			map.put("title", rs.getString("title"));
			map.put("firstName", rs.getString("firstName"));
			map.put("lastName", rs.getString("lastName"));
			map.put("companyName", rs.getString("companyName"));
			
			dataList.add(map);
		}
		
		Object[][] data = new Object[dataList.size()][1];
		
		for(int i=0; i< dataList.size();i++) {
			
			data[i][0] = dataList.get(i);
		}
		
		return data;
		
	}
}
