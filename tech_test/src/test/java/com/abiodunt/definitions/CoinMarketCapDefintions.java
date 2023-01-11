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
        Thread.sleep(5000);
//        homePageActions.waitForPageLoad();
    }

    @And("The relevant cryptocurrency data on the page has been captured")
    public void captureTableData() throws InterruptedException {
        homePageActions.captureData(headerNames, fullTableData);
        LOGGER.info("{} cryptocurrencies have been listed", fullTableData.size());
        Thread.sleep(5000);
    }

    @Then("Filter by Algorithm - PoW")
    public void filterByAlgorithmPoW() throws InterruptedException {
        System.out.println("test");
    }

    @And("Filter by Mineable")
    public void filterByMineable() throws InterruptedException {
        System.out.println("test");
    }

    @And("Filter by Coins")
    public void filterByCoins() throws InterruptedException {
        System.out.println("test");
    }

    @And("Set price range to {int} - {int}")
    public void setPriceRangeTo(int min, int max) throws InterruptedException {
        System.out.println("test");
    }

    @Then("Compare page contents to previous")
    public void comparePageContentsToPrevious() {
        System.out.println("test");
    }
}
