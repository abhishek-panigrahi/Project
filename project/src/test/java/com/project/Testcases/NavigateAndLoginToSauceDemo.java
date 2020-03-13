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
	@Test
	public void navigateToSauceDemo(String browser, String environment) throws InterruptedException
	{	
	    Logs.logSetter("Navigate to "+testSiteName, author, category);
	    TestConstants.environmentSetter(environment);
		BrowserFactory.navigateToTestSite(testSiteURL, testSiteName, browser);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	} 
	
	@Parameters({"username", "password"})
	@Test(dependsOnMethods = { "navigateToSauceDemo" })
	public void loginToSauceDemo(String username, String password) throws InterruptedException {
   	
    	Logs.logSetter("Login", author, category);
		logger.info("Logging in to saucedemo.com");
		loginPageObject.loginToSauceDemo(username, password);
		MethodLibrary.waitForPageToLoad();
		
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
	@Test(dependsOnMethods = { "loginToSauceDemo" })
	public void logoutOfSauceDemo() throws InterruptedException {
		
		Logs.logSetter("Logout", author, category);
		logger.info("Logging out of saucedemo.com");
		mainMenuPageObject.logoutOfSauceDemo();
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}

}
