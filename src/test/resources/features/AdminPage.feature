Feature: Admin page and every test case associated with admin set ups and users available on the platform.

  Background:
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    Then User has been successfully logged in

  Scenario: Creating user account for employee to the platform by admin
    Given I click 'Admin' panel on the header list
    And I click 'Add' button
    And I select option 'ESS' in User Role dropdown
    And I select option 'Enabled' in Status dropdown
    And I enter 'name' into Employee Name input and confirm
    And I enter 'generate' into Username input
    And I enter 'generate' into Password input
    And I enter the same password into Confirm Password input
    When I click 'Save' button
    Then Confirmation is displayed and user has been created