package com.automationtest.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automationtest.pages.BaseClass;
import com.automationtest.pages.LoginPage;
import com.automationtest.utility.BrowserFactory;
import com.automationtest.utility.Helper;

public class LoginAppTest extends BaseClass{
	
	@Test
	public void loginApp()
	{
		logger = report.createTest("Login to CRM");
		System.out.println(driver.getTitle());
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting application.");
		loginPage.loginCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		logger.pass("Login success.");
	}

}
