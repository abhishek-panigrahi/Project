package com.project.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.Configure.BaseClass;

public class MethodLibrary extends BaseClass{
	
	
	/**
	 * 
	 * This method is used to remove all files
	 * from the given directory
	 * 
	 * 
	 * @param indexResultFilename
	 * 
	 */ 
	public static void removeAllFiles(String indexResultFilename)
	{
		Logs.info("Removing all files from 'Reports' folder");
		
		try
		{
			// Store current directory
			String currentDir = indexResultFilename.substring(0, indexResultFilename.lastIndexOf(File.separator));

			// Clear Test Report folder
			File directory = new File(currentDir);
			
			// Store all files names in given directory
			File[] allFiles = directory.listFiles();
			
			// Remove all files
			for (int count = 0; count < allFiles.length; count++) {
				allFiles[count].delete();
			}
		}
		catch(Exception exception)
		{
			// Log error message
			logMessage = "Error came: "+exception;
			System.out.println(logMessage);
			Logs.error(logMessage);
		}
	}
	

	/**
	 * This method is used to take screenshot
	 * and return it's path.
	 * 
	 * 	
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException
	 */
	public  static String captureScreenshot(WebDriver driver, String screenShotName) throws IOException {
		
		String dest = null;
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
			Date date = new Date();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			dest = System.getProperty("user.dir") + "\\Reports\\" + screenShotName + dateFormat.format(date)
			+ ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
		} catch (Exception exception) {
			exception.getMessage();
			System.out.println(exception.getMessage());
		}
		return dest;
	}

	/**
	 * 
	 * highlight the element on which action will be performed
	 * 
	 * @param driver
	 * @param loginButton
	 */
	public static void highlightElement(WebDriver driver, WebElement element, String elementName) {

		Logs.info("Highlighting element: "+elementName);
		
		int count = 0;

		try {

			for (count = 0; count < 3; count++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border: 2px solid red;");
			}

		}

		catch (Throwable t) {
			if(count==2)
				reportLogger.warning("Error came while highlighting element: " + 
						t.getMessage().substring(0, Math.min(t.getMessage().length(), indexForWarning))+"...");
		}
	}

	/**
	 *  public static void waitForElementToLoad(By WebElement, String elementName, int timeperiod) method specification:
	 * 
	 * 1) Waits for the web element to appear on the page 2) new
	 * WebDriverWait(driver, 60) -> Waits for 60 seconds 3)
	 * wait.until((ExpectedCondition<Boolean>) -> Wait until expected condition
	 * (All documents present on the page get ready) met
	 * 
	 * @param : no parameters passed
	 * 
	 * 
	 * @param logoutButton
	 * @param elementName
	 * @param timePeriod
	 */
	public static
	void waitForElementToLoad(final WebElement logoutButton, String elementName, int timePeriod) {

		logMessage = "Waiting for web element: "+elementName+" to load on the page";
		reportLogger.info(logMessage);
		Logs.info(logMessage);
				
		try {

			// Waits for 60 seconds
			Wait<WebDriver> wait = new WebDriverWait(driver, timePeriod);

			// Wait for element to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(convertToBy(logoutButton)));

			// log message
			logMessage = "Waiting ends ... Web element loaded on the page";
			
			// Log result
			reportLogger.info(logMessage);
			Logs.info(logMessage);
		}

		catch (Throwable waitForElementException) {

			// Log message
			logMessage = "Error came while waiting for element: "+elementName+" to appear: " 
					+ waitForElementException.getMessage().substring(0, Math.min(waitForElementException.getMessage().length(), indexForWarning))
					+"...";
			
			// Log error
			reportLogger.error(logMessage);
			Logs.error(logMessage);
		}

	}

	/**
	 * Waits for element to appear on the page. Once appeared, highlight the
	 * element and clicks on it. Returns Pass if able to click on the element.
	 * Returns Fail if any exception occurs in between.
	 * 
	 * @param loginButton
	 *            Element locator
	 * @param elemName
	 *            Element name
	 * 
	 * @return Pass/Fail
	 */

	public static void clickLink(WebElement element, String elemName) {

		// Log Message
		logMessage = "Clicking on : " + elemName;
		
		// Log in report
		reportLogger.info(logMessage);
		
		// Log in application.log
		Logs.info(logMessage);

		try {

			// Wait for link to appear on the page
			//waitForElementToLoad(locator,elemName, 10);

			// Highlight link
			MethodLibrary.highlightElement(driver, element, elemName);

			// Click on the link
			element.click();

			// Log result
			reportLogger.pass("Clicked on : " + elemName);

		}

		catch (Throwable clickLinkException) {

			// Log error
			Assert.fail("Error while clicking on: " + elemName + " : " + clickLinkException.getMessage());

		}

	}


	/**
	 * Waits for input box to appear on the page. Once appeared, highlight and
	 * clears the box. Returns Pass if Input box got cleared successfully.
	 * Returns Fail if input box didn't clear or any exception occurs in
	 * between.
	 * 
	 * @param locator
	 *            Element locator
	 * @param elemName
	 *            Element name
	 * 
	 * @return Pass/Fail
	 */

	public static void clearField(WebElement locator, String elemName) {

		reportLogger.info("Clearing field : " + elemName);

		try {

			// Wait for the input-box to load on the page
			waitForElementToLoad(locator,elemName, 10);

			// Highlight the input-box
			MethodLibrary.highlightElement(driver, driver.findElement(convertToBy(locator)), elemName);

			// Clear the input-box
			driver.findElement(convertToBy(locator)).clear(); 

			// Check whether input-box has been cleared or not
			if (!driver.findElement(convertToBy(locator)).getAttribute("value").isEmpty())
				driver.findElement(convertToBy(locator)).clear();

			// Log result
			reportLogger.info("Cleared : " + elemName);

		}

		catch (Throwable clearFieldException) {

			// Log error
			reportLogger.error("Error while clearing field: " + elemName + " : " + clearFieldException.getMessage()
			.substring(0, Math.min(clearFieldException.getMessage().length(), indexForWarning))+"...");
		}

	}


	/**
	 * 
	 * public static String clearAndInput(By locator,String elemName,String
	 * Value) method specification :-
	 * 
	 * 1) Clear and then Inputs/sends value 2) locator -> identify the web
	 * element by id,x-path,name,etc. 3) elemName -> the name of the web element
	 * where we intend to input/send values 4) Value -> the string value which
	 * we intend to input/send 5) waitForElementToLoad(locator) -> waits for web
	 * element to load 6) FunctionLibrary.clearField(locator, elemName); ->
	 * clears the input field 7) driver.findElement(locator).sendKeys(Value) ->
	 * inputs/sends the value to the intended web element
	 * 
	 * @param : Locator for the input-box, name of the web element, value to be
	 * inputted
	 * 
	 * @param elemName
	 * @param Value
	 */
	public static void clearAndInput(WebElement element, String elemName, String Value) {

		try {

			// Wait for the input box to appear on the page
			waitForElementToLoad(element,elemName, 10);

			// Highlight the input box
			MethodLibrary.highlightElement(driver, driver.findElement(convertToBy(element)), elemName);

			// Clear the input field before sending values
			MethodLibrary.clearField(element, elemName);

			// Send values to the input box
			reportLogger.info("Sending values to : " + elemName);

			driver.findElement(convertToBy(element)).sendKeys(Value);

			// Log result
			reportLogger.pass("Inputted '" + Value + "' text into : '" + elemName + "'");

		}

		catch (Throwable inputException) {

			// Log error
			Assert.fail("Error while inputting text into: '" + elemName + "' : " + inputException.getMessage()
			.substring(0, Math.min(inputException.getMessage().length(), indexForWarning))+"...");

		}

	}


	/**
	 * 
	 * public static void waitForPageToLoad() method specification :-
	 * 
	 * 1) Waits for a new page to load completely 2) new WebDriverWait(driver,
	 * 60) -> Waits for 60 seconds 3) wait.until((ExpectedCondition<Boolean>) ->
	 * Wait until expected condition (All documents present on the page get
	 * ready) met
	 * 
	 * @param : no parameters passed
	 * 
	 * @return : void
	 * 
	 * @throws InterruptedException
	 */

	public static void waitForPageToLoad() throws InterruptedException {

		try {

			// Waits for 60 seconds
			WebDriverWait wait = new WebDriverWait(driver, 60);

			// Wait until expected condition (All documents present on the page
			// get ready) met
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver d) {

					if (!(d instanceof JavascriptExecutor))
						return true;

					Object result = ((JavascriptExecutor) d)
							.executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

					if (result != null && result instanceof Boolean && (Boolean) result)
						return true;

					return false;

				}

			});

		}

		catch (Exception waitForPageToLoadException) {

			reportLogger.warning("Error came while waiting for page to load : " + waitForPageToLoadException.getMessage()
			.substring(0, Math.min(waitForPageToLoadException.getMessage().length(), indexForWarning))+"...");
		}

	}

	/**
	 * 
	 * public static Boolean isElementPresent(By locator, String elemName)
	 * method specification
	 * 
	 * driver.findElement(locator) : Checking whether element present or not
	 * 
	 * @param locator
	 * @param elemName
	 * @return true / false
	 */
	public static boolean isElementPresent(By locator, String elemName) {

		reportLogger.info("Checking whether " + elemName + " is present on the page or not ...");

		try {

			// Check whether web element is displayed or not
			driver.findElement(locator);

			reportLogger.info(elemName + " is present on the page");
			return true;

		}

		catch (NoSuchElementException elementPresentError) {

			reportLogger.info(elemName + " not present on the page");
			return false;

		}

	}

	/**
	 * This method is used to navigate to the application
	 * 
	 * 
	 * @param url
	 * @param testSiteName
	 */

	public static void navigateToSite(String url, String testSiteName)
	{
		reportLogger.info("Navigating to test site: "+testSiteName);

		try
		{

			driver = new ChromeDriver();	

			// Implicitly wait for 30 seconds for browser to open
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Delete all browser cookies
			driver.manage().deleteAllCookies();

			driver.navigate().to(url);

			driver.manage().window().maximize();
		}
		catch(Exception exception)
		{
			Assert.fail("Unable to visit website due to exception: "+exception.getMessage());
		}
	}



	/**
	 * 
	 * This method is used to make assertions
	 * for text and log them
	 * 
	 * @param actualText
	 * @param expectedText
	 * @param element
	 */
	public static void assertText(String actualText, String expectedText, String element)
	{
		reportLogger.info("Asserting text for: "+element);
		Assert.assertEquals(actualText, expectedText);
		reportLogger.pass("Pass: Succesfully asserted "+element);
	}


	/**
	 * This method is used to convert web element
	 * to type By
	 * 
	 * 
	 * @param webelment
	 * @return by
	 */
	public static By convertToBy(WebElement webelment) {
		String[] data = webelment.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];

		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) webelment;
	}




}
