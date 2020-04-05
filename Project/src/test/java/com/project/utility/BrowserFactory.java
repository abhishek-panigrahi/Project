package com.project.utility;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.project.configure.BaseClass;

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
			// Remove unnecessary java logs in console
			Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
			
			// Assign browser
			if(browserName.equalsIgnoreCase("firefox"))
			{
				
				Logs.info("Browser: "+"firefox");
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator+"src"+
	                     File.separator+"test"+File.separator+"resources"+File.separator+"\\geckodriver.exe");
		     
				// Remove unnecessary logs for firefox from console
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
			
				
		        driver = new FirefoxDriver();
					
			}
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				
				Logs.info("Browser: "+"chrome");
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator+"src"+
			                     File.separator+"test"+File.separator+"resources"+File.separator+"\\chromedriver.exe");
				
				ChromeOptions options = new ChromeOptions();
				
				// Remove unnecessary logs from chrome driver in console
				System.setProperty("webdriver.chrome.silentOutput", "true");
				
				driver = new ChromeDriver(options);
			
			}
			else if(browserName.equalsIgnoreCase("IE"))
			{
				Logs.info("Browser: "+"IE");
				
				/*
				We are explicitly using a 32-bit IE driver to
				solve the problem of slow send keys operation
				*/
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator+"src"+
	                 File.separator+"test"+File.separator+"resources"+File.separator+"\\IEDriverServer.exe");
				
				// Remove unnecessary logs from IE driver in console
				System.setProperty("webdriver.ie.driver.silent", "true");
				
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
