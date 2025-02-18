package test.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	private static ExtentReports extent;
	private static ExtentReports extentCases;
	
	public static ExtentReports getReportObject() {
		
		File reportPath = new File (System.getProperty("user.dir")+ "//reports//ExtentReport.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setDocumentTitle("Automation Tests");
		reporter.config().setReportName("Test Execution Report");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java version", System.getProperty("java.version"));
		
		return extent;
	}

	public static ExtentReports getSeperateReports() {
			
			File passedReportPath = new File (System.getProperty("user.dir")+ "//reports//PassedTestsReport.html");
			ExtentSparkReporter passedReporter = new ExtentSparkReporter(passedReportPath);
			
			passedReporter.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
			
			passedReporter.config().setDocumentTitle("Passed Automation Tests");
			passedReporter.config().setReportName("Passed Test Execution Report");
			passedReporter.config().setTheme(Theme.DARK);
			
			
			File failedReportPath = new File (System.getProperty("user.dir")+ "//reports//FailedTestsReport.html");
			ExtentSparkReporter failedReporter = new ExtentSparkReporter(failedReportPath);
			
			failedReporter.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
			
			failedReporter.config().setDocumentTitle("Failed Automation Tests");
			failedReporter.config().setReportName("Failed Test Execution Report");
			failedReporter.config().setTheme(Theme.DARK);
			
			
			extentCases = new ExtentReports();
//			extent.attachReporter(passedReporter);
//			extent.attachReporter(failedReporter);
			extentCases.attachReporter(passedReporter,failedReporter);
			
			return extentCases;
	}

}
