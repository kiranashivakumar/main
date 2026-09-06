package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import driver.DriverFactory;
import reports.ExtentManager;
import utils.ScreenshotUtils;

public class BaseTest {

    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp(ITestResult result) {

        DriverFactory.initDriver();
        

        String testName =
                result.getMethod().getMethodName();

        extentTest =
                ExtentManager.getInstance()
                        .createTest(testName);
    }
    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        // Screenshot
        ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                testName
        );

        // Extent status
        if (result.getStatus() == ITestResult.SUCCESS) {

            extentTest.pass("Test Passed");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            extentTest.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {

            extentTest.skip("Test Skipped");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void generateExtentReport() {

        ExtentManager.getInstance().flush();
    }

    public WebDriver getDriver() {

        return DriverFactory.getDriver();
    }
}
