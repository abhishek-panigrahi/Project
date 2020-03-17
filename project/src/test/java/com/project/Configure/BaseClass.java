package com.project.Configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.project.Page.LoginPage;
import com.project.Page.MainMenuPage;
import com.project.Testdata.TestConstants;
import com.project.Utility.BrowserFactory;
import com.project.Utility.Logs;

public class BaseClass {

	public static WebDriver driver;
	public static int indexForWarning = 200;
	public static ExtentTest reportLogger;
	public static String logMessage = null;
	public static Properties config;
	public static String testSiteURL = null;
	public static String browser = null;
	public static String environment = null;
	public static String category = null;
	public static String author = null;
	public static String testSiteName = null;
	
	protected LoginPage loginPageObject;
	protected MainMenuPage mainMenuPageObject;
	
	@BeforeSuite
	public void onStart() throws IOException {
		
		config = new Properties();		
		FileInputStream configFile = new FileInputStream(
		System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"
				+File.separator+"project"+File.separator+"Configure"+File.separator+"config.properties");
		config.load(configFile);
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		TestConstants.environmentSetter();
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
	BrowserFactory.closeBrowser(driver);	
	}
	

}
