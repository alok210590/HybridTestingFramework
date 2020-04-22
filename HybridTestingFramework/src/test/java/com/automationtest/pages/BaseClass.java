package com.automationtest.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.automationtest.utility.BrowserFactory;
import com.automationtest.utility.ConfigDataProvider;
import com.automationtest.utility.ExcelDataProvider;
import com.automationtest.utility.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Setting up report and test is getting ready", true);
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter
				(new File(System.getProperty("user.dir")+"/Reports/ExecutionReport_"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extentHtmlReporter);
		Reporter.log("Settings done - Test can be started", true);
	}
	
	@BeforeClass
	public void setup() {
		Reporter.log("Trying to start browser and Getting application ready.", true);
		driver = BrowserFactory.launchApplication(driver, config.getBrowser(), config.getURL());
		Reporter.log("Browser and application is up and running", true);
	}
	
	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException 
	{
		Reporter.log("Test is about to end", true);
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.fail("Test Failed ", 
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.pass("Test Passed ", 
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.skip("Test Skipped ", 
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
		Reporter.log("Test completed >> Report generated", true);
	}

}
