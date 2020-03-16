package com.project.Utility;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project.Page.BaseClass;

public class Logs extends BaseClass{

	public static ExtentReports report = new ExtentReports();
	public static Logger logger = LogManager.getLogger("LOG");

	/**
	 * 
	 * This method is used to create extent report
	 * 
	 * @param url
	 * @param testSiteName
	 */
	public static void extentReportSetter(String url, String testSiteName)
	{

		String reportFileName = null;

		// Edit file name for the report
		reportFileName = testSiteName.replaceAll("\\s", "");

		// Create an instance of the report
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+reportFileName+".html"));
		
		// Assign dark theme to report
		extent.config().setTheme(Theme.DARK);
		
		// Assign title of the report
		extent.config().setReportName("Automated tests for "+testSiteName);

		// Provide URL in the report
		report.setSystemInfo("URL", "<a href='"+testSiteURL+"'>link</a>");
		
		// Provide browser type in the report
		report.setSystemInfo("Browser", "Chrome");
		
		// Attach the extent report to the script
		report.attachReporter(extent);

		// Remove all files in reports folder before starting the test
		MethodLibrary.removeAllFiles(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+reportFileName+".html");

	}


	/**
	 * 
	 * This method is used to define
	 * the logs in the extent report
	 * 
	 * @param testName
	 * @param author
	 * @param category
	 */
	public static void reportLogSetter(String testName, String author, String category)
	{
		// Create a new section of test in the the report
		reportLogger = report.createTest(testName);
		
		// Assign the author in the report
		reportLogger.assignAuthor(author);
		
		// Assign the category for tests
		reportLogger.assignCategory(category);
	}



	public static void logFailMessage(ITestResult result) throws IOException
	{
		logMessage = "Test failed. Detailed error: ";
		reportLogger.fail(logMessage+result.getThrowable().getMessage(), MediaEntityBuilder.
				createScreenCaptureFromPath(MethodLibrary.captureScreenshot(driver, result.getName())).build());
		Logs.error(logMessage+result.getThrowable().getMessage());
		report.flush();
	}


	public static void logPassMessage(ITestResult result)
	{
		logMessage = "Test Case Passed: " + result.getName();
		reportLogger.pass(logMessage);
		Logs.info(logMessage);
		report.flush();
	}


	public static void logSkipMessage(ITestResult result)
	{
		logMessage = "Test Case Skipped: " + result.getName();
		reportLogger.skip("Test Case Skipped: " + result.getName());
		Logs.debug(logMessage);
		report.flush();
	}

	// Method for info log 
	public static void info(String msg) {
		logger.info(msg);
	}

	// Method for debug log 
	public static void debug(String msg) {
		logger.debug(msg);
	}

	// Method for error log 
	public static void error(String msg) {
		logger.error(msg);
	}

	// Method for trace log
	public static void trace(String msg) {
		logger.trace(msg);
	}

	// Method for fatal log
	public static void fatal(String msg) {
		logger.fatal(msg);
	}

}
