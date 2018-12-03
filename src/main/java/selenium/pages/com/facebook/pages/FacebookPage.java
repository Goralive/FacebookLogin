package selenium.pages.com.facebook.pages;
/**
 * CSS Locators for Cucumber on Facebook page
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookPage {

    private WebDriver webDriver;

    public FacebookPage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }
    public void fillEmail(String email){
        WebElement fillEmail = webDriver.findElement(By.cssSelector("#email"));
        fillEmail.sendKeys(email);    }

    public void fillPassword(String password) {
        WebElement passwordFill = webDriver.findElement(By.cssSelector("#pass"));
        passwordFill.sendKeys(password);
    }
    public void clickLogIn () {
        WebElement clickLogIn = webDriver.findElement(By.cssSelector("#u_0_2"));
        clickLogIn.click();
    }
    public boolean verifyTitle (){
        if (webDriver.getTitle().toLowerCase().contains("facebook")) {
            return true;
        }
        return false;
    }
}

