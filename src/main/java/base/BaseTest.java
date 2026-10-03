package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest extends DriverManager {
	public static Logger logger = LogManager.getLogger(BaseTest.class);
	
	@BeforeMethod
	public void setUp() {
		logger.info("Driver initial set up Started");
		WebDriverManager.chromedriver().setup();
		
		// Disable password manager and save prompts
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");        
        
		DriverManager.setDriver(new ChromeDriver(options));
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
