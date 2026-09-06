package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot( WebDriver driver,String testName) {

    String timestamp = new SimpleDateFormat( "yyyyMMdd_HHmmss").format(new Date());

        String filePath ="test-output/screenshots/"+testName+"_"+timestamp+".jpg";

        try {
            TakesScreenshot screenshot =(TakesScreenshot) driver;

            File source =screenshot.getScreenshotAs(OutputType.FILE);

            File destination =new File(filePath);

            FileUtils.copyFile(source,destination );

            return destination.getAbsolutePath();
            } 
        catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}
