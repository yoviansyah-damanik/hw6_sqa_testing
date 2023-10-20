Feature: User adds product to cart

  @Regression @Positive
    Scenario: User adds products via product pages
    Given User has login
    Then User clicks Hamburger Menu Button
    Then User clicks All Items Menu
    When User clicks Add to Cart Button
    Then The product has been successfully added to the basket by increasing the number of numbers on the basket icon