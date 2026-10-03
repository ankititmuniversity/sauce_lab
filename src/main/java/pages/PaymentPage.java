package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class PaymentPage extends BasePage{
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[contains(text(),'Checkout: Overview')]")
	private WebElement title;

	@FindBy(xpath="//div[@data-test='payment-info-value']")
	private WebElement paymentInfo ;

	@FindBy(xpath="//div[@data-test='shipping-info-value']")
	private WebElement shippingInfo;

	@FindBy(xpath="//div[@data-test='subtotal-label']")
	private WebElement subTotal ;

	@FindBy(xpath="//div[@data-test='total-label']")
	private WebElement total ;

	@FindBy(id="cancel")
	private WebElement cancelBtn;

	@FindBy(id="finish")
	private WebElement finishBtn;

	public String getTitle() {
		return title.getText();
	}

	public String getPaymentInfo() {
		return paymentInfo.getText();
	}

	public String getShippingInfo() {
		return shippingInfo.getText();
	}

	public String getsubTotalInfo() {
		return subTotal.getText();
	}

	public String getTotalInfo() {
		return total.getText();
	}

	public void clickFinishBtn() {
		finishBtn.click();
	}

	public void clickCancelBtn() {
		cancelBtn.click();
	}

}
