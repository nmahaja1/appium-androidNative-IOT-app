package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import tests.KenmoreOvenProductTest;

@Slf4j
public class TestUtility implements ITestListener {

    String destDir;
    DateFormat dateFormat;

    // Function to capture screenshot.
    public void captureScreenShot(ITestResult result, String status) {
        String failureMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
        log.error(failureMethod);
        takeScreenShot(failureMethod);
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        captureScreenShot(iTestResult, "fail");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public void takeScreenShot(String failureMethod) {
        System.out.println("Taking Screenshot");
        // Set folder name to store screenshots.
        destDir = "screenshots";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) KenmoreOvenProductTest.driver).getScreenshotAs(OutputType.FILE);
        // Set date format to set It as screenshot file name.
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name using current date time.
        String destFile = failureMethod + "_" + dateFormat.format(new Date()) + ".png";
        log.error("dest file name :: " + destFile);
        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
