package test.PracticeTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatabaseTesting {

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		// Connect to SQL database 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_qa", "root", "root1234");
		// The connection creates a statement object which is used to send SQL to the database
		Statement stmt = conn.createStatement();
		// ResultSet contains the tabular results of the query
	    // ResultSet object acts like an iterator over the returned rows
		ResultSet rs = stmt.executeQuery("SELECT * from EmployeeCreds where scenario = 'basiccard'");
		// rs.next() moves the cursor to the next row (returns false when no more rows)
		// next() positions the cursor before the first row initially
		while(rs.next()) {
			
			driver.manage().window().maximize();
			driver.get("https://login.salesforce.com/?locale=in");
			
			driver.findElement(By.cssSelector("#username")).sendKeys(rs.getString("username"));
			driver.findElement(By.cssSelector("#password")).sendKeys(rs.getString("password"));
			
			System.out.println(rs.getString("username")+" "+rs.getString("password"));
		}
		
		Thread.sleep(2000);
		driver.quit();
	}

}
