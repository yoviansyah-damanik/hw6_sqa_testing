Feature: User can access the cart page

  @Regression @Negative
  Scenario: User access the cart page without logging in
    Given User is on login page
    Then User enter a link to the cart page via the address bar
    Then Error messages must be logged in first