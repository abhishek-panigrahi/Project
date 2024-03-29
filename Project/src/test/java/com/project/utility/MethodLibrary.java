package com.project.utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.configure.BaseClass;

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
			dest = System.getProperty("user.dir") + "\\Reports\\" + screenShotName + dateFormat.format(date)
			+ ".png";
			File destination = new File(dest);

			// This code will capture screenshot of current screen      
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			// This will store screenshot on Specific location
			ImageIO.write(image, "png", destination);

		} catch (Exception exception) {
			exception.getMessage();
			System.out.println(exception.getMessage());
			Logs.error(exception.getMessage());
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
	public static void highlightElement(WebDriver driver, By element, String elementName) {

		Logs.info("Highlighting element: "+elementName);

		int count = 0;

		try {

			for (count = 0; count < 3; count++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", driver.findElement(element),
						"color: red; border: 2px solid red;");
			}

		}

		catch (Throwable t) {
			if(count==2)
				reportLogger.warning("Error came while highlighting element: " + 
						t.getMessage().substring(0, Math.min(t.getMessage().length(), indexForWarning))+"...");
			Logs.warning("Error came while highlighting element: " + 
					t.getMessage());
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
	public static void waitForElementToLoad(final By element, String elementName, int timePeriod) {

		logMessage = "Waiting for web element: "+elementName+" to load on the page";
		reportLogger.info(logMessage);
		Logs.info(logMessage);

		try {

			// Waits for 60 seconds
			Wait<WebDriver> wait = new WebDriverWait(driver, timePeriod);

			// Wait for element to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));

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

			// Log message
			logMessage = "Error came while waiting for element: "+elementName+" to appear: " 
					+ waitForElementException.getMessage();

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

	public static void clickLink(By locator, String elemName) {

		// Log Message
		logMessage = "Clicking on : " + elemName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try {

			// Wait for link to appear on the page
			//waitForElementToLoad(locator,elemName, 10);
			
			WebDriverWait wait=new WebDriverWait(driver, 10);
			
			wait.until(ExpectedConditions.elementToBeClickable(locator));

			// Highlight link
			MethodLibrary.highlightElement(driver, locator, elemName);

			// Click on the link
			driver.findElement(locator).click();

			// Log Message
			logMessage = "Clicked on : " + elemName;

			// Log in report
			reportLogger.pass(logMessage);

			// Log in application.log
			Logs.info(logMessage);

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

	public static void clearField(By locator, String elemName) {

		// Log message
		logMessage = "Clearing field : " + elemName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try {

			// Wait for the input-box to load on the page
			waitForElementToLoad(locator,elemName, 5);

			// Highlight the input-box
			MethodLibrary.highlightElement(driver, locator, elemName);

			// Clear the input-box
			driver.findElement(locator).clear(); 

			// Check whether input-box has been cleared or not
			if (!driver.findElement(locator).getAttribute("value").isEmpty())
				driver.findElement(locator).clear();

			// Log result
			reportLogger.info("Cleared : " + elemName);

		}

		catch (Throwable clearFieldException) {

			// Log error

			// Log in report
			reportLogger.error("Error while clearing field: " + elemName + " : " + clearFieldException.getMessage()
			.substring(0, Math.min(clearFieldException.getMessage().length(), indexForWarning))+"...");

			// Log in application.log
			Logs.error("Error while clearing field: " + elemName + " : " + clearFieldException.getMessage());
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
	public static void clearAndInput(By element, String elemName, String Value) {

		try {

			// Highlight the input box
			MethodLibrary.highlightElement(driver, element, elemName);

			// Clear the input field before sending values
			MethodLibrary.clearField(element, elemName);

			//Log message
			logMessage = "Sending values to : " + elemName;

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

			// Send values to the input box
			driver.findElement(element).sendKeys(Value);

			// Log result
			reportLogger.pass("Inputted '" + Value + "' text into : '" + elemName + "'");

		}

		catch (Throwable inputException) {

			// Log error
			Assert.fail("Error while inputting text into: '" + elemName + "' : " + inputException.getMessage());

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

			// Log message
			logMessage = "Waiting for page to load";

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

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

			// Log message
			logMessage = "Waiting ends, web page loaded";

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

		}

		catch (Exception waitForPageToLoadException) {

			// Log error message
			reportLogger.warning("Error came while waiting for page to load : " + waitForPageToLoadException.getMessage()
			.substring(0, Math.min(waitForPageToLoadException.getMessage().length(), indexForWarning))+"...");

			// Log in application.log
			Logs.warning("Error came while waiting for page to load : " + waitForPageToLoadException.getMessage());
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

		// Log message
		logMessage = "Checking whether " + elemName + " is present on the page or not ...";

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try {

			// Check whether web element is displayed or not
			driver.findElement(locator);

			// Log message
			logMessage = elemName + " is present on the page";

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

			return true;

		}

		catch (NoSuchElementException elementPresentError) {

			// Log message
			logMessage = elemName + " not present on the page";

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

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

		// Log message
		logMessage = "Navigating to test site: "+testSiteName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try
		{

			// Create a new instance of chrome browser window
			driver = new ChromeDriver();	

			// Implicitly wait for 30 seconds for browser to open
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Delete all browser cookies
			driver.manage().deleteAllCookies();

			// Navigate to the given URL
			driver.navigate().to(url);

			// Maximize window
			driver.manage().window().maximize();
		}
		catch(Exception exception)
		{
			// Assert failure
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
		// Log message
		logMessage = "Asserting text for: "+element;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		// Perform assertion
		Assert.assertEquals(actualText, expectedText);

		// Log message
		logMessage = "Successfully asserted "+element;

		// Log in report
		reportLogger.pass(logMessage);

		// Log in application.log
		Logs.info(logMessage);
	}
	
	
	/**
	 *  
	 * This method is used to make assertions
	 * for boolean values and log them
	 * 
	 * 
	 * @param value
	 * @param elementName
	 */
	public static void assertIfTrue(boolean value, String elementName)
	{
		// Log message
		logMessage = "Asserting if "+elementName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		// Perform assertion
		Assert.assertTrue(value);

		// Log message
		logMessage = "Successfully asserted that "+elementName;

		// Log in report
		reportLogger.pass(logMessage);

		// Log in application.log
		Logs.info(logMessage);
	}
	

	/**
	 * 
	 * Returns true if the element is displayed
	 * Returns false if the element is not displayed
	 * Returns false if any exception occurs
	 * 
	 * 
	 * @param locator
	 * @param elementName
	 * @return true/false
	 */
	public static Boolean isElementDisplayed(By locator, String elementName) {

		boolean isDisplayed;
		
		// Log message
		logMessage = "Checking whether " + elementName + " is displayed on the page or not ...";

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try {

			// Check whether web element is displayed or not
			driver.findElement(locator);
			
			isDisplayed = true;

			logMessage = elementName + " is displayed on the page";

			// Highlight link
			MethodLibrary.highlightElement(driver, locator, elementName);

			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

		}

		catch (Throwable exception) {
			
			isDisplayed = false;
			
			logMessage = elementName + " is not displayed on the page";
			
			// Log in report
			reportLogger.info(logMessage);

			// Log in application.log
			Logs.info(logMessage);

		}

		return isDisplayed;

	}

	
	
	/**
	 * Scrolls to the text mentioned as a parameter;
	 * Logs an info message on successfully finding the text;
	 * Logs a warning message on not finding the text;
	 * Logs an error message on not finding the text;
	 * 
	 * 
	 * @param text
	 * @param verticalPixels
	 * @param count
	 * @throws InterruptedException
	 */
	public static void scrollToText(String text, int verticalPixels, int count) throws InterruptedException
	{

		// Log message
		logMessage = "Scrolling to the text: "+text;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		String locator = null;
		int InScroll=0;
		int ScrollTo=verticalPixels;
		int flagForFailSafe = 0;

		boolean status = true;

		// Construct a locator
		locator="//*[contains(text(),'"+text+"')]";

		try
		{

			//scroll down
			while(!(MethodLibrary.isElementDisplayed(By.xpath(locator), text)) && status){
				
				if(flagForFailSafe<count)
				{

					// Log message
					logMessage = "Scrolling...";

					// Log in report
					reportLogger.info(logMessage);

					// Log in application.log
					Logs.info(logMessage);

					((JavascriptExecutor)driver).executeScript("scroll("+InScroll+","+ScrollTo+")");
					InScroll=ScrollTo;
					
					if(verticalPixels>0)
					    ScrollTo=ScrollTo+verticalPixels;
					else
						ScrollTo=ScrollTo-verticalPixels;
					
					++flagForFailSafe;

					status = true;
				}
				else
					status = false;
			}

			if(!status)
			{
				// Log message
				logMessage = "Element: "+text+" not found after scrolling "+count+" times";

				// Log in report
				reportLogger.warning(logMessage);

				// Log in application.log
				Logs.warning(logMessage);

			}
			else if(status)
			{

				// Log message
				logMessage = "Scrolled to element: "+text;

				// Log in report
				reportLogger.pass(logMessage);

				// Log in application.log
				Logs.info(logMessage);
			}

		}
		catch (Throwable exception)
		{

			// Log message
			logMessage = "Error while scrolling to text: " + exception.getMessage();

			// Log in report
			reportLogger.error(logMessage);

			// Log in application.log
			Logs.error(logMessage);

		}

	}


	
	
	/** 
	 * Scrolls to the element mentioned as a parameter;
	 * Logs an info message on successfully finding the element;
	 * Logs a warning message on not finding the element;
	 * Logs an error message on not finding the element;
	 * 
	 *
	 * 
	 * @param locator
	 * @param elementName
	 * @param verticalPixels
	 * @param count
	 * @throws InterruptedException
	 */
	public static void scrollToLocator(By locator, String elementName, int verticalPixels, int count) throws InterruptedException
	{

		// Log message
		logMessage = "Scrolling to the element: "+elementName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);
		
		int InScroll=0;
		int ScrollTo=verticalPixels;
		int flagForFailSafe = 0;

		boolean status = true;

		try
		{

			//scroll down
			while(!(MethodLibrary.isElementDisplayed(locator, elementName)) && status){
				
				if(flagForFailSafe<count)
				{

					// Log message
					logMessage = "Scrolling...";

					// Log in report
					reportLogger.info(logMessage);

					// Log in application.log
					Logs.info(logMessage);

					((JavascriptExecutor)driver).executeScript("scroll("+InScroll+","+ScrollTo+")");
					InScroll=ScrollTo;
					
					if(verticalPixels>0)
					    ScrollTo=ScrollTo+verticalPixels;
					else
						ScrollTo=ScrollTo-verticalPixels;
					
					++flagForFailSafe;

					status = true;
				}
				else
					status = false;
			}

			if(!status)
			{
				// Log message
				logMessage = "Element: "+elementName+" not found after scrolling "+count+" times";

				// Log in report
				reportLogger.warning(logMessage);

				// Log in application.log
				Logs.warning(logMessage);

			}
			else if(status)
			{

				// Log message
				logMessage = "Scrolled to element: "+elementName;

				// Log in report
				reportLogger.pass(logMessage);

				// Log in application.log
				Logs.info(logMessage);
			}

		}
		catch (Throwable exception)
		{

			// Log message
			logMessage = "Error while scrolling to text: " + exception.getMessage();

			// Log in report
			reportLogger.error(logMessage);

			// Log in application.log
			Logs.error(logMessage);

		}

	}


	/**
	 * 
	 * Retrieves text from given locator
	 * Fails test on getting exception
	 * 
	 * 
	 * @param locator
	 * @param elementName
	 * @return
	 */
	public static String retrieveText(By locator, String elementName) {

		String retrievedText = null;
		
		// Log message
		logMessage = "Retrieving Text from : " + elementName;

		// Log in report
		reportLogger.pass(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		try {

			// Wait for web element to load on the page
			waitForElementToLoad(locator, elementName, 10);

			// Highlight the web element
			highlightElement(driver, locator, elementName);

			// Retrieve text from web element
			retrievedText = driver.findElement(locator).getText().trim();

			// Log result
			logMessage = "Retrieved text : " + retrievedText;

		}

		catch (Throwable retrieveTextException) {

			// Fail the test
			Assert.fail("Error while retrieving text from '" + elementName + "' : " + retrieveTextException.getMessage());
		
		}

		return retrievedText;

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
