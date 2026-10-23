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
		
//		data[1][0]="locked_out_user";
//		data[1][1]="secret_sauce";	
		
//		data[2][0]="problem_user";
//		data[2][1]="secret_sauce";	
//		
//		data[3][0]="performance_glitch_user";
//		data[3][1]="secret_sauce";	
//		
//		data[4][0]="error_user";
//		data[4][1]="secret_sauce";	
//		
//		data[5][0]="visual_user";
//		data[5][1]="secret_sauce"; 
		
		return data;		
	}
}
