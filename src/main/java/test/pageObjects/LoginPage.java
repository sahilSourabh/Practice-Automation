package test.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="#inputUsername")
	WebElement username;
	
	@FindBy(css="input[type*='password']")
	WebElement password;
	
	@FindBy(css="#chkboxOne")
	WebElement firstCheckBox;
	
	@FindBy(xpath="(//div[@class='checkbox-container']//input)[2]")
	WebElement secondCheckBox;
	
	@FindBy(css=".submit.signInBtn")
	WebElement submitButton;
	
	By loggedInText =By.xpath("//h1/following-sibling::p[contains(.,'You are successfully logged in.')]");
	
	@FindBy(linkText="Forgot your password?")
	WebElement forgetPwdLink;
	
	@FindBy(xpath="//form//h2[.='Forgot password']")
	WebElement forgetPwdtext;
	
	
	public void goTo ()
	{
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
	}
	
	public void loginApplication(String user, String pwd)
	{
		username.sendKeys(user);
		password.sendKeys(pwd);
		firstCheckBox.click();
		secondCheckBox.click();
		submitButton.click();
		waitforElementToBeVisible(loggedInText);
	}
	
	public void ForgotPassword()
	{
		forgetPwdLink.click();
		waitforForgotPasswordToBeVisible(forgetPwdtext);
	}
	

}
