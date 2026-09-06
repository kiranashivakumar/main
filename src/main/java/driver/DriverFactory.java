package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import pages.LoginPage;
import utils.ConfigReader;

public class DriverFactory {

    private static WebDriver driver;
    static LoginPage loginPage;
    
    public static void initDriver() {
        driver = new ChromeDriver();
        
        loginPage =
                new LoginPage(DriverFactory.getDriver());
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
