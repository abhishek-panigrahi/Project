package com.project.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.project.Configure.BaseClass;

/**
 * 
 * This method is used to navigate
 * to the test site
 * 
 * @parameters:
 * test site URL
 * test site name
 * browser name
 * 
 * @return type
 * Web driver
 *
 */
public class BrowserFactory extends BaseClass{

	public static WebDriver navigateToTestSite(String url, String testSiteName, String browserName)
	{
		// Log message
		logMessage = "Navigating to test site: "+testSiteName;
		reportLogger.info(logMessage);
		Logs.info(logMessage);

		try
		{
			// Assign browser
			if(browserName.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();		
			}
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("IE"))
			{
				driver = new InternetExplorerDriver();
			}

			// Implicitly wait for 30 seconds for browser to open
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Delete all browser cookies
			driver.manage().deleteAllCookies();
			
			// Maximize browser window
			driver.manage().window().maximize();
			
			// Navigate to the given URL
			driver.navigate().to(url);
			
			// Log message
			logMessage = "Successfully visited test site: "+testSiteName;
			reportLogger.info(logMessage);
			Logs.info(logMessage);

			
		}
		catch(Exception exception)
		{
			// Log failure message
			logMessage = "Unable to visit website due to exception: "+exception;
			reportLogger.fatal(logMessage);
			Logs.fatal(logMessage);
			Assert.fail(logMessage);
		}

		return driver;
	}

	
	
	/**
	 * This method is used to close the
	 * browser instance
	 * 
	 * 
	 * @param driver
	 */
	public static void closeBrowser(WebDriver driver)
	{
		if(null != driver){		
			
			// Log message
			logMessage = "Closing browser instance";
			Logs.info(logMessage);

			// Close the browser instance
			driver.close();
			driver=null;
		}

	}
	
}
