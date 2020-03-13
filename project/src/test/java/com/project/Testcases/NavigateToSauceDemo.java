package com.project.Testcases;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.Page.BaseClass;
import com.project.Utility.BrowserFactory;
import com.project.Utility.Logs;
import com.project.Utility.MethodLibrary;

public class NavigateToSauceDemo extends BaseClass{

	@Parameters({"test site URL","test site name","Browser"})
	@Test
	public void navigateToSauceDemo(String url, String testSiteName, String browser) throws InterruptedException
	{	
	    Logs.logSetter("Navigate to "+testSiteName, author, category);
		BrowserFactory.navigateToTestSite(url, testSiteName, browser);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	} 

}
