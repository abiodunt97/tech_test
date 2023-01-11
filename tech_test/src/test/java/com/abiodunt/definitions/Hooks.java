package com.abiodunt.definitions;

import com.abiodunt.utils.DriverContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public static void setUp() {
        DriverContext.initializeDriver();
    }

    @After
    public static void tearDown() {
        DriverContext.tearDown();
    }
}
