package com.project.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.Utility.MethodLibrary;


public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(id="user-name")
	@CacheLookup
	WebElement username;
	
	@FindBy(how = How.ID,using = "password")
	@CacheLookup
	WebElement password;
	
	@FindBy(how = How.CLASS_NAME,using = "btn_action")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy(how = How.XPATH ,using = "//div[@class='bm-burger-button']//button")
	@CacheLookup
	WebElement sideMenuButton;
	
	@FindBy(how = How.ID ,using = "logout_sidebar_link")
	@CacheLookup
	WebElement logoutButton;
	

	public void loginToSauceDemo(String userName, String passWord)
	{
		MethodLibrary.clearAndInput(username, "Username field", userName);
		MethodLibrary.clearAndInput(password, "Password field", passWord);
		MethodLibrary.clickLink(loginButton, "Login button");
	}
	
	public void logoutOfSauceDemo() throws InterruptedException
	{
		MethodLibrary.clickLink(sideMenuButton, "side menu button");
		MethodLibrary.waitForElementToLoad(logoutButton, "logout button", 10);
		MethodLibrary.clickLink(logoutButton, "logout button");
	}
	
	
}


