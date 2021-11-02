import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class TestSample {
	
	@Test
	public void testLogin() throws MalformedURLException {
		
		FirefoxOptions chromeOptions = new FirefoxOptions();
	    DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability(CapabilityType.BROWSER_NAME,"firefox");
	    caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, chromeOptions);
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.8.101:4444/"), caps);
		driver.get("https://www.paypal.com/fj/signin?country.x=FJ&locale.x=en_FJ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("olly.bula@gmail.com");
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		
	}

}
