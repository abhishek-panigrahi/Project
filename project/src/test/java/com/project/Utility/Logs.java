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
	public static Logger logger = LogManager.getLogger("Logs");

	public static void extentReportSetter(String url, String testSiteName)
	{
	
	 String reportFileName = null;
     
		reportFileName = testSiteName.replaceAll("\\s", "");
		
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+reportFileName+".html"));
		extent.config().setTheme(Theme.DARK);
		extent.config().setReportName("Automated tests for "+testSiteName);
		
		//report = new ExtentReports();
		report.setSystemInfo("URL", "<a href='"+testSiteURL+"'>link</a>");
		report.setSystemInfo("Browser", "Chrome");
		report.attachReporter(extent);
		
		MethodLibrary.removeAllFiles(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+reportFileName+".html");
		
	}
	
	
	public static void reportLogSetter(String testName, String author, String category)
	{
		reportLogger = report.createTest(testName);
		reportLogger.assignAuthor(author);
		reportLogger.assignCategory(category);
	}

	
	
	public static void logFailMessage(ITestResult result) throws IOException
	{
		reportLogger.fail("Test failed. Detailed error: "+result.getThrowable().getMessage(), MediaEntityBuilder.
				createScreenCaptureFromPath(MethodLibrary.captureScreenshot(driver, result.getName())).build());
		report.flush();
	}
	
	
	public static void logPassMessage(ITestResult result)
	{
		reportLogger.pass("Test Case Passed: " + result.getName());	
		report.flush();
	}
	
	
	public static void logSkipMessage(ITestResult result)
	{
	reportLogger.skip("Test Case Skipped: " + result.getName());
	report.flush();
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void debug(String msg) {
		logger.debug(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}
	
}
