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

	@Parameters({"browser","headless","execution"})
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser,@Optional("false") String headless,@Optional("local") String execution) throws MalformedURLException {
		logger.info("Driver setup started");

		WebDriver driver = factory.createDriver(browser,Boolean.parseBoolean(headless),execution);
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
