package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactoryImpl implements IDriverFactory {

	@Override
	public WebDriver createDriver(String browser, boolean headless, String execution) {
		String gridUrl = System.getProperty("grid.url", "http://localhost:4444");

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			if (headless) co.addArguments("--headless=new", "--window-size=1920,1080");
			try {
				return "remote".equalsIgnoreCase(execution)
						? new RemoteWebDriver(new URL(gridUrl), co)
								: new ChromeDriver(co);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

		case "firefox":
			FirefoxOptions fo = new FirefoxOptions();
			if (headless) fo.addArguments("-headless");
			try {
				return "remote".equalsIgnoreCase(execution)
						? new RemoteWebDriver(new URL(gridUrl), fo)
								: new FirefoxDriver(fo);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		case "edge":
			EdgeOptions eo = new EdgeOptions();
			if (headless) eo.addArguments("--headless=new", "--window-size=1920,1080");
			try {
				return "remote".equalsIgnoreCase(execution)
						? new RemoteWebDriver(new URL(gridUrl), eo)
								: new EdgeDriver(eo);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
}
