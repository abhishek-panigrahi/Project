package com.project.Page;
import org.openqa.selenium.By;
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
	
	public void loginToSauceDemo(String userName, String passWord)
	{
		username.sendKeys(userName);
		password.sendKeys(passWord);
		MethodLibrary.clickLink(loginButton, "Login button");
	}
	
}


