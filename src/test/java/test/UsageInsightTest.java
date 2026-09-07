package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import driver.DriverFactory;
import pages.LoginPage;
import pages.UsageInsightPage;
import utils.ConfigReader;

public class UsageInsightTest extends BaseTest {
	LoginPage loginPage;
	UsageInsightPage usageInsightPage;
	@BeforeMethod
	public void initLoginPage()
	{
		  loginPage =
	                new LoginPage(DriverFactory.getDriver());
		  usageInsightPage=new UsageInsightPage(DriverFactory.getDriver());
	}
	@Test 
	public void usageInsight() throws InterruptedException
	   {
		loginPage.login(ConfigReader.get("username"),
       		ConfigReader.get("password"));
		loginPage.ClickOrgDropdown();
		usageInsightPage.clickOrganisationModule();
//		usageInsightPage.createTask();
//		usageInsightPage.searchMemberonUsageInsight("qhGcz");
		
	   }
}
