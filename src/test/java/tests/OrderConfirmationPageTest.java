package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.PaymentPage;
import pages.ProductPage;
import utils.DDT;
import utils.DriverManager;

public class OrderConfirmationPageTest extends BaseTest {
	LoginPage loginPage;	
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	PaymentPage paymentPage;
	OrderConfirmationPage orderConfirmationPage;
	@Test(dataProvider="commonData", dataProviderClass=DDT.class)
	public void testOrderConfirmation(String username, String pwd) throws InterruptedException {
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

		checkoutPage = new CheckoutPage(DriverManager.getDriver());		
		String expectedCheckoutPageTitle = "Checkout: Your Information";
		String actualCheckoutPageTitle = checkoutPage.getTitle();
		Assert.assertEquals(expectedCheckoutPageTitle, actualCheckoutPageTitle);

		checkoutPage.enterfName("Ankit");
		checkoutPage.enterlName("Kumar");
		checkoutPage.enterPostalCode("123456");
		checkoutPage.clickContinueBtn();
		
		//Checkout: Overview        
		paymentPage = new PaymentPage(DriverManager.getDriver());
		String expectedPyementPageTitle = "Checkout: Overview";
		String actualPyementPageTitle = paymentPage.getTitle();
		Assert.assertEquals(expectedPyementPageTitle, actualPyementPageTitle);
		
		String payment = paymentPage.getPaymentInfo();
		Assert.assertEquals(payment, "SauceCard #31337");
		
		String shippingChannel = paymentPage.getShippingInfo();
		Assert.assertEquals(shippingChannel, "Free Pony Express Delivery!");
		
		String subTotal = paymentPage.getsubTotalInfo();
		Assert.assertEquals(subTotal, "Item total: $29.99");
		
		String total = paymentPage.getTotalInfo();
		Assert.assertEquals(total, "Total: $32.39");
	
		paymentPage.clickFinishBtn();	
		
		orderConfirmationPage = new OrderConfirmationPage(DriverManager.getDriver());
		
		String expectedOrderPageTitle = "Checkout: Complete!";
		String actualOrderPageTitle = orderConfirmationPage.getTitle();
		Assert.assertEquals(expectedOrderPageTitle, actualOrderPageTitle);
		
		String welcomeMsg = orderConfirmationPage.getWelcomeMsg();
		Assert.assertEquals(welcomeMsg, "Thank you for your order!");
		
		orderConfirmationPage.clickGenerateBill();
		

	}
}
