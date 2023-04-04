package com.seleniumspringboot.utils;

import com.seleniumspringboot.logs.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class Screenshot {

    public static String takeSnap(WebDriver driver, String ssname) {

        File sourceSS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        String time = date.toString().replace(" ", "-").replace(":", "_");
        String filepath = "./screenshots/" + ssname + "_" + time + ".png";
        try {
            FileUtils.copyFile(sourceSS, new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            Log.fatal("Error taking screenshot: " + e.getMessage());
        }
        return filepath;
    }

    public static void takeSnap(WebDriver driver) {

        File sourceSS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        String time = date.toString().replace(" ", "-").replace(":", "_");

        String filepath = "./screenshots/" + "_" + time + ".png";

        try {
            FileUtils.copyFile(sourceSS, new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error taking Screenshot: " + e.getMessage());
        }
    }
}
