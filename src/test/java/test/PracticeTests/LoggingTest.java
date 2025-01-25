package test.PracticeTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {
	
	private static Logger logger = LogManager.getLogger(LoggingTest.class);

	public static void main(String[] args) {
		
		System.out.println("Logging started...");
		
		logger.trace("Trace message");
		logger.debug("Debug message");
		logger.info("Info message");
		logger.warn("Warn message");
		logger.error("Error message");
		logger.fatal("Fatal messsage");
		
		System.out.println("Logging ended...");

	}

}
