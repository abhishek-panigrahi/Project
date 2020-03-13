package com.project.Utility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class WebDriverUtils {

	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
 
	
	public WebDriverUtils(WebDriver driver, ExtentReports report, ExtentTest logger){
		this.driver = driver;
		this.report = report;
		this.logger = logger;
	}
	
	public void quitBrowser(){
			driver.quit();
		}
	
	
}
