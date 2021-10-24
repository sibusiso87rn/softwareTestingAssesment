@Regression
Feature: Shopping

  Background:
    Given The user navigates to the Shopping Website

  Scenario Outline: User wants to shop
    And  The user is navigated to the login page
    And  The user enters the username "<username>" and password "<password>"
    And  The user clicks the login button
    Then The user is presented with shopping screen with a list of items to shop
    And  The user adds item "0" to the shopping cart
    And  The user adds item "1" to the shopping cart
    Then The cart icon displays "2" items on the list
    And  The user clicks the cart icon
    Then The user is navigated to the cart summary page
    And  The user is presented with a correct list of cart items
    And  The user clicks checkout button
    And  The user is presented with the checkout info page
    And  The user enters the customer name "<customerName>" the customer surname "<customerSurname>" and the zipcode "<customerZipcode>"
    And  The customer continue from the checkout information page
    Then The user is navigated to the checkout overview page
    And  The pre tax total item price is calculated correctly
    And  The user clicks the finish button
    Examples:
      | username     | password   |customerName|customerSurname|customerZipcode|
      |standard_user |secret_sauce|Sibusiso    |Radebe         |4491           |

