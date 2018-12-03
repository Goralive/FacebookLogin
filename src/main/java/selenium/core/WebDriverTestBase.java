package selenium.core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * General properties for test.
 */

@Listeners(selenium.core.TestListener.class)
public class WebDriverTestBase {

    // WebDriver
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Properties
    private String browser = System.getProperty("browser", CHROME);
    private long implicitWait = Long.parseLong(PropertiesCache.getProperty("wait.implicity"));
    private long timeOutInSec = Long.parseLong(PropertiesCache.getProperty("timeOutInSec"));
    private long pageWait = Long.parseLong(PropertiesCache.getProperty("wait.page"));
    private long scriptWait = Long.parseLong(PropertiesCache.getProperty("wait.script"));

    private DesiredCapabilities setDesiredCapabilities(String platform, String remoteBrowser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (platform.equalsIgnoreCase(Platform.WIN8_1.name())) {
            caps.setPlatform(Platform.WIN8_1);
            caps.setBrowserName(remoteBrowser);
        }
        return caps;
    }


    @Parameters({"platform", "remoteBrowser"})
    @BeforeClass
    public void setUp(@Optional String platform, @Optional String remoteBrowser) throws MalformedURLException {
        switch (browser.toLowerCase()) {
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            /**
             *  For test firefox add VM option -Dbrowser=firefox But if you want to start with this flag please note that facebook have an issue with FF https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/2863
             */
            case FIREFOX:
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            case "remote":
                DesiredCapabilities caps = setDesiredCapabilities(platform, remoteBrowser);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                break;
        }
        wait = new WebDriverWait(driver, timeOutInSec);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(scriptWait, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


