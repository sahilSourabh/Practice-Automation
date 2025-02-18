package test.Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test.TestComponents.BaseTest;
import test.resources.ExtentReportNG;

public class MyListener extends BaseTest implements ITestListener{
	
	private static ExtentReports extent = ExtentReportNG.getReportObject();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	//private static ExtentTest test;
	
	
	@Override
	public void onStart(ITestContext context) {
		
		System.out.println("Test Execution Started: " + context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
		test.log(Status.INFO, "Starting Test: "+ result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		ExtentTest test = extentTest.get();
		
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test Case Passed", ExtentColor.GREEN));
		attachScreenshot(result, Status.PASS);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		ExtentTest test = extentTest.get();
		
		test.log(Status.FAIL , MarkupHelper.createLabel(result.getName()+" -Test Case Failed", ExtentColor.RED));
		test.fail(result.getThrowable());
		attachScreenshot(result, Status.FAIL);
		
//		test.log(Status.FAIL, "Test is FAILED at: " + result.getName() +
//                "\nError: " + result.getThrowable().getMessage(),
//                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		ExtentTest test =  extentTest.get();
		
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" - Test Case Skipped", ExtentColor.ORANGE));
		attachScreenshot(result, Status.SKIP);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		System.out.println("Test Execution Finished: "+ context.getName());
		
		sendMailWithAttachments();
	}
	
	private void attachScreenshot(ITestResult result, Status status) {
		
		ExtentTest test = extentTest.get();
		String screenshotPath = null;
		
		try {
			//dynamically retrieves the WebDriver instance from the test class where the failure occurred
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//Get screenshot path from BaseTest
			screenshotPath = getScreenshotPath(result.getMethod().getMethodName(), driver);
			//attaching the screenshot
			test.log(status, "Screenshot: "+ test.addScreenCaptureFromPath(screenshotPath, result.getName()));
			
			//test.addScreenCaptureFromPath(screenshotPath, result.getName());
		} 
		catch (Exception e) {
			
			test.log(Status.WARNING, "Failed to attach screenshot");
		}
		
		
	}

}
