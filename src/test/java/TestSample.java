import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSample {
	
	public static DesiredCapabilities caps = new DesiredCapabilities();
	
	@Test(dataProvider = "getData")
	public void testLogin(String username, String browser) throws MalformedURLException {
		
		if(browser.equals("firefox")) {
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
		    caps.setCapability(CapabilityType.BROWSER_NAME,"firefox");
		    caps.setPlatform(Platform.ANY);
		    caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
			
		} else if(browser.equals("safari")) {
			
			SafariOptions safariOptions = new SafariOptions();
		    caps.setCapability(CapabilityType.BROWSER_NAME,"safari");
		    caps.setPlatform(Platform.MAC);
		    caps.setCapability("something", safariOptions);
			
		} else if(browser.equals("chrome")) {
			
			ChromeOptions chromeOptions = new ChromeOptions();
		    caps.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		    caps.setPlatform(Platform.ANY);
		    caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			
		} else if(browser.equals("opera")) {
			
			OperaOptions operaOptions = new OperaOptions();
		    caps.setCapability(CapabilityType.BROWSER_NAME,"opera");
		    caps.setPlatform(Platform.ANY);
		    caps.setCapability(OperaOptions.CAPABILITY, operaOptions);
			
		}
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.8.101:4444/"), caps);
		driver.get("https://www.paypal.com/fj/signin?country.x=FJ&locale.x=en_FJ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData() {
		
		Object[][] data = new Object[4][2];
		
		//first row
		data[0][0] = "olly.bula@gmail.com";
		data[0][1] = "firefox";
		
		// second row
		data[1][0] = "tflrunningsheet@gmail.com";
		data[1][1] = "chrome";

		// third row
		data[2][0] = "teddy.bula@gmail.com";
		data[2][1] = "opera";

		// fourth row
		data[3][0] = "oliver.bula@gmail.com";
		data[3][1] = "safari";
		
		return data;
		
	}

}
