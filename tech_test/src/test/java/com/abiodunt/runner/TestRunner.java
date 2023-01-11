package com.abiodunt.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.abiodunt")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.abiodunt")
public class TestRunner {
}
