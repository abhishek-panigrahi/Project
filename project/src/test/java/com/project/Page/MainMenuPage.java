package com.project.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.project.Utility.MethodLibrary;

public class MainMenuPage {
	
	WebDriver driver;
	
	public MainMenuPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By sideMenuButton = By.xpath("//div[@class='bm-burger-button']//button");
	By logoutButton = By.id("logout_sidebar_link");
	
	public void logoutOfSauceDemo() throws InterruptedException
	{
		MethodLibrary.clickLink(sideMenuButton, "side menu button");
		MethodLibrary.waitForElementToLoad(logoutButton, "logout button", 10);
		MethodLibrary.clickLink(logoutButton, "logout button");
	}
}
