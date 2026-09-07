package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import driver.DriverFactory;
import utils.ConfigReader;


public class UsageInsightPage {
	 private static final String NoSuchElementInterception = null;
	 LoginPage loginPage =
	            new LoginPage(DriverFactory.getDriver());
	String memberName=RandomStringUtils.randomAlphabetic(5);
	String memberEmail;
	String memberScore = "";
	private WebDriver driver;
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 private By username = By.id("email");
	  private By organisationBtn=By.xpath("//*[text()='Organization']");
	  private By dashboardBtn=By.xpath("(//*[text()='Dashboard'])[1]");
	  private By membersBtn=By.xpath("//*[text()='Members']");
	  private By inviteMembers=By.xpath("//*[text()='Invite Member']");
	  private By nameField=By.id("name");
	  private By emailField=By.id("email");
	  private By roleField=By.xpath("//*[text()='Select Access Level']");
	  private By costField=By.id("cost");
	  private By menu=By.xpath("(//*[@aria-haspopup=\"menu\"])[1]");
	  private By logOutBtn=By.xpath("//*[text()='Log out']");
	  private By copyBtn=By.id("copbtn");
	  private By activationEmail=By.xpath("//*[text()='Ralvie AI Activate Existing Members']");
	  private By ActivateYourAccountClick=By.xpath("//*[text()='Activate your account']");
	  private By goToLoginBtn=By.xpath("//*[text()='Go to Login']");
	  private By password=By.xpath("//*[@id='password']");
	  private By confirmPassword=By.xpath("//*//*[@id='confirmPassword']");
	  private By activateAccountBtn=By.xpath("(//*[text()='Activate Account'])[2]");
	  private By clickMayBeLater=By.xpath("//*[text()='Maybe Later']");
	  private By membersList=By.xpath("//table//tbody/tr/td[2]//span[2]");
	  private By nextBtn=By.xpath("(//*[@class=\"flex items-center gap-2\"]/button[2])[1]");
	  private By workBtn=By.xpath("//*[text()='Work']");
	  private By tasksBtn=By.xpath("//*[text()='Tasks']");
	  private By newTaskBtn=By.xpath("//*[text()='New Task']");
	  private By taskTitleField=By.id("title");
	  private By taskProjectselection=By.xpath("//*[text()='Select Project']");
	  private By taskSelection=By.id("radix-_r_ju_");
	  
	  public UsageInsightPage(WebDriver driver)
	  {
		  this.driver=driver;
	  }
	 public void AddMember() 
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 Actions action=new Actions(driver);
		driver.findElement(inviteMembers).click();
		 System.out.println(driver.getCurrentUrl());
		 String applicationTab = driver.getWindowHandle();
		 String parentUrl=driver.getCurrentUrl();
		 driver.switchTo().newWindow(WindowType.TAB);
		 driver.get("https://generator.email/");
		 WebElement cpyBtn=driver.findElement(copyBtn);
			 js.executeScript("document.body.style.zoom='70%'");

			 wait.until(ExpectedConditions.presenceOfElementLocated(copyBtn));
			    wait.until(ExpectedConditions.elementToBeClickable(cpyBtn));

			    cpyBtn.click();
			    String emailTab=driver.getWindowHandle();
			    driver.switchTo().window(applicationTab);
//		 driver.get(parentUrl);
		 wait.until(ExpectedConditions.presenceOfElementLocated(nameField));
		 WebElement name=driver.findElement(nameField);
		 name.sendKeys(memberName);
		 WebElement email=driver.findElement(emailField);
		 email.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		memberEmail=email.getAttribute("value");
		 System.out.println(memberEmail);
		 driver.findElement(roleField).click();
		 action.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).build().perform();
		 wait.until(ExpectedConditions.presenceOfElementLocated(costField));
		 WebElement costF=driver.findElement(costField);
		 js.executeScript(
				    "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
				    costF
				);
		 costF.sendKeys("10");
//		 js.executeScript("arguments[0].value='10';", costF);
		 WebElement inviteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Invite Member'])[3]")));
		 Assert.assertTrue(inviteButton.isEnabled(),
			        "Invite Member button is not enabled");
		 inviteButton.click();
		 driver.switchTo().window(emailTab);
		 js.executeScript("document.body.style.zoom='25%'");
		 wait.until(ExpectedConditions.elementToBeClickable(activationEmail));
		 WebElement activationEmailfield=driver.findElement(activationEmail);
		 activationEmailfield.click();
		 js.executeScript("document.body.style.zoom='25%'");
		 action.scrollToElement(driver.findElement(ActivateYourAccountClick));
		 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ActivateYourAccountClick)));
		 driver.findElement(ActivateYourAccountClick).click();
		 for (String handle : driver.getWindowHandles()) {
			    if (!handle.equals(applicationTab) &&
			        !handle.equals(emailTab) ){

			        driver.switchTo().window(handle);
			        break;
			    }
		 }
		 loginWithCreatedAccount();
		 signOutSignIn();
		 }
	 public void loginWithCreatedAccount()
	 {
		 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.presenceOfElementLocated(password));
		 driver.findElement(password).sendKeys("Admin@123");
		 driver.findElement(confirmPassword).sendKeys("Admin@123");
		 driver.findElement(activateAccountBtn).click();
		 System.out.println(memberEmail);
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(username)));
		 loginPage.login(memberEmail, "Admin@123");	 
	 }
	 public void signOutSignIn()
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.elementToBeClickable(clickMayBeLater));
		 driver.findElement(clickMayBeLater).click();
		 wait.until(ExpectedConditions.elementToBeClickable(menu));
		 driver.findElement(menu).click();
		 driver.findElement(logOutBtn).click();
		 System.out.println("Successfully logged out from the application");
		 loginPage.login(ConfigReader.get("username"),
	        		ConfigReader.get("password"));
		 loginPage.ClickOrgDropdown();
	 }
	 public void verifyUsageInsightScore()
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.presenceOfElementLocated(dashboardBtn));
		 driver.findElement(dashboardBtn).click();
		 wait.until(ExpectedConditions.urlContains("https://ralvie.minervaiotstaging.com/app/dashboard"));
	 }
	  public void clickOrganisationModule() 
	  {
		  Actions action=new Actions(driver);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.elementToBeClickable(organisationBtn));
		 WebElement orgBtn=driver.findElement(organisationBtn);
		 orgBtn.click();
		 action.click();
		 wait.until(ExpectedConditions.elementToBeClickable(membersBtn));
		  driver.findElement(membersBtn).click();
		  action.click();
		  AddMember();
		  verifyUsageInsightScore();
		  searchMemberonUsageInsight(memberName,1);
	  }
	  public void searchMemberonUsageInsight(String searchMember,int expectedScore)
	  {
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  boolean memberFound = false;
		  while (true) {
		      List<WebElement> memberList = driver.findElements(membersList);
		      for (WebElement member : memberList) {
		          String fetchMemberName = member.getText().trim();
		          if (fetchMemberName.equalsIgnoreCase(searchMember)) {
		        	  WebElement row = member.findElement(By.xpath("./ancestor::tr"));
		              // Get Total Score from same row
		              memberScore = row.findElement(By.xpath("./td[5]"))
		                               .getText()
		                               .trim();
		              memberFound = true;
		              
		              break;
		          }
		      }
		      if (memberFound) {
		          break;
		      }
		      wait.until(ExpectedConditions.presenceOfElementLocated(nextBtn));
		      WebElement nextButton = driver.findElement(nextBtn);
		      if (!nextButton.isEnabled() ||
		          nextButton.getAttribute("disabled") != null) {
		          break;
		      }
		      nextButton.click();
		  } 
		  if (!memberFound) {

		      System.out.println(
		          "Searched member '" + searchMember +
		          "' is not present in the list."
		      );
		  } 
		  Assert.assertEquals(
				  memberScore,
			        String.valueOf(expectedScore), "Total Score mismatch for member: " + searchMember );

			    System.out.println(
			        "Member '" + searchMember +
			        "' has Total Score = " + memberScore );
			    
			    driver.findElement(menu).click();
			    wait.until(ExpectedConditions.elementToBeClickable(logOutBtn));
				 driver.findElement(logOutBtn).click();
				 System.out.println("Successfully logged out from the application after adding member and checked the total score");
	  }
	  
	  public void createTask() throws InterruptedException
	  {
		  Actions action=new Actions(driver);
//		  loginPage.login(memberEmail, "Admin@123");
		  loginPage.login(ConfigReader.get("username"),
	        		ConfigReader.get("password"));
		  loginPage.ClickOrgDropdown();
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.elementToBeClickable(workBtn));
		  driver.findElement(workBtn).click();
		  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(tasksBtn))).click();
//		  driver.findElement(tasksBtn).click();
		  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(newTaskBtn))).click();
//		  driver.findElement(newTaskBtn).click();
		  driver.findElement(taskTitleField).sendKeys("Automation".concat(RandomStringUtils.randomAlphabetic(3)));
		  try {
		  driver.findElement(taskProjectselection).click();
		  driver.findElement(taskSelection).click();
		  } catch(NoSuchElementException e)
		  {
			  System.out.println("No projects are they to create the tasks");
			  System.out.println(e.getMessage());
		  }
		  
		  }
	  
		  
	  }
	  
	  

