Feature: Leave page and every test case associated with annual leave set up.

  Background:
    Given I enter login 'Admin' into Username input
    And I enter password 'admin123' into Password input
    When I click on 'Login' button
    When User has been successfully logged in
    Then I click 'Leave' panel on the header list

#    TODO
#  dane, ktore sa w tym tescie maja zostac podane przy pomocy feature examples
#  utworzyc free jire, podpiac github/bitbucket pod jire i bedzie mozna tworzyc z niej branche
  Scenario: NAZWA DO ZMIANY Verify that Assign Leave Panel contains all required leave types
    Given I click on 'Assign Leave' button of navigation
#    EDIT2: jednak dziala metoda z AdminPage - musialem dodac dodatkowego waita w metodzie inaczej nie lapalo elementu i wywalalo NoSuchElementException
#    And I enter 'employee name' into Employee Name input and confirm
    And I enter 'name' into Employee Name input and confirm
    And I select option 'CAN - FMLA' in Leave Type dropdown
    And I enter date '2026-05-02' into From Date input
    And I enter date '2026-10-02' into To Date input
    And I select option 'All Days' in Partial Days dropdown
    And I select option 'Half Day - Morning' in Duration dropdown
    And I click 'Assign' button
    And I click 'Ok' confirm button
#    Analogiczny przypadek - juz posiadam taką asercję z elementem i selectorem 1:1
#    Tutaj przy tych samych ustawieniach raz wychodzi success,a raz inny pop up
    Then Confirmation is displayed

  Scenario: Verify that Assign Leave Panel contains all required leave types
    Given I click on 'Assign Leave' button of navigation
    Then Validate required options of Leave Type

  Scenario Outline: Verify that Assign Leave Panel contains all required leave types using examples
    Given I click on 'Assign Leave' button of navigation
    Then Validate required types "<options>" of Leave Type

    Examples:
      | options           |
      | CAN - Bereavement |
      | CAN - FMLA        |
      | CAN - Matternity  |
      | CAN - Personal    |
      | CAN - Vacation    |
      | US - Bereavement  |
      | US - FMLA         |
      | US - Matternity   |
      | US - Personal     |
      | US - Vacation     |



