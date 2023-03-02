package TestNG;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Pojo;
import pomFacebook.LoginPage;
import pomFacebook.SignUpPage;
import utils.Utility;

public class FaceBookTestNG {
	WebDriver driver;
	private String testID;
	String testMethodname;
	@BeforeClass
	public void lunchBrowser()
	{
		System.out.println("Before Class");
		//System.setProperty("webdriver.chrome.driver","C:\\NILESH\\Programme\\Manual\\chromedriver.exe");
		Pojo.openCromeBrowser();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
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
	
	@Test
	public void verifyPrivacyPolicyLink() throws IOException, InterruptedException
	{
		testID = "TC01";
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.clickOnPrivacyPolicy();
		ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(add.get(1));
		Thread.sleep(4000);
		
		String privacyPolicytURL = driver.getCurrentUrl();
		if(privacyPolicytURL.equals("wwwhttps://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0"))
		{
			System.out.println("Privacy Test Pass");
		}
		else
		{
			Utility.captureScreenShot(driver, testMethodname);
			System.out.println("Privacy Test Fail");
		}
		Thread.sleep(1000);
		
		
	 }
	@Test
	public void verifytermsLink() throws IOException
	{
		testID = "TC02";
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.clickOnTerms();
		ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(add.get(1));
		
		String termsURL = driver.getCurrentUrl();
		if(termsURL.equals("https://www.facebook.com/legal/terms/update"))
		{
			System.out.println("Terms Test Pass");
		}
		else
		{
			Utility.captureScreenShot(driver, testMethodname);
			System.out.println("Terms Test Fail");
		}
		

		
		
	 }
	@Test
	public void verifycookiesPolicyLink() throws IOException
	{
		testID = "TC03";
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.clickOnCookiesPolicy();
		ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(add.get(1));
		
		String cookiesPolicyURL = driver.getCurrentUrl();
		if(cookiesPolicyURL.equals("wwwhttps://www.facebook.com/privacy/policies/cookies/_redirect&entry=0"))
		{
			System.out.println("Cookies Test Pass");
		}
		else
		{
			Utility.captureScreenShot(driver, testMethodname);

			System.out.println("Cookies Test Fail");
		}
		

		
		
	 }

	
	@AfterMethod
	public void closeChildBrowser(ITestResult result) throws IOException
	{
		System.out.println(testID);

		//System.out.println(testID);
		if(ITestResult.FAILURE == result.getStatus())
		{
						Utility.captureScreenShot(driver, testID);
		}
		driver.close();
		ArrayList<String> add = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(add.get(0));
		System.out.println("After Method");
		
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("After Class");
	}
	

}
