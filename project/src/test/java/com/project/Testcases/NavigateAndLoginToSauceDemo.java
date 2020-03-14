package com.project.Testcases;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.Page.BaseClass;
import com.project.Page.TestConstants;
import com.project.Utility.BrowserFactory;
import com.project.Utility.Logs;
import com.project.Utility.MethodLibrary;

public class NavigateAndLoginToSauceDemo extends BaseClass{

	@Parameters({"Browser","Environment"})
	@Test(description = "Navigate to the given test site and asserting it's title")
	public void navigateToSauceDemo(String browser, String environment) throws InterruptedException
	{	
	    Logs.reportLogSetter("Navigate to "+testSiteName, author, category);
	    TestConstants.environmentSetter(environment);
		BrowserFactory.navigateToTestSite(testSiteURL, testSiteName, browser);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	} 
	
	@Parameters({"username", "password"})
	@Test(enabled = true, dependsOnMethods = {"navigateToSauceDemo"}, description = "Logging in to given test site and asserting it's title")
	public void loginToSauceDemo(String username, String password) throws InterruptedException {
   	
    	Logs.reportLogSetter("Login", author, category);
		reportLogger.info("Logging in to saucedemo.com");
		loginPageObject.loginToSauceDemo(username, password);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
	@Test(enabled = true, dependsOnMethods = {"loginToSauceDemo"}, description = "Logging out of given test site and asserting it's title")
	public void logoutOfSauceDemo() throws InterruptedException {
		
		Logs.reportLogSetter("Logout", author, category);
		reportLogger.info("Logging out of saucedemo.com");
		mainMenuPageObject.logoutOfSauceDemo();
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}

}
