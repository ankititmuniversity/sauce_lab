package utils;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver createDriver(String browser, boolean headless, String execution);
}
