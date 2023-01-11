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
import java.util.Comparator;
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
    public void sortRowsby20(int value) throws InterruptedException {
        homePageActions.sortRows(String.valueOf(value));
//        homePageActions.waitForPageLoad();
    }

    @And("The relevant cryptocurrency data on the page has been captured")
    public void captureTableData() throws InterruptedException {
        homePageActions.scrollToBottom();
        Thread.sleep(5000);
        homePageActions.scrollToTop();
        homePageActions.captureData(headerNames, fullTableData);
        LOGGER.info("{} cryptocurrencies have been listed", fullTableData.size());

        LOGGER.info("The top {} cryptocurrencies are: ", fullTableData.size());
        for (LinkedHashMap<String, String> fullTableDatum : fullTableData) {
//            String[] identifier = fullTableDatum.get("Name").split(System.lineSeparator());
            LOGGER.info("Rank: {}", fullTableDatum.get("#"));
            LOGGER.info("Name: {}", fullTableDatum.get("Name"));
//            LOGGER.info("Ticker Symbol: {}", identifier[1]);
            LOGGER.info("Price: {}", fullTableDatum.get("Price"));
            LOGGER.info("Market Capitalization: {}", fullTableDatum.get("Market Cap"));
            LOGGER.info("Circulating Supply: {}", fullTableDatum.get("Circulating Supply"));
            LOGGER.info("-----");
        }
//        LOGGER.info("The rank 1 cryptocurrency is: {}", fullTableData.get(0).get("Name"));
//        LOGGER.info("The rank 20 cryptocurrency is: {}", fullTableData.get(19).get("Name"));
//        LOGGER.info("The cryptocurrency with the highest price is: {}", fullTableData.stream().max(Comparator.comparing(x -> x.get("Price"))));

    }

    @Then("Filter by Algorithm - PoW")
    public void filterByAlgorithmPoW() throws InterruptedException {
        homePageActions.clickButton("Filters");
        homePageActions.waitForElementPresence(homePageLocators.algorithmButtonLocator);
//        Thread.sleep(2000);
        homePageActions.handleDropDown("Algorithm");
        homePageActions.waitForElementPresence(homePageLocators.powFilterLocator);
        homePageActions.clickButton("PoW");
    }

    @And("Filter by Mineable")
    public void filterByMineable() throws InterruptedException {
        homePageActions.waitForElementPresence(homePageLocators.addFilterLocator);
        homePageActions.clickButton("Add Filter");

//        Thread.sleep(2000);
        homePageActions.waitForElementPresence(homePageLocators.mineableToggleLocator);
        homePageActions.handleToggle("Mineable", "mineable");

//        Thread.sleep(2000);

    }

    @And("Filter by Coins")
    public void filterByCoins() throws InterruptedException {
        homePageActions.waitForClickableElement(homePageLocators.allCryptocurrenciesLocator);
        homePageActions.handleButtonByText("All Cryptocurrencies");
//        Thread.sleep(2000);
        homePageActions.waitForClickableElement(homePageLocators.coinsLocator);
        homePageActions.handleButtonByText("Coins");
//        Thread.sleep(2000);
    }

    @And("Set price range to {int} - {int}")
    public void setPriceRangeTo(int min, int max) throws InterruptedException {
        homePageActions.waitForClickableElement(homePageLocators.priceLocator);
        homePageActions.handleButtonByText("Price");
//        Thread.sleep(2000);
        homePageActions.waitForElementPresence(homePageLocators.minRangeInputLocator);
        homePageActions.handleInputByXPath("//input[@data-qa-id='range-filter-input-min']", String.valueOf(min));
//        Thread.sleep(2000);
        homePageActions.waitForElementPresence(homePageLocators.maxRangeInputLocator);
        homePageActions.handleInputByXPath("//input[@data-qa-id='range-filter-input-max']", String.valueOf(max));
//        Thread.sleep(2000);
        homePageActions.waitForClickableElement(homePageLocators.applyFilterLocator);

        homePageActions.handleButtonByText("Apply Filter");
//        Thread.sleep(2000);
        homePageActions.waitForClickableElement(homePageLocators.showResultsLocator);

        homePageActions.handleButtonByText("Show results");
//        Thread.sleep(2000);
    }

    @Then("Compare page contents to previous")
    public void comparePageContentsToPrevious() {
        homePageActions.captureData(filteredHeaderNames, filteredTableData);

        LOGGER.info("{} cryptocurrencies have been listed", filteredTableData.size());

        LOGGER.info("The filters have presented {} cryptocurrencies", filteredTableData.size());
        for (LinkedHashMap<String, String> filteredTableDatum : filteredTableData) {
//            String[] identifier = fullTableDatum.get("Name").split(System.lineSeparator());
            LOGGER.info("Rank: {}", filteredTableDatum.get("#"));
            LOGGER.info("Name: {}", filteredTableDatum.get("Name"));
//            LOGGER.info("Ticker Symbol: {}", identifier[1]);
            LOGGER.info("Price: {}", filteredTableDatum.get("Price"));
            LOGGER.info("Market Capitalization: {}", filteredTableDatum.get("Market Cap"));
            LOGGER.info("Circulating Supply: {}", filteredTableDatum.get("Circulating Supply"));
            LOGGER.info("-----");
        }

        for (int i = 0; i < filteredTableData.size(); i++) {
            LOGGER.info("The rank {} cryptocurrency in the full table is {} and the rank {} cryptocurrency" +
                    " in the filtered table is {}", i+1, fullTableData.get(i).get("Name"), i+1, filteredTableData.get(i).get("Name"));
        }
    }
}
