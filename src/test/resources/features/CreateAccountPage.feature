Feature: Creating accounts by Admin and checks that they are working properly

  Background:
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    And I click on 'Login' button
    And I click 'Admin' panel on the header list
    Then I click 'Add' button

  Scenario Outline: Verify if logging to account working properly
    Given I select "<role>" in User Role dropdown
    And I select "<status>" in Status dropdown
    And I enter 'name' into Employee Name input and confirm
    And I enter "<username>" into Username input
    And I enter "<password>" into Password input
    And I enter the same "<password>" into Confirm Password input
    And I click 'Save' button
    Then I logout from currently logged account
    And I enter login "<username>" into Username input
    And I enter password "<password>" into Password input
    When I click on 'Login' button
    Then Login result should be "<result>"

    Examples:
      | role  | status   | username  | password | result     |
      | Admin | Enabled  | Maciej110 | Test123! | Successful |
      | Admin | Disabled | Maciej111 | Test123! | Fail       |
      | ESS   | Enabled  | Maciej112 | Test123! | Successful |
      | ESS   | Disabled | Maciej113 | Test123! | Fail       |