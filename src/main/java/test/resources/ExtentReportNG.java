package test.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
	
	private static ExtentReports extent;
	
public static ExtentReports getReportObject() {
		
		if(extent ==  null ) {
			
			File path = new File(System.getProperty("user.dir")+"//reports//Report.html");
			
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			
			reporter.config().setDocumentTitle("Automation Tests");
			reporter.config().setReportName("Web Automation Results");
			reporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			//extent.setSystemInfo("User", "Sourabh");
			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		
		return extent;
	}

}
