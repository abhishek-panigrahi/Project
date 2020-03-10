package com.project.Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.Utility.BrowserFactory;
import com.project.Utility.Listener;
import com.project.Utility.MethodLibrary;

public class NavigateToSauceDemo extends Listener{

	@Parameters({"test site URL","test site name","Browser"})
	@Test
	public void navigateToSauceDemo(String url, String testSiteName, String browser) throws InterruptedException
	{
		MethodLibrary.extentReportSetter(url, testSiteName);
		MethodLibrary.logSetter("Navigate to "+testSiteName, "Abhishek\tPanigrahi", "Smoke\tfor\tsaucedemo.com");
		
		driver = BrowserFactory.navigateToTestSite(url, testSiteName, browser);

		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");

	} 

}
