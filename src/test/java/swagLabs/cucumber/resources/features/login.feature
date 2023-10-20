Feature: Log in to the application

    @Regression @Positive
Scenario: Login to application as valid user
    Given User is on the login page
    When User input username
    And User input password
    And Click on Login Button
    Then Homepage should be displayed

    @Regression @Negative
Scenario: Login to application as invalid user
    Given User is on the login page
    When User input username
    And User input invalid password
    And Click on Login Button
    Then Error message should be displayed

    @TDD
    Scenario Outline: User login to Saucedemo
        Given User is on the login page
        When User input <username> as username
        And User input <password> as password
        And Click on Login Button
        Then User verify <status> login result
    Examples:
        | username      | password      | status    |
        | standard_user | secret_sauce  | success   |
        | standard_user | passwordasal  | failed    |
        | tesuser       | secret_sauce  | failed    |

