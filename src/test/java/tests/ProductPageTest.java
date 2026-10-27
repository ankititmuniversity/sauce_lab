package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.DDT;
import utils.DriverManager;

public class ProductPageTest extends BaseTest {
	LoginPage loginPage;	
	ProductPage productPage;
	@Test(dataProvider="commonData", dataProviderClass=DDT.class)
	public void testProducts(String username, String pwd) {
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
	}
}
