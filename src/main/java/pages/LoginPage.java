package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import utils.ConfigReader;

public class LoginPage {

    private WebDriver driver;
  
    private By username = By.id("email");

    private By password =  By.id("password");

    private By loginButton = By.xpath("//*[text()='Sign In']");
    private By orgSelection=By.xpath("//*[text()='Organization']/following-sibling::button");
    private By orgList=By.xpath("//*[text()='Organization']/following-sibling::select");
    private By firstOption=By.xpath("//*[text()='Organization']/following-sibling::select/option[1]");
    private By getStartedBtn=By.xpath("//*[text()='Get started']");
    private By organisationBtn=By.xpath("//*[text()='Organization']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
//Login
    public void enterUsername(String usernameValue) {
        driver.findElement(username)
                .sendKeys(usernameValue);
    }
    public void enterPassword(String passwordValue) {
        driver.findElement(password)
                .sendKeys(passwordValue);
    }

    public void clickLogin() {
        driver.findElement(loginButton)
                .click();   
    }
    public void ClickOrgDropdown()
    {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    try {
    	    	System.out.println(driver.getCurrentUrl());
            	wait.until(ExpectedConditions.elementToBeClickable(orgSelection));
            	WebElement orgele=driver.findElement(orgSelection);
            	orgele.click();
                List<WebElement> orglist=driver.findElements(orgList);
            	wait.until(ExpectedConditions.visibilityOfAllElements(orglist));
            	Actions action=new Actions(driver);
            	action.sendKeys(Keys.ENTER).build().perform();
            	WebElement getStartedBtnc=driver.findElement(getStartedBtn);
            	wait.until(ExpectedConditions.elementToBeClickable(getStartedBtnc));
            	getStartedBtnc.click();
    	        System.out.println("Organization dropdown clicked");
    	        wait.until(ExpectedConditions.urlToBe(ConfigReader.get("url").concat("app/dashboard")));
    	        System.out.println(driver.getCurrentUrl());
    	    } catch (TimeoutException e) {
               System.out.println(driver.getCurrentUrl());
    	        System.out.println("Organization selection not required. Continuing to dashboard.");
    	    }
    	}
   
    public void login(String usernameValue, String passwordValue) {
        enterUsername(usernameValue);
        enterPassword(passwordValue);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
       
        try {
        	wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        	clickLogin();
        }
        catch(TimeoutException e)
        {
        Alert alert= driver.switchTo().alert();
        alert.accept();
        }
       
    }
    public boolean isSignInButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }
    
}