package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id="user-name")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login-button")
	private WebElement loginBtn;
	@FindBy (xpath = "//div[contains(@class,'error-message')]")
	private WebElement errorMsg;
	

	public void enterUsername(String user) {
		username.sendKeys(user);
	}
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void clickLoginBtn() {
		loginBtn.click();
	}
	public String getErrorMessage() {
		return errorMsg.getText();
	}
}
