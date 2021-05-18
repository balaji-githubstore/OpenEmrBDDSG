package com.sg.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sg.base.WebDriverWrapper;

public class SearchOrAddPatientPage {

	public static String handleAlertAndGetAlertText() {
		WebDriverWait wait = new WebDriverWait(WebDriverWrapper.driver, 50);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = WebDriverWrapper.driver.switchTo().alert().getText();
		WebDriverWrapper.driver.switchTo().alert().accept();
		return alertText;
	}

}
