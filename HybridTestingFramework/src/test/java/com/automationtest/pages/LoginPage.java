package com.automationtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy (name = "email") WebElement userName;
	
	@FindBy (name = "password") WebElement password;
	
	@FindBy (xpath = "//div[@class=\"ui middle aligned center aligned grid\"]") WebElement loginBtn;
	
	public void loginCRM(String userNameApp, String passwordApp)
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userName.sendKeys(userNameApp);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		password.sendKeys(passwordApp);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginBtn.click();
	}
	
}
