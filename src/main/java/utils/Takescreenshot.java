package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Attachment;

public class Takescreenshot {   

    // For ExtentReports (file-based)
    public static void takeShot(String dest, WebDriver driver) throws IOException {
        if (driver == null) {
            System.out.println("Driver is null, cannot capture screenshot!");
            return;
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(dest));
    }

    // For Allure (byte array-based)
    @Attachment(value = "Failure Screenshot", type = "image/jpeg")
    public static byte[] takeShotAsBytes(WebDriver driver) {
        if (driver == null) {
            System.out.println("Driver is null, cannot capture screenshot!");
            return new byte[0];
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
