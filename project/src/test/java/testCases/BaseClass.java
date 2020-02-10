package testCases;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static int indexForWarning = 50; 

	@BeforeSuite
	public void beforeSuite() {

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/saucedemo.html"));
		extent.config().setTheme(Theme.DARK);
		extent.config().setReportName("Automated tests for saucedemo");
		
		report = new ExtentReports();
		report.setSystemInfo("URL", "<a href='https://www.saucedemo.com/'>link</a>");
		report.setSystemInfo("Browser", "Chrome");
		report.attachReporter(extent);
		
		FunctionLibrary.removeAllFiles(System.getProperty("user.dir") + File.separator+"Reports"+File.separator+"saucedemo.html");
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");	
	}


	@Test(priority = 1)
	public void navigateToSauceDemo()
	{
		logger = report.createTest("Navigate to saucedemo.com");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");

		FunctionLibrary.navigateToSite("https://www.saucedemo.com/", "SauceDemo.com");
		FunctionLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}

	@Test(priority = 2, dependsOnMethods = "navigateToSauceDemo")
	public void loginToSauceDemo() throws InterruptedException {

		logger = report.createTest("Login");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		logger.info("Logging in to saucedemo.com");

		FunctionLibrary.clearAndInput(By.id("user-name"),"User name", "standard_user");
		FunctionLibrary.clearAndInput(By.id("password"),"Password", "secret_sauce");
		FunctionLibrary.clickLink(By.className("btn_action"), "login button");
		FunctionLibrary.waitForPageToLoad();
		FunctionLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
	@Test(priority = 3, dependsOnMethods = "loginToSauceDemo")
	public void logoutOfSauceDemo() throws InterruptedException {
		
		logger = report.createTest("Logout");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		logger.info("Logging out of saucedemo.com");
		
		FunctionLibrary.clickLink(By.xpath("//*[@class='bm-burger-button']//span"), "Side menu icon");
		FunctionLibrary.clickLink(By.id("logout_sidebar_link"), "Log out option");
		FunctionLibrary.waitForPageToLoad();
		FunctionLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}


	@AfterMethod
	public void getResult(ITestResult result) throws IOException 
	{
		
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test failed. Detailed error: "+result.getThrowable().getMessage(), MediaEntityBuilder.
					createScreenCaptureFromPath(FunctionLibrary.captureScreenshot(driver, result.getName())).build());
		}

		if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test Case Skipped: " + result.getName());
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Case Passed: " + result.getName());
		}
	
		report.flush();		
	}

}
