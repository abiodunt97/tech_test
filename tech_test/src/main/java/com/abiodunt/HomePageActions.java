package com.abiodunt;

import com.abiodunt.locators.HomePageLocators;
import com.abiodunt.utils.DriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.List;

public class HomePageActions extends PageActions {

    HomePageLocators homePageLocators;
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public HomePageActions() {
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(DriverContext.getDriver(), homePageLocators);
    }

    public void captureData(List<String> headerList, List<LinkedHashMap<String, String>> dataTable) {
        List<WebElement> headerItems = homePageLocators.tableHeader;
        for (WebElement headerItem : headerItems) {
            String headerName = headerItem.getText();
            headerList.add(headerName);
        }

        List<WebElement> rowList = homePageLocators.tableRows;
        for (int i = 1; i < rowList.size(); i++) {
            String rowLocator = "//table[@class='sc-f7a61dda-3 kCSmOD cmc-table  ']//tbody//tr[" + i + "]";
            List<WebElement> rowItems = DriverContext.getDriver().findElement(By.xpath(rowLocator))
                    .findElements(By.tagName("td"));

            LinkedHashMap<String, String> rowData = new LinkedHashMap<>();
            for (int j = 0; j < rowItems.size(); j++) {
                String cellValue = rowItems.get(j).getText();
                rowData.put(headerList.get(j), cellValue);
            }

            dataTable.add(rowData);
        }
        LOGGER.info("Data table: \n {} \n", dataTable);
    }

    public void showRows(String value) {
        LOGGER.info("Filtering table to show {} rows", value);
        homePageLocators.rowSortDropdown.click();
        DriverContext.getDriver().findElement(By.xpath("//*[text()='" + value + "']")).click();
        waitForPageLoad();
    }

    public void clickButton(String value) {
        LOGGER.info("Clicking button with text: {}", value);
        WebElement element = DriverContext.getDriver().findElement(By.xpath("//*[ text() = '" + value + "' ]"));
        JavascriptExecutor executor = (JavascriptExecutor) DriverContext.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void handleDropDown(String value) {
        LOGGER.info("Opening drop down with text: {}", value);
        WebElement element = DriverContext.getDriver().findElement(By.xpath("//*[ contains (text(), '" +  value + "' ) ]"));
        element.click();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverContext.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) DriverContext.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void handleToggle(String text, String labelId) {
        LOGGER.info("Toggling control with text: {}", text);
        DriverContext.getDriver().findElement(By.xpath("//button[contains(text(),'" + text + "')]"))
                .findElement(By.xpath("//label[@id='" + labelId + "']")).click();
    }

    public void handleButtonByText(String text) {
        LOGGER.info("Clicking button with text: {}", text);
        DriverContext.getDriver().findElement(By.xpath("//button[contains(text(),'" + text + "')]")).click();
    }

    public void handleInputByXPath(String locator, String value) {
        LOGGER.info("Entering value: {} into input field", value);
        DriverContext.getDriver().findElement(By.xpath(locator)).sendKeys(String.valueOf(value));
    }
}
