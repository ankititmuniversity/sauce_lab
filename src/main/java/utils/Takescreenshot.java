package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Takescreenshot {	
	public static void takeShot(String dest,WebDriver driver) throws IOException {
		//WebDriver driver = DriverManager.getDriver();
		if (driver == null) {
			System.out.println("Driver is null, cannot capture screenshot!");
			return;
		}
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(dest));
	}
}
