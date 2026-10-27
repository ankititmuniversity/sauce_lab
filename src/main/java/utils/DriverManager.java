package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    
	public static WebDriver getDriver() {
		return DRIVER.get();
	}
	public static void setDriver(WebDriver driver) {
		DRIVER.set(driver);
	}
	public static void quitDriver() {
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			try {
				driver.quit();
			} finally {
				DRIVER.remove();
			}
		}
	}
}
