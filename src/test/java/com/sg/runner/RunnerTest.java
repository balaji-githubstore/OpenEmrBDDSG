package com.sg.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
//		features = {"src/test/resources/Feature/Login.feature"
//				,"src/test/resources/Feature/Patient.feature"},
		features = { "src/test/resources/Feature" }
		, glue = { "com.sg.stepdefinitions",
				"com.sg.base" }
		, publish = true, monochrome = true
//		 ,dryRun = true
		, tags = "@invalid"
//		,tags="@invalid or @valid"
//		,tags="@invalid and @lowpriority"
		, plugin = { "html:report/index.html" })

@RunWith(Cucumber.class)
public class RunnerTest {

}
