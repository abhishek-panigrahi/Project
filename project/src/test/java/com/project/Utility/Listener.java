package com.project.Utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.util.Strings;

/**
 * This is a listener class
 * 
 * It listens to the events of the scripts
 * and logs those events
 * 
 * 
 * @author Abhishek Panigrahi
 *
 */
public class Listener implements ITestListener {
	
	@Override
	public void onStart(ITestContext result) {   
	}

	@Override
	public void onFinish(ITestContext result) {
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		if(!Strings.isNullOrEmpty(result.getMethod().getDescription())){
			Logs.info("Executing test case: "+result.getMethod().getDescription());
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Logs.logFailMessage(result);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Logs.logSkipMessage(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Logs.logPassMessage(result);
	}
	
}
