package com.orange.hrm.base;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orange.hrm.utils.Constants;
import com.orange.hrm.utils.Utilily;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTestCase {

	ExtentReports extent;
	ExtentTest logger;
	private static WebDriver driver;

	@BeforeTest
	public void startTest(){
		extent = new ExtentReports(Constants.userDirectory+"\\test-output\\ExtentReport.html", true);
		extent.loadConfig(new File(Constants.userDirectory+"\\config\\extent-config.xml"));
	}
	
	@Test
	public void passTest(){
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case Passed is : passTest");
	}
	
	@Test
	public void failTest(){
		logger = extent.startTest("passTest");
		Assert.assertTrue(false);
		logger.log(LogStatus.FAIL, "Test Case Failed is : failTest");
	}
	
	@Test
	public void skipTest(){
		logger = extent.startTest("passTest");
		logger.log(LogStatus.SKIP, "Test Case Skipped is : skipTest");
		throw new SkipException("This is not ready for testing");
	}
	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test case failed is :"+result.getName());
			logger.log(LogStatus.FAIL, "Test case failed is :"+result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test case skipped is :"+result.getName());
			logger.log(LogStatus.SKIP, "Test case skipped is :"+result.getThrowable());
		}
		extent.endTest(logger);
	}
	
	@AfterTest
	public void endTest(){
		extent.flush();
		extent.close();
	}
	
	/*@BeforeTest
	public void setUp() {
		String browser = new Utilily().getBaseProperty("browser.name");
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", Constants.userDirectory + "\\server\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", Constants.userDirectory + "\\server\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			
			break;
		}
	}*/
}
