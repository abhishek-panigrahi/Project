package com.project.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.project.page.LoginPage;
import com.project.page.MainMenuPage;
import com.project.testdata.TestConstants;
import com.project.utility.BrowserFactory;
import com.project.utility.Logs;

public class BaseClass {

	public static WebDriver driver;
	public static int indexForWarning = 250;
	public static ExtentTest reportLogger;
	public static String logMessage = null;
	public static Properties config;
	public static String testSiteURL = null;
	public static String browser = null;
	public static String environment = null;
	public static String category = null;
	public static String author = null;
	public static String testSiteName = null;
	
	protected static LoginPage loginPageObject;
	protected static MainMenuPage mainMenuPageObject;
	
	@BeforeSuite
	public void onStart() throws IOException {
		
		config = new Properties();		
		FileInputStream configFile = new FileInputStream(
		System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"
				+File.separator+"project"+File.separator+"Configure"+File.separator+"config.properties");
		config.load(configFile);
		
		TestConstants.environmentSetter();
		Logs.extentReportSetter(testSiteURL, testSiteName);

	}
	
	@BeforeMethod
	public void pageObjects()
	{
		loginPageObject = PageFactory.initElements(driver, LoginPage.class);
		mainMenuPageObject = PageFactory.initElements(driver, MainMenuPage.class);
	}
	
	@AfterClass
	public void finishedTest()
	{
	BrowserFactory.closeBrowser(driver);	
	}

}
