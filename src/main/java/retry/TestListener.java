package retry;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import utils.DriverManager;
import utils.ExtentReportsManager;
import utils.ExtentTestManager;
import utils.Takescreenshot;

public class TestListener implements ITestListener {
	public void onStart(ITestContext context) {
		ExtentReportsManager.getInstance();
	}
	public void onFinish(ITestContext context) {
		ExtentReportsManager.getInstance().flush();
	}
	public void onTestStart(ITestResult result) {
		ExtentTest test = ExtentReportsManager.getInstance().createTest(result.getMethod().getMethodName());
		ExtentTestManager.setExtentTest(test);
	}
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getExtentTest().pass("Name of Test Passed successfully is : "+result.getMethod().getMethodName());
		ExtentTestManager.removeExtentTest();
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = DriverManager.getDriver();
		String time_stamp =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		ExtentTestManager.getExtentTest().fail(result.getThrowable());
		String dest = System.getProperty("user.dir")+"/screenshots/"+result.getMethod().getMethodName()+"_"+time_stamp+".png";
		try {
			Takescreenshot.takeShot(dest,driver);
			ExtentTestManager.getExtentTest().addScreenCaptureFromPath(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ExtentTestManager.removeExtentTest();
	}
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getExtentTest().skip("Test skipped: " + result.getMethod().getMethodName());
		ExtentTestManager.removeExtentTest();
	}

}
