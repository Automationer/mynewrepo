package com.craigslist.test;

import com.craigslist.base.TestBase;
import com.craigslist.pages.CarsAndTrucks;
import com.craigslist.pages.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SmokeTest extends TestBase {

    LandingPage landingPage;
    CarsAndTrucks carsAndTrucks;

    public SmokeTest() {
        landingPage = new LandingPage();
        carsAndTrucks = new CarsAndTrucks();
    }

    @Test(groups = {"report_this"})
    public void websiteShouldLaunchSuccessfully() {
        String title = driver.getTitle();
        Assert.assertEquals(title
                , "craigslist: washington, DC jobs, apartments, for sale, services, community, and events");
    }

    @Test(groups = {"report_this"})
    public void languageDropdownOptionsDisplayed() {
        List<WebElement> allAvailableLanguages = landingPage.getAllAvailableLanguages();
        int count = allAvailableLanguages.size();
        landingPage.displayAvailableLanguages();
        lib.waitSecond(1);
        Assert.assertEquals(count, 15);
    }

    @Test(groups = {"report_this"})
    public void websiteShouldBeAbleToChangeToDifferentLanguages() {
        landingPage.chooseLanguage("russian");
        By carsAndTrucksInRussian = By.xpath("//span[text()='авто+груз.']");
        String actual = lib.explicitlyWaitUntilElementPresent(carsAndTrucksInRussian).getText();
        Assert.assertEquals(actual, "авто+груз.");
    }

    @Test
    public void appShouldBeAbleToChangeLanguageToTurkish() {
        landingPage.chooseLanguage("türkçe");
        By turkishText = By.xpath("//span[text()='bisiklet']");
        String actual = lib.explicitlyWaitUntilElementPresent(turkishText).getText();
        Assert.assertEquals(actual, "bisiklet");
    }

    @Test
    public void verifyCommunityLinks() {
        List<WebElement> hrefLinks = landingPage.getCommunityHrefLinks();
        String prevHref = "";
        for (WebElement href : hrefLinks) {
            String currentUrl = href.getAttribute("href");
            Assert.assertNotSame(prevHref, currentUrl);
            prevHref = currentUrl;
        }
    }

    @Test
    public void filterByAll() {
        landingPage.goTo("cars+trucks");
        carsAndTrucks.selectSearchGroupCategory("all");
        String placeholder = driver.findElement(carsAndTrucks.searchBox_locator).getAttribute("placeholder");
        Assert.assertEquals(placeholder, "search cars & trucks");
    }

    @Test
    public void filterByOwner() {
        landingPage.goTo("cars+trucks");
        carsAndTrucks.selectSearchGroupCategory("owner");
        String placeholder = driver.findElement(carsAndTrucks.searchBox_locator).getAttribute("placeholder");
        Assert.assertEquals(placeholder, "search cars & trucks - by owner");
    }

    @Test
    public void filterByDealer() {
        landingPage.goTo("cars+trucks");
        carsAndTrucks.selectSearchGroupCategory("dealer");
        String placeholder = driver.findElement(carsAndTrucks.searchBox_locator).getAttribute("placeholder");
        Assert.assertEquals(placeholder, "search cars & trucks - by dealer");
    }

    @Test
    public void filterByDealer1() {
        landingPage.goTo("cars+trucks");
        carsAndTrucks.selectSearchGroupCategory("dealer");
        String placeholder = driver.findElement(carsAndTrucks.searchBox_locator).getAttribute("placeholder");
        Assert.assertEquals(placeholder, "search cars & trucks - by dealer");
    }
}
