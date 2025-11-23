Feature: Login page and every test case associated with login page

  Scenario: Successful logging in on OrangeHrm demo webside
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    Then User has been successfully logged in

  Scenario: Unsuccessful login with an incorrect username
    Given I enter incorrect login into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    Then Announcement 'Invalid credentials' appear

  Scenario: Unsuccessful login with an incorrect password
    Given I enter login 'Admin' into Username input
    And I enter incorrect 'password' into Password input
    When I click on 'Login' button
    Then Announcement 'Invalid credentials' appear

  Scenario: Reset password link sent successfully
    Given I click on link 'Forgot your password?'
    And I type 'Admin' into Username input
    When I click on 'Reset Password' button
    Then Reset password link has been successfully sent

  Scenario: Username is required to reset your password
    Given I click on link 'Forgot your password?'
    When I click on 'Reset Password' button
    Then Warning 'Required' is displayed