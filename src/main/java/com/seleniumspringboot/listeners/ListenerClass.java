package com.seleniumspringboot.listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumspringboot.logs.Log;
import com.seleniumspringboot.reports.Report;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    ExtentReports extentReports = Report.generateExtentReports();
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {

        ITestListener.super.onTestStart(result);

        String testName = result.getName();
        extentTest = extentReports.createTest(testName);
        Log.info("****************************************************" + testName + "****************************************************");
        Log.info("Execution of Test Started..");
        extentTest.log(Status.INFO, testName + " started executing..");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ITestListener.super.onTestSuccess(result);

        String testName = result.getName();
        extentTest.log(Status.PASS, testName + " got successfully executed and passed.");
        Log.info("*******************************************" + testName + "*******************************************");
        Log.info("Execution of Tests Started..");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ITestListener.super.onTestFailure(result);

        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName + " has failed.");
        Log.fatal("Test Execution has failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ITestListener.super.onTestSkipped(result);

        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + " got failed.");
        Log.info("*******************************************" + testName + "*******************************************");
        Log.warn("Test skipped.");
    }

    @Override
    public void onStart(ITestContext context) {

        ITestListener.super.onStart(context);

        Log.info("Execution of Test Suite Started..");
    }

    @Override
    public void onFinish(ITestContext context) {

        ITestListener.super.onFinish(context);

        Log.info("Execution of Test Suite finished.");
        extentReports.flush();
    }
}
