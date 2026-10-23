package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverManager;

/**
 * BaseTest only manages the test lifecycle.
 * HOW the browser is created (local or remote) lives in DriverFactory.
 */
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Driver set up started");

        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);

        logger.info("Thread ID: {} running {}", Thread.currentThread().getId(),
                this.getClass().getSimpleName());

        driver.get(ConfigReader.getProperty("url"));
        logger.info("Driver set up completed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Browser closed successfully");
    }
}