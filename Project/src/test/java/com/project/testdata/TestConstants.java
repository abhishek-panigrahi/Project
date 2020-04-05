package com.project.testdata;

import com.project.configure.BaseClass;

public class TestConstants extends BaseClass{
	
	public static void environmentSetter()
	{

		category = "Smoke";
		author = "Abhishek\tPanigrahi";
		testSiteName = "SauceDemo.com";
		
		environment = config.getProperty("Environment");
		browser = config.getProperty("Browser");
		
		switch(environment)
		{
		case "saucedemo":
			testSiteURL = config.getProperty("testSiteURL_saucedemo");
		  break;
		
		case "google":
			testSiteURL = config.getProperty("testSiteURL_google");
		   break;
		}
	}
}
