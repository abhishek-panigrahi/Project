package com.project.Testcases;

import org.testng.annotations.Test;

import com.project.Page.BaseClass;
import com.project.Utility.Logs;
import com.project.Utility.MethodLibrary;

public class LogOutOfSauceDemo extends BaseClass{

	@Test
	public void logoutOfSauceDemo() throws InterruptedException {
		
		Logs.logSetter("Logout", author, category);
		logger.info("Logging out of saucedemo.com");
		mainMenuPageObject.logoutOfSauceDemo();
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
}
