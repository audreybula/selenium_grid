import java.net.MalformedURLException;
import java.net.URL;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParallelThreadLocal {

	public ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public static WebDriver driver;
	public ThreadLocal<DesiredCapabilities> capab = new ThreadLocal<DesiredCapabilities>();
	public static DesiredCapabilities caps;

	public WebDriver getDriver() {

		return dr.get();

	}

	public void setWebDriver(WebDriver driver) {

		dr.set(driver);

	}

	public DesiredCapabilities getDesiredCap() {

		return capab.get();

	}

	public void setDesiredCap(DesiredCapabilities dc) {

		capab.set(dc);

	}

	@Test(dataProvider = "getData")
	public void testLogin(String username, String browser) throws MalformedURLException {

		if (browser.equals("firefox")) {
			
			caps = new DesiredCapabilities();
			setDesiredCap(caps);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			getDesiredCap().setCapability(CapabilityType.BROWSER_NAME, "firefox");
			getDesiredCap().setPlatform(Platform.ANY);
			firefoxOptions.merge(getDesiredCap());

		} else if (browser.equals("safari")) {

			caps = new DesiredCapabilities();
			setDesiredCap(caps);
			
			SafariOptions safariOptions = new SafariOptions();
			getDesiredCap().setCapability(CapabilityType.BROWSER_NAME, "safari");
			getDesiredCap().setPlatform(Platform.MAC);
			safariOptions.merge(getDesiredCap());

		} else if (browser.equals("chrome")) {

			caps = new DesiredCapabilities();
			setDesiredCap(caps);
			
			ChromeOptions chromeOptions = new ChromeOptions();
			getDesiredCap().setCapability(CapabilityType.BROWSER_NAME, "chrome");
			getDesiredCap().setPlatform(Platform.ANY);
			chromeOptions.merge(getDesiredCap());

		} else if (browser.equals("opera")) {

			caps = new DesiredCapabilities();
			setDesiredCap(caps);
			
			OperaOptions operaOptions = new OperaOptions();
			caps.setCapability(CapabilityType.BROWSER_NAME, "opera");
			caps.setPlatform(Platform.ANY);
			operaOptions.merge(getDesiredCap());

		}

		driver = new RemoteWebDriver(new URL("http://192.168.8.101:4444/"), caps);
		setWebDriver(driver);
		getDriver().get("https://www.paypal.com/fj/signin?country.x=FJ&locale.x=en_FJ");
		getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		getDriver().findElement(By.xpath("//button[normalize-space()='Next']")).click();
		getDriver().quit();

	}

	@DataProvider
	public Object[][] getData() {

		Object[][] data = new Object[4][2];

		// first row
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
