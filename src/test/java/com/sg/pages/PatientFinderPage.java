package com.sg.pages;

import org.openqa.selenium.By;

import com.sg.base.WebDriverWrapper;

public class PatientFinderPage {
	private static String finFrameName = "fin";
	private static By addNewPatientLocator = By.id("create_patient_btn1");

	public static void SwitchToFinFrame() {
		WebDriverWrapper.driver.switchTo().frame(finFrameName);
	}

	public static void clickAddNewPatient() {
		WebDriverWrapper.driver.findElement(addNewPatientLocator).click();
	}

	public static void switchToMainHtml() {
		WebDriverWrapper.driver.switchTo().defaultContent();
	}

}
