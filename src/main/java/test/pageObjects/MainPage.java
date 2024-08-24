package test.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	
	WebDriver driver;
	
	public MainPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

//			
//			String actual = driver
//					.findElement(By.xpath("//h1/following-sibling::p[contains(.,'logged in.')]"))
//					.getText();
//			String expected = "You are successfully logged in.";
//
//			softAssert.assertEquals(actual, expected);
	@FindBy(css="h2")
	WebElement welcomeText;
	
	@FindBy(xpath="//h1/following-sibling::p[contains(.,'logged in.')]")
	WebElement loginSuccessText;
	
	@FindBy(css=".logout-btn")
	WebElement logOutButton;
	
	public String getWelcomeText()
	{
		return welcomeText.getText();
	}
	
	public String LogInSuccessText()
	{
		return loginSuccessText.getText();
	}
	
	public void LogOut()
	{
		logOutButton.click();
	}

}
