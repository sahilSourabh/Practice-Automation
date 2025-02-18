package test.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;


public class Retry implements IRetryAnalyzer {
	
	private int retryCount = 0;
	private static final int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		
		if(retryCount < maxRetryCount) {
			
			retryCount++;
			//System.out.println("Retrying failed test: " + result.getName() + " (Retry " + retryCount + ")");
			
			return true;
		}
		
		return false;
	}

}
