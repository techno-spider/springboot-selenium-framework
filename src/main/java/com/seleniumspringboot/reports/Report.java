package com.seleniumspringboot.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Report {
    public static ExtentReports generateExtentReports() {

        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\extentReport\\extentreport.html");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFile);

        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("Springboot Selenium Framework Report");
        extentSparkReporter.config().setDocumentTitle("Springboot Selenium execution Report");
        extentSparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("Browser Name", "Chrome");
        extentReports.setSystemInfo("URL", "https://automation-realm.netlify.app/");

        return extentReports;
    }
}
