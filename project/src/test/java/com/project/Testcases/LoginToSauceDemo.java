package com.project.Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.project.Page.BaseClass;
import com.project.Utility.Logs;
import com.project.Utility.MethodLibrary;

public class LoginToSauceDemo extends BaseClass{

    @Parameters({"username", "password"})
	@Test
	public void loginToSauceDemo(String username, String password) throws InterruptedException {
   	
    	Logs.logSetter("Login", author, category);
		logger.info("Logging in to saucedemo.com");
		loginPageObject.loginToSauceDemo(username, password);
		MethodLibrary.waitForPageToLoad();
		
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
}
