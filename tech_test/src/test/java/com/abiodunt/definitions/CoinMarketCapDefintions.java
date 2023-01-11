package com.abiodunt.definitions;

import com.abiodunt.HomePageActions;
import com.abiodunt.locators.HomePageLocators;
import com.abiodunt.utils.DriverContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;

import static com.abiodunt.PageActions.*;

public class CoinMarketCapDefintions {

    HomePageActions homePageActions = new HomePageActions();
    HomePageLocators homePageLocators = new HomePageLocators();
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Given("User is on CoinMarketCap home page")
    public void navigateToCoinMarketCap() {
        DriverContext.navigateTo("https://coinmarketcap.com");
    }

    @When("User sorts rows by {int}")
    public void sortRowsby20(int value) {
        homePageActions.showRows(String.valueOf(value));
        homePageActions.waitForPageLoad();
    }

    @And("The relevant cryptocurrency data on the page has been captured")
    public void captureTableData() {
        homePageActions.scrollToBottom();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.error("Exception thrown during thread sleep.");
        }

        homePageActions.scrollToTop();
        homePageActions.captureData(headerNames, fullTableData);
        LOGGER.info("{} cryptocurrencies have been listed", fullTableData.size());

        LOGGER.info("The top {} cryptocurrencies are: ", fullTableData.size());
        for (LinkedHashMap<String, String> fullTableDatum : fullTableData) {
            LOGGER.info("Rank: {}", fullTableDatum.get("#"));
            LOGGER.info("Name: {}", fullTableDatum.get("Name"));
            LOGGER.info("Price: {}", fullTableDatum.get("Price"));
            LOGGER.info("Market Capitalization: {}", fullTableDatum.get("Market Cap"));
            LOGGER.info("Circulating Supply: {}", fullTableDatum.get("Circulating Supply"));
            LOGGER.info("24h Volume: {}", fullTableDatum.get("Volume(24h)"));
            LOGGER.info("-----");
        }
    }

    @Then("Filter by Algorithm - PoW")
    public void filterByAlgorithmPoW() {
        homePageActions.clickButton("Filters");

        homePageActions.waitForElementPresence(homePageLocators.algorithmButtonLocator);
        homePageActions.handleDropDown("Algorithm");

        homePageActions.waitForElementPresence(homePageLocators.powFilterLocator);
        homePageActions.clickButton("PoW");
    }

    @And("Filter by Mineable")
    public void filterByMineable() throws InterruptedException {
        homePageActions.waitForElementPresence(homePageLocators.addFilterLocator);
        homePageActions.clickButton("Add Filter");

        homePageActions.waitForElementPresence(homePageLocators.mineableToggleLocator);
        homePageActions.handleToggle("Mineable", "mineable");
    }

    @And("Filter by Coins")
    public void filterByCoins() {
        homePageActions.waitForClickableElement(homePageLocators.allCryptocurrenciesLocator);
        homePageActions.handleButtonByText("All Cryptocurrencies");

        homePageActions.waitForClickableElement(homePageLocators.coinsLocator);
        homePageActions.handleButtonByText("Coins");
    }

    @And("Set price range to {int} - {int}")
    public void setPriceRangeTo(int min, int max) {
        homePageActions.waitForClickableElement(homePageLocators.priceLocator);
        homePageActions.handleButtonByText("Price");

        homePageActions.waitForElementPresence(homePageLocators.minRangeInputLocator);
        homePageActions.handleInputByXPath("//input[@data-qa-id='range-filter-input-min']", String.valueOf(min));

        homePageActions.waitForElementPresence(homePageLocators.maxRangeInputLocator);
        homePageActions.handleInputByXPath("//input[@data-qa-id='range-filter-input-max']", String.valueOf(max));

        homePageActions.waitForClickableElement(homePageLocators.applyFilterLocator);
        homePageActions.handleButtonByText("Apply Filter");

        homePageActions.waitForClickableElement(homePageLocators.showResultsLocator);
        homePageActions.handleButtonByText("Show results");
    }

    @Then("Compare page contents to previous")
    public void comparePageContentsToPrevious() {
        homePageActions.captureData(filteredHeaderNames, filteredTableData);

        LOGGER.info("{} cryptocurrencies have been listed", filteredTableData.size());

        LOGGER.info("The filters have presented {} cryptocurrencies", filteredTableData.size());
        for (LinkedHashMap<String, String> filteredTableDatum : filteredTableData) {
            LOGGER.info("Rank: {}", filteredTableDatum.get("#"));
            LOGGER.info("Name: {}", filteredTableDatum.get("Name"));
            LOGGER.info("Price: {}", filteredTableDatum.get("Price"));
            LOGGER.info("Market Capitalization: {}", filteredTableDatum.get("Market Cap"));
            LOGGER.info("Circulating Supply: {}", filteredTableDatum.get("Circulating Supply"));
            LOGGER.info("24h Volume: {}", filteredTableDatum.get("Volume(24h)"));
            LOGGER.info("-----");
        }

        for (int i = 0; i < filteredTableData.size(); i++) {
            homePageActions.rankComparator(i, fullTableData, filteredTableData);
            homePageActions.marketCapComparator(i, fullTableData, filteredTableData);
            homePageActions.supplyComparator(i, fullTableData, filteredTableData);
            homePageActions.volumeComparator(i, fullTableData, filteredTableData);
            LOGGER.info("-----");
        }
    }
}
