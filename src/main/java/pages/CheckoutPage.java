package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class CheckoutPage extends BasePage{
		
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'Checkout: Your Information')]")
	private WebElement title;
	
	@FindBy (id = "first-name")
	private WebElement enterFirstName;
	
	@FindBy (id = "last-name")
	private WebElement enterLastName;
	
	@FindBy (id = "postal-code")
	private WebElement enterPostalCode;
	
	@FindBy (id ="cancel")
	private WebElement clickCancelBtn;
	
	@FindBy (id ="continue")
	private WebElement clickContinueBtn;
	
	public String getTitle() {
		return title.getText();
	}
	
	public void enterfName(String fname) {
		enterFirstName.sendKeys(fname);
	}
	public void enterlName(String lname) {
		enterLastName.sendKeys(lname);
	}
	public void enterPostalCode(String postCode) {
		enterPostalCode.sendKeys(postCode);
	}
	public void clickCancelBtn() {
		clickCancelBtn.click();
	}
	public void clickContinueBtn() {
		clickContinueBtn.click();
	}
}
