package testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParametersXML {
	
	public static WebDriver driver;
	public static DesiredCapabilities cap = new DesiredCapabilities();
	
	@Parameters({"browser"})
	@Test
	public void launchBrowser(String browser) throws MalformedURLException {
		
		Date d = new Date();
		System.out.println("called with paramemter: " + browser + "---" + d.toString());
		
		if(browser.equals("firefox")) {
			
			System.out.println("firefox function called" + d.toString());
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			cap.setCapability(CapabilityType.BROWSER_NAME,"firefox");
		    cap.setPlatform(Platform.ANY);
		    firefoxOptions.merge(cap);
			
		} else if(browser.equals("chrome")) {
			
			System.out.println("chrome function called" + d.toString());
			ChromeOptions chromeOptions = new ChromeOptions();
		    cap.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		    cap.setPlatform(Platform.ANY);
		    chromeOptions.merge(cap);
			
		} else if(browser.equals("safari")) {
			
			System.out.println("safari function called" + d.toString());
			SafariOptions safariOptions = new SafariOptions();
		    cap.setCapability(CapabilityType.BROWSER_NAME,"safari");
		    cap.setPlatform(Platform.MAC);
		    safariOptions.merge(cap);
			
		} else if(browser.equals("opera")) {
			
			System.out.println("opera function called" + d.toString());
			OperaOptions operaOptions = new OperaOptions();
		    cap.setCapability(CapabilityType.BROWSER_NAME,"opera");
		    cap.setPlatform(Platform.ANY);
		    operaOptions.merge(cap);
			
		}
		
		driver = new RemoteWebDriver(new URL("http://192.168.8.101:4444"), cap);
		driver.get("https://www.paypal.com/fj/signin?country.x=FJ&locale.x=en_FJ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("olly.bula@gmail.com");
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		driver.quit();
		
	}

}
