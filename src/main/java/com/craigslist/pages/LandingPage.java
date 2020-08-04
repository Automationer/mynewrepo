package com.craigslist.pages;

import com.craigslist.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends TestBase {

    By languageDropDown_locator = By.cssSelector("select[name='lang']");
    By communityHrefLinks_locators = By.cssSelector("#ccc>div>ul a");
    By allLinks_locators = By.cssSelector("[href] span");

    public List<WebElement> getAllAvailableLanguages() {
        List<WebElement> languages = driver.findElement(languageDropDown_locator)
                .findElements(By.tagName("option"));
        return languages;
    }

    public void displayAvailableLanguages() {
        lib.click("language dropdown", languageDropDown_locator);
    }

    public void chooseLanguage(String l) {
        List<WebElement> allAvailableLanguages = getAllAvailableLanguages();
        for (WebElement language : allAvailableLanguages) {
            String languageText = language.getText();
            if (languageText.equalsIgnoreCase(l)) {
                lib.click("language: " + languageText, language);
                break;
            }
        }
    }

    public List<WebElement> getCommunityHrefLinks() {
        return driver.findElements(communityHrefLinks_locators);
    }

    public List<WebElement> getAllLinks() {
        return driver.findElements(allLinks_locators);
    }

    public void goTo(String linkName) {
        List<WebElement> allLinks = getAllLinks();
        for (WebElement link : allLinks) {
            String linkText = link.getText();
            if (linkText.contains(linkName)) {
                lib.click(linkName, link);
                break;
            }
        }

    }

}
