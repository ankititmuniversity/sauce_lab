package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest extends DriverManager {
	public static Logger logger = LogManager.getLogger(BaseTest.class);
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws MalformedURLException {
		logger.info("Driver initial set up Started");
		//WebDriverManager.chromedriver().setup();
		MutableCapabilities options;
	    switch (browser.toLowerCase()) {
	        case "chrome":
	            options = new ChromeOptions();
	            break;
	        case "firefox":
	            options = new FirefoxOptions();
	            break;
	        case "edge":
	            options = new EdgeOptions();
	            break;
	        default:
	            throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }
		
		// Disable password manager and save prompts
//        options.addArguments("--disable-notifications");
//        options.addArguments("--headless=new");
//        options.addArguments("--incognito");        
        
		//DriverManager.setDriver(new ChromeDriver(options));
		DriverManager.setDriver(new RemoteWebDriver(new URL("http://localhost:4444"),options));
		System.out.println("Thread ID: " + Thread.currentThread().getId() + " running " + this.getClass().getSimpleName());
		DriverManager.getDriver().get(ConfigReader.getProperty("url"));
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		logger.info("Driver initial set up Completed");
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.getDriver().quit();
		logger.info("All opened windows closed successfully");
	}
	
}
