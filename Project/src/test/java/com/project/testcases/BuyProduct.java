package com.project.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.configure.BaseClass;
import com.project.page.MainMenuPage;
import com.project.utility.BrowserFactory;
import com.project.utility.Logs;
import com.project.utility.MethodLibrary;

public class BuyProduct extends BaseClass {

	@Parameters({"username", "password"})
	@Test(description = "Navigate to the given test site and buy a product")
	public static void buyProduct(String username, String password) throws InterruptedException
	{	
		// Set up a new log entry in report
		Logs.reportLogSetter("Buy a product from "+testSiteName, author, category);

		// Navigate to the test site
		BrowserFactory.navigateToTestSite(testSiteURL, testSiteName, browser);

		// Wait for page to load
		MethodLibrary.waitForPageToLoad();

		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");

		// Login to application
		loginPageObject.loginToSauceDemo(username, password);

		// Wait for page to load
		MethodLibrary.waitForPageToLoad();

		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");

		// Scroll to the product using it's name
		MethodLibrary.scrollToText("Sauce Labs Onesie", 200, 3);
		
		String price = null;
		
		// Retrieve price
		price = MethodLibrary.retrieveText(By.xpath("//div[text()='Sauce Labs Onesie']//parent::a//parent::div//following-sibling::div//div[@class='inventory_item_price']"), "Price of the product").trim();
		
		price = price.replace("$", "");
		
		// Add product to cart
		MethodLibrary.clickLink(MainMenuPage.locatorAddToCartButton, MainMenuPage.nameAddToCartButton);
			
		// Assert if remove button is displayed
		MethodLibrary.assertText(MethodLibrary.retrieveText(MainMenuPage.locatorAddToCartButton, MainMenuPage.nameRemoveButton).trim(),"REMOVE", MainMenuPage.nameRemoveButton);
		
		// Scroll to the product using it's locator
		MethodLibrary.scrollToLocator(By.id("shopping_cart_container"), "Shopping cart icon", -200, 3);
		
		// Check if shopping cart badge is displayed
		MethodLibrary.isElementDisplayed(By.xpath("//div[@id='shopping_cart_container']//span"), "Shopping cart badge");
		
		// Assert if the number of products is correct
		MethodLibrary.assertText(MethodLibrary.retrieveText(By.xpath("//div[@id='shopping_cart_container']//span"), "Shopping cart badge"), "1", "Shopping cart badge");
		
		// Click on the cart icon
		MethodLibrary.clickLink(By.id("shopping_cart_container"), "Shopping cart icon");
		
		// Assert page title
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
		
		// Check if shopping cart badge is displayed
		MethodLibrary.isElementDisplayed(By.xpath("//div[@id='shopping_cart_container']//span"), "Shopping cart badge");
		
		// Assert if the number of products is correct
		MethodLibrary.assertText(MethodLibrary.retrieveText(By.xpath("//div[@id='shopping_cart_container']//span"), "Shopping cart badge"), "1", "Shopping cart badge");
		
		// Assert that the product is present on the page
		MethodLibrary.assertIfTrue(MethodLibrary.isElementDisplayed(By.xpath("//*[text()='Sauce Labs Onesie']"), "Sauce Labs Onesie"),"Sauce Labs Onesie is displayed");
		
		// Assert price of the product
		MethodLibrary.assertIfTrue(MethodLibrary.isElementDisplayed(By.xpath("//*[text()='"+price+"']"), "Price of the product"),"Price of the product is displayed");
		
	}
}
