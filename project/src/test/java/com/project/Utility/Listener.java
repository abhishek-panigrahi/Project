package com.project.Utility;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener implements ITestListener{

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static int indexForWarning = 200; 
	
	

	@Override
	public void onStart(ITestContext result) {		
		MethodLibrary.baseUtility();
	}

	@Override
	public void onFinish(ITestContext result) {
		driver.close();
	}

	@Override
	public void onTestStart(ITestResult result) {		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		
		try {
			logger.fail("Test failed. Detailed error: "+result.getThrowable().getMessage(), MediaEntityBuilder.
					createScreenCaptureFromPath(MethodLibrary.captureScreenshot(driver, result.getName())).build());

		} catch (IOException e) {
			e.printStackTrace();
		}
		report.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.skip("Test Case Skipped: " + result.getName());
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.pass("Test Case Passed: " + result.getName());	
		report.flush();
	}
	
}
