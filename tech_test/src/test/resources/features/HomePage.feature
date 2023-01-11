Feature: Data extraction and comparison for cryptocurrencies on CoinMarketCap

  The purpose of this test is to extract data sets from the CoinMarketCap
  website for comparison.

  Scenario: Extract and compare filtered and unfiltered dataset
    Given User is on CoinMarketCap home page
      When User sorts rows by 20
      And The relevant cryptocurrency data on the page has been captured
      Then Filter by Algorithm - PoW
      And Filter by Mineable
      And Filter by Coins
      And Set price range to 100 - 10000
      Then Compare page contents to previous
