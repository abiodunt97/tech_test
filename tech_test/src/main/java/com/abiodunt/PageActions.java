package com.abiodunt;

import com.abiodunt.utils.DriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.abiodunt.utils.DriverContext.TIMEOUT;

public abstract class PageActions {

    public static List<String> headerNames = new ArrayList<>();
    public static List<LinkedHashMap<String, String>> fullTableData = new ArrayList<>();

    public static List<String> filteredHeaderNames = new ArrayList<>();
    public static List<LinkedHashMap<String, String>> filteredTableData = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void waitForElementPresence(String locator) {
        try {
            new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        } catch (Exception e) {
            LOGGER.error("Element with XPath: {} not present.", locator);
        }
    }

    public void waitForClickableElement(String locator) {
        try {
            new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        } catch (Exception e) {
            LOGGER.error("Element with XPath: {} not clickable.", locator);
        }
    }

    public void waitForPageLoad() {
        try {
            new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(TIMEOUT)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            LOGGER.error("Page did not load before timeout.");
        }
    }

}
