package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Pojo {
	
	public static WebDriver openCromeBrowser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\NILESH\\Programme\\Manual\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver openFirFoxBrowser()
	{
		System.setProperty("webdriver.gecko.driver","C:\\NILESH\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	
	
	

}
