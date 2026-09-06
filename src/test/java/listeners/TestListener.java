package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import reports.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener, IExecutionListener {

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                ExtentManager.getInstance()
                        .createTest(
                                result.getMethod()
                                        .getMethodName()
                        );

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = extentTest.get();

        test.pass("Test Passed");

        captureScreenshot(result, test);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = extentTest.get();

        test.fail(result.getThrowable());

        captureScreenshot(result, test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = extentTest.get();

        test.skip("Test Skipped");

        captureScreenshot(result, test);
    }

    private void captureScreenshot(
            ITestResult result,
            ExtentTest test) {

        Object instance = result.getInstance();

        if (instance instanceof BaseTest) {

            BaseTest baseTest =
                    (BaseTest) instance;

            WebDriver driver =
                    baseTest.getDriver();

            if (driver != null) {

                String screenshotPath =
                        ScreenshotUtils.captureScreenshot(
                                driver,
                                result.getMethod()
                                        .getMethodName()
                        );

                try {

                    test.addScreenCaptureFromPath(
                            screenshotPath
                    );

                } catch (Exception e) {

                    test.warning(
                            "Unable to attach screenshot"
                    );
                }
            }
        }
    }

    @Override
    public void onExecutionFinish() {

        ExtentManager.getInstance().flush();
    }
}
