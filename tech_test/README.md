# tech_test

## Overview

This test suite is responsible for performing filtering and data extraction for comparison on CoinMarketCap. The test
suite uses Cucumber and Selenium in order to execute a UI test scenario with a BDD approach.

## Project structure

| Module | Description |
| ------ | ----- |
| `main` | Main module contains setup classes, locators and helper functions (within the action classes) |

* `locators`: Contains the locator class, with selectors used to interact with web elements.
* `utils`: Contains the DriverContext; the class which is required to intiialize the webdriver.
* `resources`: Is where any relevant resource files can be found such as the `log4j2.xml` file.


| Module | Description |
| ------ | ----- |
| `test` | Test module contains test runner, step definition and webdriver hooks.

* `definitions`: Contains the webdriver Hooks class and the test scenario step definition class.
* `resources`: Is where any resource files relevant to testing can be found such as the cucumber.properties file.
  * `features`: Is where the feature file(s) can be found.

## Dependencies

This test suite has no external dependencies.

## How to run the tests

To run the tests you only need to run the `HomePage.feature` file.

The tests can also be run using `mvn clean test -Dcucumber.features="src/test/resources/features`, however, please note
that depending on your Maven configuration, this could result in the test being run twice and therefore erroneous data
may be collected.

**It is strongly advised to run the tests using the feature file.**

## Project definition

This project uses:
- Java SDK 11
- Apache Maven 3.8.3

## Notes

This repository uses the `io.github.bonigarcia:webdrivermanager` dependency to manage the webdriver, and therefore,
you should not need to manually download one.

Like all UI tests, these tests *can* be flaky, in the event of a test failure/exceptions please run the test again, as
Selenium may run into some issues with the explicit waits.
