package com.project.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.project.Utility.MethodLibrary;

public class LoginPage  {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By username = By.id("user-name");
    By password = By.id("passwor");
    By loginButton = By.className("btn_action");
	
	public void loginToSauceDemo(String userName, String passWord)
	{
		MethodLibrary.clearAndInput(username, "Username field", userName);
		MethodLibrary.clearAndInput(password, "Password field", passWord);
		MethodLibrary.clickLink(loginButton, "Login button");
	}
	
}


