package test.PracticeAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import test.pageObjects.LoginPage;
import test.pageObjects.MainPage;
import test.pageObjects.ResetPasswordPage;

public class LoginTestPOM {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		SoftAssert softAssert = new SoftAssert();
		
		String username = "sourabhsahil";
		//String password = "rahulshettyacademy";
		String name= "Sahil";
		String mail="sahil@gmail.com";
		String phone="7010400022";

		//LandingPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.ForgotPassword();
		
		//Getting password by Resetting the Password
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		
		resetPasswordPage.detailsToResetPassword(name, mail, phone);
		resetPasswordPage.resetPassword();
		String password = resetPasswordPage.getPasswordText();
		resetPasswordPage.goToLoginPage();
		
		Thread.sleep(2000);
		loginPage.loginApplication(username,password);

		//MainPage
		MainPage mainPage = new MainPage(driver);
		
		//Welcome Text Username Validation
		String welcomeText = mainPage.getWelcomeText();
		System.out.println("Introductory text : " + welcomeText);
		softAssert.assertTrue(welcomeText.contains(username));
	
		//Success Text Validation
		String actual = mainPage.LogInSuccessText();
		String expected = "You are successfully logged in.";
		softAssert.assertEquals(actual, expected);
		Thread.sleep(2000);

		mainPage.LogOut();
		
		driver.quit();
		softAssert.assertAll();

	}
}
