package com.craigslist.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;

public class Library {

    private final WebDriver driver;

    public Library(WebDriver driver) {
        this.driver = driver;
    }

    public void enter(String name, By by, String key) {
        driver.findElement(by).sendKeys(key);
        System.out.println("sending '" + key + "' to " + name);
    }

    public void click(String name, By by) {
        driver.findElement(by).click();
        System.out.println("clicked: " + name);
    }

    public void click(String name, WebElement element) {
        element.click();
        System.out.println("clicked: " + name);
    }

    public void select(By by, String name, String way, String selection) {
        WebElement selectFrom = driver.findElement(by);
        Select select = new Select(selectFrom);
        if (way.equals("byValue")) {
            select.selectByValue(selection);
            System.out.println(name + " selected '" + selection + "' by " + way);
        } else if (way.equals("byIndex")) {
            select.selectByIndex(Integer.parseInt(selection));
            System.out.println(name + " selected '" + selection + "' by " + way);
        } else if (way.equals("byVisibleText")) {
            select.selectByVisibleText(selection);
            System.out.println(name + " selected '" + selection + "' by " + way);
        } else {
            System.out.println("please provide a valid selector from one of the below options:");
            System.out.println("1) byValue\t2) byIndex\t3) byVisibleText");
        }
    }

    public void waitSecond(int amount) {
        try {
            Thread.sleep(amount * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitMilli(int amount) {
        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollThePage(int x, int y) {
        waitSecond(1);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(" + x + "," + y + ")");
        waitSecond(1);
    }

    public void moveToElement(WebElement element) {
        waitSecond(2);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        waitSecond(2);
    }

    public void scrollIntoView(By by) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        //    waitSecond(1);
    }

    public void highlight(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 1; i < 3; i++) {
            js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid red;');", ele);
            waitMilli(500);
            js.executeScript("arguments[0].setAttribute('style');", ele);
            waitMilli(500);
        }

    }

    public WebElement explicitlyWaitUntilVisible(By by) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    public WebElement explicitlyWaitUntilElementPresent(By by) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }


    public WebElement explicitlyWaitUntilClickable(By by) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public void waitUntilAlertIsPresent() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
    }

    public void switchTab(int index) {
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(index));
    }

    public void waitUntilPageLoad() {
        new WebDriverWait(driver, 7).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
