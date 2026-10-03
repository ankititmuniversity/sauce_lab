package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager {
	private static ExtentReports extentReports;

	public static ExtentReports getInstance() {
		if(extentReports == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/ExtentReport.html");
			reporter.config().setDocumentTitle("Test Results");
			reporter.config().setReportName("Sauce Lab Report");
			
			extentReports = new ExtentReports();
			extentReports.attachReporter(reporter);
			extentReports.setSystemInfo("Tester", "Your Name");
			extentReports.setSystemInfo("Environment", "QA");
			extentReports.setSystemInfo("Browser", "Chrome");
		}
		return extentReports;
	}
}
