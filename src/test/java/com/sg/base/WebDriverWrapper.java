package com.sg.base;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWrapper {
	public static WebDriver driver;
	public static Scenario scenario;
	public static WebDriverWait wait;
	public static Actions action;
	
	public static void launchBrowser(String browser)
	{

		switch (browser.toLowerCase()) {
		case "ff":
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			WebDriverWrapper.driver=new FirefoxDriver();
			break;
		case "ie":
		case "internetexplorer":
			WebDriverManager.iedriver().setup();
			WebDriverWrapper.driver=new InternetExplorerDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			WebDriverWrapper.driver=new ChromeDriver();
			break;
		}
		
		WebDriverWrapper.driver.manage().window().maximize();
		WebDriverWrapper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWrapper.driver.get("https://demo.openemr.io/b/openemr");
		
		WebDriverWrapper.wait=new WebDriverWait(WebDriverWrapper.driver, 50);
		WebDriverWrapper.action=new Actions(WebDriverWrapper.driver);
	}
	
	@Before
	public void start(Scenario scenario)
	{
		WebDriverWrapper.scenario=scenario;
	}
	
//	@AfterStep
//	public void runEachStep(Scenario scenario)
//	{
//		TakesScreenshot ts=(TakesScreenshot) WebDriverWrapper.driver;
//		byte[] screenshot= ts.getScreenshotAs(OutputType.BYTES);
//		scenario.attach(screenshot, "image/png", "openemr_screenshot"+new Date().toString());
//	}
	
	
	@After
	public void end(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			TakesScreenshot ts=(TakesScreenshot) WebDriverWrapper.driver;
			byte[] screenshot= ts.getScreenshotAs(OutputType.BYTES);
			
			scenario.attach(screenshot, "image/png", "openemr_screenshot"+new Date().toString());
		}
		
//		System.out.println(scenario.getStatus());
//		System.out.println(scenario.getName());
//		System.out.println(scenario.isFailed());
//		System.out.println(scenario.getUri());
//		scenario.log("balaji dinakaran");
		
		WebDriverWrapper.driver.quit();
	}
}
