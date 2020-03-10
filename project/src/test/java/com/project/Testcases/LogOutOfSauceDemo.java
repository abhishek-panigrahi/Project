package com.project.Testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.project.Page.MainMenuPage;
import com.project.Utility.Listener;
import com.project.Utility.MethodLibrary;

public class LogOutOfSauceDemo extends Listener{

	@Test
	public void logoutOfSauceDemo() throws InterruptedException {
		
		MethodLibrary.logSetter("Logout", "Abhishek\tPanigrahi", "Smoke\tfor\tsaucedemo.com");
		logger.info("Logging out of saucedemo.com");
		
		MainMenuPage mainMenuPage = PageFactory.initElements(driver, MainMenuPage.class);
		mainMenuPage.logoutOfSauceDemo();
		MethodLibrary.waitForPageToLoad();
		MethodLibrary.assertText(driver.getTitle(), "Swag Labs","Page title");
	}
	
}
