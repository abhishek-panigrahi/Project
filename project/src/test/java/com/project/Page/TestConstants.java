package com.project.Page;

public class TestConstants {

	public static String testSiteURL = "https://www.saucedemo.com/";
	public static String category = "Smoke";
	public static String author = "Abhishek\tPanigrahi";
	public static String testSiteName = "SauceDemo.com";
	
	public static void environmentSetter(String environment)
	{
		switch(environment)
		{
		case "saucedemo":
			testSiteURL = "https://www.saucedemo.com/";
		  break;
		
		case "google":
			testSiteURL = "https://www.google.com/";
		   break;
		}
	}
}
