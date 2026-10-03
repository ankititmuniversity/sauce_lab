package base;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait eWait;
	protected static Wait<WebDriver> fWait;
	protected static Actions actions;
	
	public BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.eWait = new WebDriverWait(driver,Duration.ofSeconds(5));
		this.fWait = new FluentWait<WebDriver>(driver)
						 .withTimeout(Duration.ofSeconds(30))
						 .pollingEvery(Duration.ofSeconds(5))
						 .ignoring(NoSuchElementException.class);
		actions = new Actions(driver);
	}
	
	public static void click(WebElement ele) {
		eWait.until(ExpectedConditions.visibilityOf(ele)).click();
	}
	
	public static void sendValue(WebElement ele,String val) {
		eWait.until(ExpectedConditions.visibilityOf(ele)).sendKeys(val);
	}
	public static String getValue(WebElement ele) {
		return eWait.until(ExpectedConditions.visibilityOf(ele)).getText();
	}
	
	
}
