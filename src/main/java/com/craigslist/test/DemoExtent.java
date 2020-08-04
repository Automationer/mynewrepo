package com.craigslist.test;


import com.aventstack.extentreports.Status;
import com.craigslist.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoExtent extends TestBase {


    @Test
    public void amazonLandingPageLoad() {
        logger = extent.createTest("Amazon Landing Page Load");
        driver.get("https://www.amazon.com");
        logger.info("user goes to amazon landing page");
        String expected = "1Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
        logger.log(Status.PASS, "verify the landing page is loaded successfully");
    }

/*    @Test
    public void facebookLandingPageLoad() {
        logger = extent.createTest("Facebook Landing Page Load");
        driver.get("https://www.facebook.com");
        logger.info("user goes to amazon landing page");
        String expected = "Facebook - Log In or Sign Up";
        String actual = driver.getTitle();
        logger.info("verify the landing page is loaded successfully");
        Assert.assertEquals(actual, expected);
    }*/

}
