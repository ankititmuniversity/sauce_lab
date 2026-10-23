package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class DriverFactory {

    private DriverFactory() {} // prevent instantiation

    public static WebDriver createDriver() {
        // Read execution mode: local or remote (default = local)
        String mode = System.getProperty("execution", "local").toLowerCase();

        // Read headless flag (default = false)
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Build Chrome options
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }

        // Decide local vs remote
        if (mode.equals("remote")) {
            String gridUrl = System.getProperty("grid.url", "http://localhost:4444");
            try {
                return new RemoteWebDriver(new URL(gridUrl), options);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
            }
        } else {
            return new ChromeDriver(options);
        }
    }
}
//mvn test -Dbrowser=chrome -Dexecution=local
//mvn test -Dbrowser=chrome -Dexecution=remote -Dgrid.url=http://localhost:4444
