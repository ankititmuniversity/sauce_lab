package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class CartPage extends BasePage {
	 public CartPage(WebDriver driver) {
	        super(driver);
	    }

    @FindBy(xpath = "//span[contains(text(),'Your Cart')]")
    private WebElement cartTitle;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Backpack')]")
    private WebElement productName;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    
    @FindBy (xpath = "//button[contains(text(),'Remove')]")
    private WebElement removeItemFromCart;
    
    public String getCartTitle() {
    	return cartTitle.getText();
    }    
    public String checkCartItem() {
    	return productName.getText();
    }
    
    public void clickCheckoutBtn() {
    	checkoutButton.click();
    }
    
    public void clickContinueShoppingBtn() {
    	continueShoppingButton.click();
    }
    
    public void clickRemoveItemBtn() {
    	removeItemFromCart.click();
    }  
    
}
