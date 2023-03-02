package TestNG;
	import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Pojo;
import pomFacebook.LoginPage;
	import pomFacebook.SignUpPage;
import utils.Utility;

	public class FBcrome {
		WebDriver driver;
		LoginPage loginPage;
		SignUpPage signUpPage;
		String testMethodname;
		private String testID;
		@Parameters ("browser")
		@BeforeTest
		public void launchBrowser(String browserName)
		{
			System.out.println(browserName);
			if(browserName.equals("Chrome"))
			{
//				System.setProperty("webdriver.chrome.driver","C:\\NILESH\\Programme\\Manual\\chromedriver.exe");
				Pojo.openCromeBrowser();
				driver = new ChromeDriver();
			}
			if(browserName.equals("Firefox"))
			{
				//System.setProperty("webdriver.gecko.driver","C:\\NILESH\\geckodriver.exe");
				Pojo.openFirFoxBrowser();
				driver = new FirefoxDriver();
			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
		}
		
		@BeforeClass
		public void test()
		{
			System.out.println("Before Class");
			loginPage = new LoginPage(driver);
			signUpPage = new SignUpPage(driver);
			
		}
		
		@BeforeMethod
		public void goToSignUpForm(Method method)
		{
			driver.manage().deleteAllCookies();
	        System.out.println("Before Method");
	        driver.get("https://www.facebook.com/");
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.clickOnCreateacc();
	        testMethodname = method.getName();
	        
		}
		@Test (enabled = false)
		
		public void signIn() throws IllegalStateException, IOException
		{
			String data = Utility.getDataFromExcelSheet("Sheet1", 1, 0);
			LoginPage loginPage = new LoginPage(driver);
			loginPage.sendEmail(data);
			data =Utility.getDataFromExcelSheet("Sheet1", 1, 1);
			loginPage.sendPasswor(data);
			loginPage.clickOnLogin();
			String loginUrl = driver.getCurrentUrl();
			String expectedUrl ="https://WWW.facebook.com/";
			
			Assert.assertEquals(loginUrl, expectedUrl,"Home page not Found");
			
							

			
			
			
			
		}
		@Test 
		public void verifyPrivacyPolicyLink() throws IOException, InterruptedException
		{
			testID = "TC01";
			//SignUpPage signUpPage = new SignUpPage(driver);
			signUpPage.clickOnPrivacyPolicy();
			ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(add.get(1));
			Thread.sleep(4000);
			String privacyPolicytURL = driver.getCurrentUrl();
			if(privacyPolicytURL.equals("https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=011"))
			{
				System.out.println("Privacy Test Pass");
			}
			else
			{
				System.out.println("Privacy Test Fail");
				Utility.captureScreenShot(driver, testMethodname);
			}
			
			
			
		 }
		@Test 
		public void verifytermsLink() throws IOException, InterruptedException
		{
			testID = "TC02";
			//SignUpPage signUpPage = new SignUpPage(driver);
			signUpPage.clickOnTerms();
			ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(add.get(1));
			Thread.sleep(4000);
			
			String termsURL = driver.getCurrentUrl();
			if(termsURL.equals("https://www.facebook.com/legal/terms/update11"))
			{
				System.out.println("Terms Test Pass");
			}
			else
			{
				System.out.println("Terms Test Fail");
				Utility.captureScreenShot(driver, testMethodname);

			}
			
			
			
		 }
		@Test 
		public void verifycookiesPolicyLink() throws IOException, InterruptedException
		{
			testID = "TC03";
			//SignUpPage signUpPage = new SignUpPage(driver);
			signUpPage.clickOnCookiesPolicy();
			ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(add.get(1));
			Thread.sleep(4000);
			String cookiesPolicyURL = driver.getCurrentUrl();
			if(cookiesPolicyURL.equals("https://www.facebook.com/privacy/policies/cookies/?entry_point=cookie_policy_redirect&entry=011"))
			{
				System.out.println("Cookies Test Pass");
			}
			else
			{
				System.out.println("Cookies Test Fail");
				Utility.captureScreenShot(driver, testMethodname);

			}
			
			
			
		 }

		
		@AfterMethod
		public void closeChildBrowser(ITestResult result) throws IOException, InterruptedException
		{
			
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenShot(driver, testID);
			}
			driver.navigate().back();
			
			
			
			driver.close();
			ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(add.get(0));
			System.out.println("After Method");
			
		}
		
		@AfterClass
		public void clearPOMObject()
		{
			signUpPage = null;
			loginPage = null;
			System.out.println("After Class");
		}

		
		@AfterTest
		public void closeBrowser()
		{
			driver.quit();
			driver = null;
			System.out.println("After Test");
		    System.gc(); //garbage collector
		}
		

	}



