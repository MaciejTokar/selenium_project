Feature: My Info page and every test case associated with user settings.

  Background:
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    Then User has been successfully logged in

    Scenario: Verify whether the buttons have links attached
      Given I click 'My Info' panel on the header list
      Then I check if every button of menu have link attached