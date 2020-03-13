package com.project.Utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project.Page.BaseClass;


public class Listener extends BaseClass implements ITestListener {

	Logs logger = new Logs();

	@Override
	public void onStart(ITestContext result) {   
	}

	@Override
	public void onFinish(ITestContext result) {
	}

	@Override
	public void onTestStart(ITestResult result) {
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			logger.logFailMessage(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//logger.skip("Test Case Skipped: " + result.getName());
		//report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logger.logPassMessage(result);
	}
	
}
