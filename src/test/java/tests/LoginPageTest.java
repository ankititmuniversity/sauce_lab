package tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.DriverManager;

public class LoginPageTest extends BaseTest {
	LoginPage loginPage;
	@Test(dataProvider="credentials",groups="@Regression")
	public void testLogin(String username, String pwd) {
		loginPage = new LoginPage(DriverManager.getDriver());
		loginPage.enterUsername(username);
		loginPage.enterPassword(pwd);
		loginPage.clickLoginBtn();	
		
//		String errorMsg = loginPage.getErrorMessage();
//		if(errorMsg.equals("Epic sadface: Username and password do not match any user in this service")) {
//			assertTrue(false);
//		}
	}
	
	@DataProvider(name="credentials")
	public String[][] getData(){
		String [][] data = new String[1][2];
		data[0][0]="standard_user";
		data[0][1]="secret_sauce";		
		return data;		
	}
}
