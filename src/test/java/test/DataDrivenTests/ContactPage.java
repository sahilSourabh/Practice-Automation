package test.DataDrivenTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import test.TestComponents.BasePage;
import test.TestComponents.BaseTest;

public class ContactPage extends BasePage{
	
	// Locators
	private By contactLink = By.xpath("//a[contains(text(),'Contacts')]");
	private By newContactLink = By.xpath("//a[contains(text(),'New Contact')]");
	private By titleDropdown = By.cssSelector("select[name='title']");
	private By fName = By.cssSelector("input[id='first_name']");
	private By lName = By.cssSelector("input[id='surname']");
	private By company = By.xpath("//input[@name='client_lookup']");
	private By saveButton = By.xpath("//input[@type='submit' and @value='Save']");

	@SuppressWarnings("static-access")
	public ContactPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnNewContactLink() {
		
		WebElement contactElement = BaseTest.waitForElementVisiblity(contactLink);
		WebElement newContactElement = driver.findElement(newContactLink);
		
		Actions action = new Actions(driver);
		action.moveToElement(contactElement).pause(1000).click(newContactElement).build().perform();
	}

	public void createNewContact(String title, String firstName, String lastName, String companyName) {
		
		WebElement titleElement = BaseTest.waitForElementVisiblity(titleDropdown);
		BaseTest.selectByValue(titleElement, title);

		driver.findElement(fName).sendKeys(firstName);
		driver.findElement(lName).sendKeys(lastName);
		driver.findElement(company).sendKeys(companyName);
		driver.findElement(saveButton).click();
	}

}
