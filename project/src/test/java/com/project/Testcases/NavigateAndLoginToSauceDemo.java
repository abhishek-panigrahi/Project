package com.project.Testcases;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.Configure.BaseClass;
import com.project.Utility.BrowserFactory;
import com.project.Utility.Logs;
import com.project.Utility.MethodLibrary;

public class NavigateAndLoginToSauceDemo extends BaseClass{
	
	@Test(description = "Navigate to the given test site and asserting it's title")
	public void navigateToSauceDemo() throws InterruptedException
	{
		// Set up a new log entry in report
	    Logs.reportLogSetter("Navigate to "+testSiteName, author, category);
	    
	    // Navigate to the test site
		BrowserFactory.navigateToTestSite(testSiteURL, testSiteName, browser);
		
		// Wait for page to load
		MethodLibrary.waitForPageToLoad();
		
		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	} 
	
	@Parameters({"username", "password"})
	@Test(enabled = true, dependsOnMethods = {"navigateToSauceDemo"}, description = "Logging in to given test site and asserting it's title")
	public void loginToSauceDemo(String username, String password) throws InterruptedException {
   	
		// Set up a new log entry in report
    	Logs.reportLogSetter("Login", author, category);
    	
    	// Login to application
		loginPageObject.loginToSauceDemo(username, password);
		
		// Wait for page to load
		MethodLibrary.waitForPageToLoad();
		
		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
	@Test(enabled = true, dependsOnMethods = {"loginToSauceDemo"}, description = "Logging out of given test site and asserting it's title")
	public void logoutOfSauceDemo() throws InterruptedException {
		
		// Set up a new log entry in report
		Logs.reportLogSetter("Logout", author, category);
		
		// Log out of test site
		mainMenuPageObject.logoutOfSauceDemo();
		
		// Wait for page to load
		MethodLibrary.waitForPageToLoad();
		
		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}

}
