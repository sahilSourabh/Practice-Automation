package test.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import test.AbstractComponents.AbstractComponent;

public class ResetPasswordPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ResetPasswordPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Getting password by Resetting the Password
//			driver.findElement(By.xpath("//form//input[contains(@placeholder,'Name')]")).sendKeys("Sourabh Sahil");
//			driver.findElement(By.xpath("//form//input[contains(@placeholder,'Email')]")).sendKeys("sahil@gmail.com");
//			driver.findElement(By.xpath("//form//input[contains(@placeholder,'Phone')]")).sendKeys("1234567890");
//			driver.findElement(By.xpath("//form//div//button[@class='reset-pwd-btn']")).click();
//			
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("form .infoMsg"))));
//			
//			String pwd = driver.findElement(By.cssSelector("form .infoMsg")).getText();
//			
//			String[] pwdStr = pwd.split("'");
//			String password = pwdStr[1].split("'")[0];
//			
//			driver.findElement(By.xpath("//div//button[@class='go-to-login-btn']")).click();
	@FindBy(xpath="//form//input[contains(@placeholder,'Name')]")
	WebElement name;
	
	@FindBy(xpath="//form//input[contains(@placeholder,'Email')]")
	WebElement email;
	
	@FindBy(xpath="//form//input[contains(@placeholder,'Phone')]")
	WebElement phone;
	
	@FindBy(xpath="//form//div//button[@class='reset-pwd-btn']")
	WebElement resetButton;
	
	@FindBy(xpath="//div//button[@class='go-to-login-btn']")
	WebElement gotoLoginButton;
	
	@FindBy(css="form .infoMsg")
	WebElement infoMsgText;
	
	By infoMessage = By.cssSelector("form .infoMsg");
	
	
	public void detailsToResetPassword(String nameOf, String mail, String phoneNum)
	{
		name.sendKeys(nameOf);
		email.sendKeys(mail);
		phone.sendKeys(phoneNum);
	}
	
	public void resetPassword()
	{
		resetButton.click();
		waitforElementToBeVisible(infoMessage);
	}
	
	public void goToLoginPage()
	{
		gotoLoginButton.click();
	}
	
	public String getPasswordText()
	{
		String pwd = infoMsgText.getText();
		String[] pwdStr = pwd.split("'");
		String password = pwdStr[1].split("'")[0];
		return password;
	}


}
