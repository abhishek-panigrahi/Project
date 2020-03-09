package com.project.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.Utility.MethodLibrary;

public class MainMenuPage {
	
	WebDriver driver;
	
	public MainMenuPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH ,using = "//div[@class='bm-burger-button']//button")
	@CacheLookup
	WebElement sideMenuButton;
	
	@FindBy(how = How.ID ,using = "logout_sidebar_link")
	@CacheLookup
	WebElement logoutButton;
	
	public void logoutOfSauceDemo() throws InterruptedException
	{
		MethodLibrary.clickLink(sideMenuButton, "side menu button");
		MethodLibrary.waitForElementToLoad(logoutButton, "logout button", 10);
		MethodLibrary.clickLink(logoutButton, "logout button");
	}
}
