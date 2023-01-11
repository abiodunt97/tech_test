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
import java.math.BigInteger;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import static com.abiodunt.utils.DriverContext.TIMEOUT;

public abstract class PageActions {

    public static List<String> headerNames = new ArrayList<>();
    public static List<LinkedHashMap<String, String>> fullTableData = new ArrayList<>();

    public static List<String> filteredHeaderNames = new ArrayList<>();
    public static List<LinkedHashMap<String, String>> filteredTableData = new ArrayList<>();

    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance();

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

    public void rankComparator(int i, List<LinkedHashMap<String, String>> dataTable1,
                                    List<LinkedHashMap<String, String>> dataTable2) {
        LOGGER.info("The rank {} cryptocurrency in the unfiltered table is {} and the rank {} cryptocurrency" +
                        " in the filtered table is {} \n", i+1, dataTable1.get(i).get("Name"), i+1,
                dataTable2.get(i).get("Name"));
    }

    public void marketCapComparator(int i, List<LinkedHashMap<String, String>> dataTable1,
                                 List<LinkedHashMap<String, String>> dataTable2) {
        LOGGER.info("The difference in market capitalisation between {} and {} is {} \n",
                dataTable1.get(i).get("Name"), dataTable2.get(i).get("Name"),
                currencyFormat.format((new BigInteger(dataTable1.get(i).get("Market Cap")
                        .replaceAll("[^\\d.]+", "")))
                        .subtract(new BigInteger(dataTable2.get(i).get("Market Cap")
                                .replaceAll("[^\\d.]+", "")))));
    }

    public void supplyComparator(int i, List<LinkedHashMap<String, String>> dataTable1,
                                 List<LinkedHashMap<String, String>> dataTable2) {
        LOGGER.info("The difference in circulating supply between {} and {} is {} \n",
                dataTable1.get(i).get("Name"), dataTable2.get(i).get("Name"),
                numberFormat.format((new BigInteger(dataTable1.get(i).get("Circulating Supply")
                        .replaceAll("[^\\d.]+", "")))
                        .subtract(new BigInteger(dataTable2.get(i).get("Circulating Supply")
                                .replaceAll("[^\\d.]+", "")))));
    }

    public void volumeComparator(int i, List<LinkedHashMap<String, String>> dataTable1,
                                 List<LinkedHashMap<String, String>> dataTable2) {
        LOGGER.info("The difference in 24 hour volume between {} and {} is {} \n",
                dataTable1.get(i).get("Name"), dataTable2.get(i).get("Name"),
                currencyFormat.format((new BigInteger(dataTable1.get(i).get("Volume(24h)").split("\n")[0]
                        .replaceAll("[^\\d.]+", "")))
                        .subtract(new BigInteger(dataTable2.get(i).get("Volume(24h)").split("\n")[0]
                                .replaceAll("[^\\d.]+", "")))));
    }

}
