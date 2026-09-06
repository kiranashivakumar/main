package test;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import driver.DriverFactory;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestData;

public class LoginTest extends BaseTest {
	LoginPage loginPage;
	@BeforeMethod
	public void initLoginPage()
	{
		  loginPage =
	                new LoginPage(DriverFactory.getDriver());
	}

    @Test
    public void validLoginTest() {

        DriverFactory.getDriver()
                .get(ConfigReader.get("url"));

        loginPage.login(
        		ConfigReader.get("username"),
        		ConfigReader.get("password")
        );
    }
    
    @Test(dataProvider="invalidLoginData",
          dataProviderClass=TestData.class)
    public void invalidLoginTest(String usernameValue,String passwordValue,String expectedButtonEnabled,String ExpectedMessage)
    {
    	loginPage.enterUsername(usernameValue);
    	loginPage.enterPassword(passwordValue);
    	if (expectedButtonEnabled.equals("DISABLED")) {

            Assert.assertFalse(
                loginPage.isSignInButtonEnabled(),
                "Sign In button should be disabled"
            );

        } else if (expectedButtonEnabled.equals("ERROR")) {

            Assert.assertTrue(
                loginPage.isSignInButtonEnabled(),
                "Sign In button should be enabled"
            );
    	
    }
}
    @Test
    public void selectOrg()
    {
    	loginPage.login(ConfigReader.get("username"),
        		ConfigReader.get("password"));
    	loginPage.ClickOrgDropdown();
    	
    }
   
}
