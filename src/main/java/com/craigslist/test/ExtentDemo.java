package com.craigslist.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExtentDemo {

    @Test
    public void test1() throws IOException {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/extent.html");
        reporter.config().setTheme(Theme.DARK);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        ExtentTest logger = extent.createTest("Login Test 1");
        logger.log(Status.INFO, "Login to craigslist");
        logger.log(Status.PASS, "Title verified");
        logger
                .fail("Test Failed due to wait", MediaEntityBuilder
                        .createScreenCaptureFromPath("FailedTestsScreenshots/image.png")
                        .build());
        System.out.println("login to craigslist");

    }


}
