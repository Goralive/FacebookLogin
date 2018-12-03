package selenium.pages;
/**
 * Locators and methods for facebook login page. Account was disabled :( Code was tested on my personal account
 */

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static selenium.core.PropertiesCache.getProperty;

public class  SeleniumFacebookLogInPage extends AbstractPage {


    @FindBy(xpath = "//*[@id='email']")
    WebElement emailInputField;

    @FindBy(xpath = "//*[@id='pass']")
    WebElement passInputField;

    @FindBy(xpath = "//*[@id='loginbutton']/*[1]") // select first child from ID loginbutton as an example. I could use search by //*[@id='u_0_2']
            WebElement logInButton;

    @FindBy(xpath = "//a[@title='Profile']")
    WebElement validAccount;

    public SeleniumFacebookLogInPage(WebDriver driver) {
        super(driver);
    }

    public void openFacebook (){ // Maybe should this method delete and create link in the WebDriverTestBase
        driver.get(getProperty("urlFacebook"));
    }

    public boolean isAtLoginPage() { // Verify user on facebook page
        if (driver.getTitle().toLowerCase().contains("facebook")) {
            return true;
        }
        return false;
    }

    public void enterEmailToInputField() {
        emailInputField.click();
        emailInputField.sendKeys(getProperty("email"));
    }

    public void enterPassToPassField() {
        passInputField.click();
        passInputField.sendKeys(getProperty("pass"));
    }

    public void clickOnLogInButton() {
        logInButton.click();
    }

    public boolean checkLogInToDisabledProfile() { //Check for invalid account
        try {
            validAccount.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException, seems your ACCOUNT IS INVALID" + e.getMessage());
        }
        return false;
    }
}





