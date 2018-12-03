package selenium.pages.com.facebook.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Steps for Cucumber
 */
import selenium.pages.com.facebook.pages.FacebookPage;

public class FacebookSteps {

    public static final String REGISTER_PAGE = "https://facebook.com";

    private WebDriver webDriver;
    private FacebookPage facebookPage;

    @Given("^I am on Facebook login page")
    public void openRegisterPage() {
        ChromeDriverManager.getInstance().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(REGISTER_PAGE);
    }

    @When("^I fill my email \"([^\"]*)\"")
    public void fillEmail(String email){
        facebookPage =  new FacebookPage(webDriver);
        facebookPage.fillEmail(email);
    }

    @And("^I fill my password \"([^\"]*)\"")
    public void fillPassword (String password){
        facebookPage = new FacebookPage(webDriver);
        facebookPage.fillPassword(password);
    }
    @And ("^I click on 'LogIn'")
    public void clickLogIn (){
        facebookPage =  new FacebookPage(webDriver);
        facebookPage.clickLogIn();
    }
    @Then("^I'm login with current profile")
    public void checkProfile (){
        facebookPage = new FacebookPage(webDriver);
        facebookPage.verifyTitle();
    }
    @After
    public void tearDown (){
        webDriver.quit();
    }
}
