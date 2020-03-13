package com.project.Utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
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
