package com.project.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.project.Utility.BrowserFactory;
import com.project.Utility.Logs;

public class BaseClass {

	public static WebDriver driver;
	public static int indexForWarning = 200;
	public static ExtentTest logger;

	
	public static String testSiteName = "SauceDemo.com";
	public static String testSiteURL = "https://www.saucedemo.com/";
	public static String category = "Smoke";
	public static String author = "Abhishek\tPanigrahi";
	//public static final String testSuiteNameForSmoke = "Smoke\tfor\tsaucedemo.com";
	
	protected LoginPage loginPageObject;
	protected MainMenuPage mainMenuPageObject;
	
	@BeforeSuite
	public void onStart() {		
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");	
		Logs.extentReportSetter(testSiteURL, testSiteName);
	}

	@BeforeMethod
	public void pageObjects()
	{
		loginPageObject = PageFactory.initElements(driver, LoginPage.class);
		mainMenuPageObject = PageFactory.initElements(driver, MainMenuPage.class);
		
	}
	
	@AfterSuite
	public void finishedTest()
	{
	BrowserFactory.closeBrowser();	
	}
	

}