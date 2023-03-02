package TestNG;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pomFacebook.LoginPage;
import pomFacebook.SignUpPage;

public class FaceBookLearnNG {
	
WebDriver driverTest;
	
	@BeforeClass
	public void lunchBrowser()
	{
		System.out.println("Before Class");
		System.setProperty("webdriver.chrome.driver","C:\\NILESH\\Programme\\Manual\\chromedriver.exe");
		driverTest = new ChromeDriver();
		driverTest.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driverTest.manage().window().maximize();
		driverTest.manage().deleteAllCookies();
		driverTest.manage().window().maximize();
		
		
	}
	
	@BeforeMethod
	public void goToSignUpForm()
	{
		driverTest.manage().deleteAllCookies();
        System.out.println("Before Method");
        driverTest.get("https://www.facebook.com/");
        LoginPage loginPage = new LoginPage(driverTest);
        loginPage.clickOnCreateacc();
	}
	
	@Test
	public void verifyPrivacyPolicyLink()
	{
		SignUpPage signUpPage = new SignUpPage(driverTest);
		signUpPage.clickOnLearnMore();
		ArrayList<String> add = new ArrayList<String>(driverTest.getWindowHandles());
		driverTest.switchTo().window(add.get(1));
		
		String learnMoretURL = driverTest.getCurrentUrl();
		if(learnMoretURL.equals("https://www.facebook.com/help/637205020878504"))
		{
			System.out.println("Learn More Test Pass");
		}
		else
		{
			System.out.println("Learn More Test Fail");
		}
		
		
	 }
	@AfterMethod
	public void closeChildBrowser()
	{
		driverTest.close();
		ArrayList<String> add = new ArrayList<String>(driverTest.getWindowHandles());
		driverTest.switchTo().window(add.get(0));
		System.out.println("After Method");
		
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driverTest.quit();
		System.out.println("After Class");
	}


}
