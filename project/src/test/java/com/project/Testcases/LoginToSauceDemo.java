package com.project.Testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.Page.LoginPage;
import com.project.Utility.Listener;
import com.project.Utility.MethodLibrary;

public class LoginToSauceDemo extends Listener{

    @Parameters({"username", "password"})
	@Test
	public void loginToSauceDemo(String username, String password) throws InterruptedException {
    	
		MethodLibrary.logSetter("Login", "Abhishek\tPanigrahi", "Smoke\tfor\tsaucedemo.com");
		logger.info("Logging in to saucedemo.com");
		
		LoginPage loginPageObject = PageFactory.initElements(driver, LoginPage.class);
		
		loginPageObject.loginToSauceDemo(username, password);
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
}
