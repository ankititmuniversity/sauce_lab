package base;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverFactoryImpl;
import utils.DriverManager;

public class BaseTest {

	protected static final Logger logger = LogManager.getLogger(BaseTest.class);
	private final DriverFactoryImpl factory = new DriverFactoryImpl();

	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws MalformedURLException {
		logger.info("Driver setup started");
		String browser = System.getProperty("browser","chrome").toLowerCase();
		boolean isHeadless  = Boolean.parseBoolean(System.getProperty("headless","false"));
		String execution = System.getProperty("execution","local").toLowerCase();
		
		WebDriver driver = factory.createDriver(browser,isHeadless,execution);
		DriverManager.setDriver(driver);

		logger.info("Thread ID: {} running {}", Thread.currentThread().getId(),this.getClass().getSimpleName());
		DriverManager.getDriver().manage().window().maximize(); 
		driver.get(ConfigReader.getProperty("url"));
		logger.info("Driver setup completed");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
		logger.info("Browser closed successfully");
	}
}
