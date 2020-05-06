package com.project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.project.configure.BaseClass;
import com.project.utility.Logs;
import com.project.utility.MethodLibrary;

public class MainMenuPage extends BaseClass{

	// Locators
	public static By locatorAddToCartButton = By.xpath("//div[text()='Sauce Labs Onesie']//parent::a//parent::div//following-sibling::div//button");	
	//public static By sideMenuButton = By.xpath("//div[@class='bm-burger-button']//button");
	public static By sideMenuButton = By.xpath("//*[text()='Open Menu']");
	
	public static By logoutButton = By.id("logout_sidebar_link");
	
	// Element names
	public static String nameAddToCartButton = "ADD TO CART button for product";
	public static String nameRemoveButton = "REMOVE button for product";

	public void logoutOfSauceDemo() throws InterruptedException
	{
		// Log message
		logMessage = "Logging out of "+testSiteName;

		// Log in report
		reportLogger.info(logMessage);

		// Log in application.log
		Logs.info(logMessage);

		((JavascriptExecutor)driver).exe
		
		((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", ));
		MethodLibrary.clickLink(sideMenuButton, "side menu button");
		MethodLibrary.waitForElementToLoad(logoutButton, "logout button", 10);
		MethodLibrary.clickLink(logoutButton, "logout button");
	}
}
