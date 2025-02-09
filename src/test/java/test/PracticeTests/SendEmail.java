package test.PracticeTests;

import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import test.TestComponents.BaseTest;
import test.mails.SendSimpleEmails;

public class SendEmail extends BaseTest{
	
	
	@Test(priority=1)
	public static void testExample() {
		
		int a = 90;
		int b = 80;
		System.out.println("Running Tests...");
		
		Assert.assertEquals(a, b);
		
	}
	
	
	@AfterMethod
	public void sendEmailOnFailure(ITestResult result) throws EmailException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
			sendEmail();
			
			System.out.println("Test has been Failed and Email is sent");
		}
		
		
	}

}
