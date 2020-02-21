package com.project.Testcases;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project.Page.LoginPage;
import com.project.Utility.BrowserFactory;
import com.project.Utility.MethodLibrary;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static int indexForWarning = 200; 

	@BeforeSuite
	public void beforeSuite() {
		
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/saucedemo.html"));
		extent.config().setTheme(Theme.DARK);
		extent.config().setReportName("Automated tests for saucedemo");
		
		report = new ExtentReports();
		report.setSystemInfo("URL", "<a href='https://www.saucedemo.com/'>link</a>");
		report.setSystemInfo("Browser", "Chrome");
		report.attachReporter(extent);
		
		MethodLibrary.removeAllFiles(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+"saucedemo.html");
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");	
	}

	
    @Parameters({"test site URL","test site name","Browser"})
	@Test(priority = 1)
	public void navigateToSauceDemo(String url, String testSiteName, String browser) throws InterruptedException
	{
    	
		logger = report.createTest("Navigate to "+testSiteName);
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		
		driver = BrowserFactory.navigateToTestSite(url, testSiteName, browser);

		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
		
	}

  
    @Parameters({"username", "password"})
	@Test(priority = 2, dependsOnMethods = "navigateToSauceDemo")
	public void loginToSauceDemo(String username, String password) throws InterruptedException {

		logger = report.createTest("Login");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		logger.info("Logging in to saucedemo.com");
		
		LoginPage loginPageObject = PageFactory.initElements(driver, LoginPage.class);
		loginPageObject.loginToSauceDemo(username, password);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
	@Test(priority = 3, dependsOnMethods = "loginToSauceDemo")
	public void logoutOfSauceDemo() throws InterruptedException {
		
		logger = report.createTest("Logout");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		logger.info("Logging out of saucedemo.com");
		LoginPage loginPageObject = PageFactory.initElements(driver, LoginPage.class);
		loginPageObject.logoutOfSauceDemo();
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}


	@AfterMethod
	public void getResult(ITestResult result) throws IOException 
	{
		
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test failed. Detailed error: "+result.getThrowable().getMessage(), MediaEntityBuilder.
			createScreenCaptureFromPath(MethodLibrary.captureScreenshot(driver, result.getName())).build());
		}

		if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test Case Skipped: " + result.getName());
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Case Passed: " + result.getName());
		}
	
		report.flush();
	}
	
	@AfterSuite
	public static void afterSuite()
	{
	driver.close();
	}

}
