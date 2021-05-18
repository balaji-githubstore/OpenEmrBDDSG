package com.sg.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sg.base.WebDriverWrapper;
import com.sg.pages.DashboardPage;
import com.sg.pages.PatientFinderPage;
import com.sg.pages.SearchOrAddPatientPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatientStepDefinitions {
	private static String actualAlertValue;

	@When("I mouseover on patient-client")
	public void i_mouseover_on_patient_client() {
		DashboardPage.mouseHoverOnPatientClient();
	}

	@When("I click on patients")
	public void i_click_on_patients() {
		DashboardPage.ClickOnPatients();
		
	}

	@When("I click on add new patient")
	public void i_click_on_add_new_patient() {
		PatientFinderPage.SwitchToFinFrame();
		PatientFinderPage.clickAddNewPatient();
		PatientFinderPage.switchToMainHtml();	
	}

	@When("I fill the patient detail")
	public void i_fill_the_patient_detail(DataTable dataTable) {

		System.out.println(dataTable);

		List<Map<String, String>> ls = dataTable.asMaps();

//	    System.out.println(ls.get(0).get("firstname"));
//	    System.out.println(ls.get(0).get("lastname"));
//	    System.out.println(ls.get(0).get("dob"));
//	    System.out.println(ls.get(0).get("gender"));

		WebDriverWrapper.driver.switchTo().frame("pat");
		WebDriverWrapper.driver.findElement(By.id("form_fname")).sendKeys(ls.get(0).get("firstname"));
		WebDriverWrapper.driver.findElement(By.id("form_lname")).sendKeys(ls.get(0).get("lastname"));
		WebDriverWrapper.driver.findElement(By.id("form_DOB")).sendKeys(ls.get(0).get("dob"));

		Select selectLanguage = new Select(WebDriverWrapper.driver.findElement(By.id("form_sex")));
		selectLanguage.selectByVisibleText(ls.get(0).get("gender"));

	}

	@When("I click on create new patient")
	public void i_click_on_create_new_patient() {
		WebDriverWrapper.driver.findElement(By.id("create")).click();
		WebDriverWrapper.driver.switchTo().defaultContent();
	}

	@When("I click on confirm create new patient")
	public void i_click_on_confirm_create_new_patient() {
		WebDriverWrapper.driver.switchTo()
				.frame(WebDriverWrapper.driver.findElement(By.xpath("//iframe[contains(@src,'popup')]")));
		WebDriverWrapper.driver.findElement(By.xpath("//*[@value='Confirm Create New Patient']")).click();
		WebDriverWrapper.driver.switchTo().defaultContent();
	}

	@When("I handle alert")
	public void i_handle_alert() {
		actualAlertValue = SearchOrAddPatientPage.handleAlertAndGetAlertText();
		WebDriverWrapper.scenario.log(actualAlertValue);
	}

	@When("I close happy birthday pop when available")
	public void i_close_happy_birthday_pop_when_available() {
		if(WebDriverWrapper.driver.findElements(By.xpath("//*[@class='closeDlgIframe']")).size()>0)
		{
			WebDriverWrapper.driver.findElement(By.xpath("//*[@class='closeDlgIframe']")).click();
		}
	}

	@Then("I validate alert message receiver {string}")
	public void i_validate_alert_message_receiver(String expectedValue) {
		Assert.assertTrue(actualAlertValue.contains(expectedValue));
	}

	@Then("I validate the added patient detail {string}")
	public void i_validate_the_added_patient_detail(String expectedValue) {
		WebDriverWrapper.driver.switchTo().frame("pat");
		String actualValue = WebDriverWrapper.driver.findElement(By.xpath("//*[contains(text(),'Medical Record')]"))
				.getText();
		Assert.assertEquals(actualValue, expectedValue);
		WebDriverWrapper.driver.switchTo().defaultContent();
	}

}
