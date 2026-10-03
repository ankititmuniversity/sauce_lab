package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(className = "title")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[contains(text(),'Sauce Labs Backpack')]")
	private WebElement inventoryItem;

	@FindBy(xpath = "//button[contains(@id,'sauce-labs-backpack')]")
	private WebElement addToCartBtn;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;

	public String getPageTitle() {
		return pageTitle.getText();
	}

	public String getInventoryItem(){
		return inventoryItem.getText();
	}
	public void addToCart() {
		addToCartBtn.click();
	} 

	public void clickOnCartBtn () {
		cartIcon.click();
	}
}
