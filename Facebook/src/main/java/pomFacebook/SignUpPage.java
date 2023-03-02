package pomFacebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	

	@FindBy (xpath="//a[@href='/legal/terms/update']" )
	private WebElement terms;
	
	@FindBy (xpath="//a[@href='/about/privacy/update']" )
	private WebElement privacypolicy;

	@FindBy (xpath="(//a[@href='/policies/cookies/'])[2]" )
	private WebElement cookiesPolicy;
	
	@FindBy (xpath="//a[@href='/help/637205020878504']")
	private WebElement learnMore;

	
	public SignUpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			
	}
	
	public void clickOnTerms() {
		terms.click();
	}
	public void clickOnPrivacyPolicy() {
		privacypolicy.click();
	}
	public void clickOnCookiesPolicy() {
		cookiesPolicy.click();
	}
	public void clickOnLearnMore() {
		learnMore.click();
	}
}
