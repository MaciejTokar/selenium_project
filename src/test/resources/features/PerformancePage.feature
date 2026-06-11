Feature: Performance page and every test case associated with performance and managing reviews.

  Background:
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    When User has been successfully logged in
    Then I click 'Performance' panel on the header list

  Scenario Outline: Verify after searching for a user with incorrect data receive information that no results were found.
    Given I enter name "<name>" into Employee Name input and confirm
    And I select job title "<title>" in Job Title dropdown
    And I select unit "<unit>" in Sub Unit dropdown
    And I select option "<option>" in Include dropdown
#    Możliwe przerzucenie tego stepu do common page steps
    When I click 'Search' button
    Then No results found information is displayed

    Examples:
      | name  | title             | unit        | option                     |
      | valid | Account Assistant | Engineering | Current and Past Employees |
