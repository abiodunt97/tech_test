package com.abiodunt.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageLocators {
    @FindBy(css = "div[class='sc-aef7b723-0 sc-dae82938-0 coScOT']")
    public WebElement rowSortDropdown;
    @FindBy(xpath = "//table[@class='sc-f7a61dda-3 kCSmOD cmc-table  ']//tr//th")
    public List<WebElement> tableHeader;
    @FindBy(xpath = "//table[@class='sc-f7a61dda-3 kCSmOD cmc-table  ']//tr")
    public List<WebElement> tableRows;

    public String algorithmButtonLocator = "//*[ contains (text(), 'Algorithm' ) ]";
    public String powFilterLocator = "//*[ text() = 'PoW' ]";
    public String addFilterLocator = "//*[ text() = 'Add Filter' ]";
    public String mineableToggleLocator = "//button[contains(text(),'Mineable')]";
    public String allCryptocurrenciesLocator = "//button[contains(text(),'All Cryptocurrencies')]";
    public String coinsLocator = "//button[contains(text(),'Coins')]";
    public String priceLocator = "//button[contains(text(),'Price')]";
    public String minRangeInputLocator = "//input[@data-qa-id='range-filter-input-max']";
    public String maxRangeInputLocator = "//input[@data-qa-id='range-filter-input-max']";
    public String applyFilterLocator = "//button[contains(text(),'Apply Filter')]";
    public String showResultsLocator = "//button[contains(text(),'Show results')]";
}
