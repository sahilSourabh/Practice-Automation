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
import test.resources.ExtentReportManager;

public class MyListener2 extends BaseTest implements ITestListener {
	
    private static ExtentReports extent = ExtentReportManager.getReportObject();
    private static ExtentReports extentCases = ExtentReportManager.getSeperateReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTestCases = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
    	
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);

        ExtentTest testCases = extentCases.createTest(result.getMethod().getMethodName());
        extentTestCases.set(testCases);

        test.log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
        testCases.log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	
        logTestResult(result, Status.PASS, ExtentColor.GREEN);
    }

    @Override
    public void onTestFailure(ITestResult result) {
         
    	ExtentTest test = extentTest.get();
        ExtentTest testCases = extentTestCases.get();
        
        test.fail(result.getThrowable());
        testCases.fail(result.getThrowable());
        
        logTestResult(result, Status.FAIL, ExtentColor.RED);
          
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	
        logTestResult(result, Status.SKIP, ExtentColor.ORANGE);
    }

    @Override
    public void onFinish(ITestContext context) {
    	
        extent.flush();
        extentCases.flush();
        System.out.println("Test Execution Finished: " + context.getName());
    }

    private void logTestResult(ITestResult result, Status status, ExtentColor color) {
    	
        ExtentTest test = extentTest.get();
        ExtentTest testCases = extentTestCases.get();
        
        test.log(status, MarkupHelper.createLabel(result.getName() + " - Test Case " + status.toString(), color));
        testCases.log(status, MarkupHelper.createLabel(result.getName() + " - Test Case " + status.toString(), color));
        
        attachScreenshot(result, status);
    }

    private void attachScreenshot(ITestResult result, Status status) {
    	
        ExtentTest test = extentTest.get();
        ExtentTest testCases = extentTestCases.get();
        
        try {
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String screenshotPath = getScreenshotPath(result.getMethod().getMethodName(), driver);
            
            test.log(status, "Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            testCases.log(status, "Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } 
        catch (Exception e) 
        {
            test.log(Status.WARNING, "Failed to attach screenshot");
            testCases.log(Status.WARNING, "Failed to attach screenshot");
        }
    }
}
