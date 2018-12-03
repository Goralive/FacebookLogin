package com.selenium;
/**
 * Please note, also you can start a test by writing a in a terminal command  mvn clean test -DsuiteXmlFile=suite.xml
 */

import org.testng.annotations.*;
import selenium.core.WebDriverTestBase;
import selenium.pages.SeleniumFacebookLogInPage;
import static org.testng.Assert.assertTrue;

public class TestSeleniumFacebookLogin extends WebDriverTestBase {

    public SeleniumFacebookLogInPage seleniumFacebookLogin;

    @BeforeClass //don't want to add selenium object in each case. Maybe there is a better solution
    public void setupObjects (){
        seleniumFacebookLogin = new SeleniumFacebookLogInPage(driver);
    }

    @Test (priority = 1) // Check that facebook page is opened
    public void verifyFacebookPageIsOpened (){
        seleniumFacebookLogin.openFacebook();
        assertTrue(seleniumFacebookLogin.isAtLoginPage());
    }

    @Test (priority = 2) // Send data to the fields
    public void logToProfile () {
        seleniumFacebookLogin.enterEmailToInputField();
        seleniumFacebookLogin.enterPassToPassField();
        seleniumFacebookLogin.clickOnLogInButton();
    }
    @Test (priority = 3) // Please note that you need to change creds to pass test for facebook in the recources/ test.properties Verify that page is opened
    public void verifyProfileIsOpened (){
        assertTrue(seleniumFacebookLogin.checkLogInToDisabledProfile());
    }
}
