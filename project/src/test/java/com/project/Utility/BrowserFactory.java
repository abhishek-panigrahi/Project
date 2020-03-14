package com.project.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.project.Page.BaseClass;

public class BrowserFactory extends BaseClass{

	public static WebDriver navigateToTestSite(String url, String testSiteName, String browserName)
	{
		
		reportLogger.info("Navigating to test site: "+testSiteName);
		
		try
		{

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
			driver.manage().window().maximize();
			driver.navigate().to(url);

		}
		catch(Exception exception)
		{
			Assert.fail("Unable to visit website due to exception: "+exception.getMessage());
		}

		return driver;
	}

public static void closeBrowser()
{
	driver.close();
}
	
}
