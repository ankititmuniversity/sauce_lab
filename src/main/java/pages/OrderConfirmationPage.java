package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class OrderConfirmationPage extends BasePage{

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[contains(text(),'Checkout: Complete!')]")
	private WebElement orderPageTitle;

	@FindBy(xpath="//h2[contains(text(),'Thank you for your order!')]")
	private WebElement welcomeMsg; 

	@FindBy (id = "back-to-products")
	private WebElement backToHome;

	@FindBy (id ="generate-pdf-order")
	private WebElement generatePdfBtn;

	public String getTitle() {
		return orderPageTitle.getText();
	}
	public String getWelcomeMsg() {
		return welcomeMsg.getText();
	}
	public void clickBackBtn() {
		backToHome.click();
	}
	public void clickGenerateBill() {
		generatePdfBtn.click();
	}
}
