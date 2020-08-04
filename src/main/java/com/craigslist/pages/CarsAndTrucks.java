package com.craigslist.pages;

import com.craigslist.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CarsAndTrucks extends TestBase {

    By searchGroupCategories_locators = By.cssSelector(".searchgroup.buttongroup.purveyor a");
    public By searchBox_locator = By.cssSelector("[name='query']");

    public void selectSearchGroupCategory(String categoryName) {
        WebElement allElement = driver.findElements(searchGroupCategories_locators).get(0);
        WebElement ownerElement = driver.findElements(searchGroupCategories_locators).get(1);
        WebElement dealerElement = driver.findElements(searchGroupCategories_locators).get(2);
        if (categoryName.equalsIgnoreCase("all"))
            lib.click(categoryName, allElement);
        if (categoryName.equalsIgnoreCase("owner"))
            lib.click(categoryName, ownerElement);
        if (categoryName.equalsIgnoreCase("dealer"))
            lib.click(categoryName, dealerElement);
        lib.waitUntilPageLoad();
    }

}
