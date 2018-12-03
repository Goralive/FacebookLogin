package selenium.core;
/**
 * Listeners, uncoment code if you want to create image on disk if test was failed
 **/
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        driver = ((WebDriverTestBase) iTestResult.getInstance()).driver;
        saveScreenshot(iTestResult.getMethod().getQualifiedName());
        // Save image on disk
      /* File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       try {
           FileUtils.copyFile(scrFile,
                   new File("c:\\tmp\\failed\\" + iTestResult.getMethod().getQualifiedName() + ".png"));
        } catch (IOException e){
            e.printStackTrace();
  }
    */}
    @Attachment (value = "{0}", type = "image/png")
    public byte [] saveScreenshot (String attachName) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
