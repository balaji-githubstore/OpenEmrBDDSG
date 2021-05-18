package com.sg.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.sg.base.WebDriverWrapper;

public class LoginPage {
	private static By usernameLocator = By.xpath("//*[text()='Username:']/following::input");
	private static By passwordLocator = By.xpath("//*[text()='Password:']/following::input");
	private static By languageLocator = By.name("languageChoice");
	private static By loginLocator = By.xpath("//button[@type='submit']");
	private static By errorLocator=By.xpath("//div[contains(text(),'Invalid')]");

	public static void enterUsername(String username) {
		WebDriverWrapper.driver.findElement(usernameLocator).sendKeys(username);
		}

	public static void enterPassword(String password) {
		WebDriverWrapper.driver.findElement(passwordLocator).sendKeys(password);
	}

	public static void selectLanguageByText(String language) {
		Select selectLanguage = new Select(WebDriverWrapper.driver.findElement(languageLocator));
		selectLanguage.selectByVisibleText(language);
	}

	public static void clickOnLogin() {
		WebDriverWrapper.driver.findElement(loginLocator).click();
	}
	
	public static String getLoginErrorMessage()
	{
		return WebDriverWrapper.driver.findElement(errorLocator).getText().trim();
	}

}
