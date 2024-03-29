package com.project.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.project.configure.BaseClass;
import com.project.utility.Logs;
import com.project.utility.MethodLibrary;

public class LoginPage extends BaseClass {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.className("btn_action");
	
	public void loginToSauceDemo(String userName, String passWord)
	{
		// Log message
		logMessage = "Logging in to "+testSiteName;
		
		// Log in report
		reportLogger.info(logMessage);
		
		// Log in application.log
		Logs.info(logMessage);
		
		MethodLibrary.clearAndInput(username, "Username field", userName);
		MethodLibrary.clearAndInput(password, "Password field", passWord);
		MethodLibrary.clickLink(loginButton, "Login button");
	}
	
}


