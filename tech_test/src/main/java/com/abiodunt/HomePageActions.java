package com.abiodunt;

import com.abiodunt.locators.HomePageLocators;
import com.abiodunt.utils.DriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.lang.invoke.MethodHandles;

public class HomePageActions extends PageActions {

    HomePageLocators homePageLocators;
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public HomePageActions() {
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(DriverContext.getDriver(), homePageLocators);
    }
}
