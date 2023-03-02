package pomFacebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy (xpath="(//a[@role='button'])[2]" )
	private WebElement createnewacc;
	
	@FindBy (xpath="//input[@type='text']" )
	private WebElement sendEmail;
	
	@FindBy (xpath="//input[@type='password']" )
	private WebElement sendPassword;
	
	@FindBy (xpath="//button[@name='login']" )
	private WebElement logIn;
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			
	}
	
	public void clickOnCreateacc() {
		createnewacc.click();
	}
	
	public void sendEmail(String user) {
		sendEmail.sendKeys(user);
		
	}
	
	public void sendPasswor(String password) {
		sendPassword.sendKeys(password);
		
	}
	public void clickOnLogin() {
		logIn.click();		
	}

	

}
