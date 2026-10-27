package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.DDT;
import utils.DriverManager;

public class CartPageTest extends BaseTest {
	
	LoginPage loginPage;	
	ProductPage productPage;
	CartPage cartPage;
	@Test(dataProvider="commonData", dataProviderClass=DDT.class)
	public void testCart(String username, String pwd) {
		loginPage = new LoginPage(DriverManager.getDriver());
		loginPage.enterUsername(username);
		loginPage.enterPassword(pwd);
		loginPage.clickLoginBtn();  

		productPage = new ProductPage(DriverManager.getDriver());
		String expectedTitle = "Products";
		String actualTitle = productPage.getPageTitle();
		Assert.assertEquals(expectedTitle, actualTitle);

		String expectedItemName = "Sauce Labs Backpack";
		String itemName = productPage.getInventoryItem();
		Assert.assertEquals(expectedItemName, itemName); 
		
		productPage.addToCart();
		productPage.clickOnCartBtn();
		
		cartPage = new CartPage(DriverManager.getDriver());
		String expectedCartPageTitle = "Your Cart";
		String actualCartPageTitle = cartPage.getCartTitle();
		Assert.assertEquals(expectedCartPageTitle, actualCartPageTitle);
		
		String itemAddedInCart = cartPage.checkCartItem();
		Assert.assertEquals("Sauce Labs Backpack", itemAddedInCart);
		
		cartPage.clickCheckoutBtn();
	}
}
