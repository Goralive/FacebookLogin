package com.facebook;
/**
 * Open Facebook login page and verify. Cucumber test. Features, Gherkin scenario, you can find in the resources
 */

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", glue = "selenium.pages.com.facebook.steps")
public class LogInFacebook extends AbstractTestNGCucumberTests {

}
