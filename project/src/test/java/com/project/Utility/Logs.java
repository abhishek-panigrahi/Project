package com.project.Utility;

import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project.Page.BaseClass;

public class Logs extends BaseClass{
	
	public static ExtentReports report;

	public static void extentReportSetter(String url, String testSiteName)
	{
	
	 String reportFileName = null;
     
		reportFileName = testSiteName.replaceAll("\\s", "");
		
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+reportFileName+".html"));
		extent.config().setTheme(Theme.DARK);
		extent.config().setReportName("Automated tests for "+testSiteName);
		
		report = new ExtentReports();
		report.setSystemInfo("URL", "<a href='"+testSiteURL+"'>link</a>");
		report.setSystemInfo("Browser", "Chrome");
		report.attachReporter(extent);
		
		MethodLibrary.removeAllFiles(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+reportFileName+".html");
		
	}
	
	
	public static void logSetter(String testName, String author, String category)
	{
		logger = report.createTest(testName);
		logger.assignAuthor(author);
		logger.assignCategory(category);
	}

	
	
	public static void logFailMessage(ITestResult result) throws IOException
	{
		logger.fail("Test failed. Detailed error: "+result.getThrowable().getMessage(), MediaEntityBuilder.
				createScreenCaptureFromPath(MethodLibrary.captureScreenshot(driver, result.getName())).build());	
		report.flush();
	}
	
	
	public static void logPassMessage(ITestResult result)
	{
		logger.pass("Test Case Passed: " + result.getName());	
		report.flush();
	}
	
	
	public static void logSkipMessage(ITestResult result)
	{
	logger.skip("Test Case Skipped: " + result.getName());
	report.flush();
	}
	

}
