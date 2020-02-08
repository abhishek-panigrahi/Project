package testCases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
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

	@BeforeSuite
	public void beforeSuite() {

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/saucedemo.html"));
		//extent.config().setTheme(Theme.DARK);
		extent.config().setReportName("Automated tests for saucedemo");
		
		report = new ExtentReports();
		report.setSystemInfo("URL", "<a href='https://www.saucedemo.com/'>link</a>");
		report.setSystemInfo("Browser", "Chrome");
		report.attachReporter(extent);

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
		
		logger.info("Navigate to saucedemo.com");

		driver = new ChromeDriver();	
		driver.navigate().to("https://www.saucedemo.com/");
		driver.manage().window().maximize();

		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		//throw new SkipException("Skipped this test");
	}

	@Test(priority = 2, dependsOnMethods = "navigateToSauceDemo")
	public void loginToSauceDemo() {

		logger = report.createTest("Login");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		
		logger.info("Logging in to saucedemo.com");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.className("btn_action")).click();

	}
	
	@Test(priority = 3, dependsOnMethods = "loginToSauceDemo")
	public void logoutOfSauceDemo() throws InterruptedException {

		logger = report.createTest("Logout");
		logger.assignAuthor("Abhishek\tPanigrahi");
		logger.assignCategory("Smoke\tfor\tsaucedemo.com");
		
		logger.info("Logging out of saucedemo.com");

		driver.findElement(By.className("bm-burger-button")).click();
		Thread.sleep(2000l);
		driver.findElement(By.id("logout_sidebar_link")).click();

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
