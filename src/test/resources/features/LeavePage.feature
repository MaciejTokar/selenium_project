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
  Scenario Outline: Verify that Assign Leave form works correctly with different variants of values
    Given I click on 'Assign Leave' button of navigation
#    EDIT2: jednak dziala metoda z AdminPage - musialem dodac dodatkowego waita w metodzie inaczej nie lapalo elementu i wywalalo NoSuchElementException
#    And I enter 'employee name' into Employee Name input and confirm
#    Czy taki rodzaj kroku nie powinien byc w oddzielnym scenariuszu?
    And I enter name "<name>" into Employee Name input and confirm
    And I select option "<options>" in Leave Type dropdown
    And I enter date "<fromDate>" into From Date input
    And I enter date "<toDate>" into To Date input
    And I select option "<partial>" in Partial Days dropdown
    And I select option "<duration>" in Duration dropdown
    And I click 'Assign' button
    And I click 'Ok' confirm button
#    Analogiczny przypadek - juz posiadam taką asercję z elementem i selectorem 1:1
#    Tutaj przy tych samych ustawieniach raz wychodzi success,a raz inny pop up
    Then Confirmation is displayed

    Examples:
      | name    | options           | fromDate   | toDate     | partial           | duration             |
      | valid   | CAN - Bereavement | 2026-05-02 | 2026-10-02 | All Days          | Half Day - Morning   |
      | invalid | CAN - FMLA        | 2026/05/02 | 2026/05/02 | All Days          | Half Day - Afternoon |
      | invalid | CAN - Matternity  | 2026.05.02 | 2026.10.02 | All Days          | Specify Time         |
      | valid   | CAN - Personal    | 2026-05-02 | 2026-10-02 | Start Day Only    | Half Day - Morning   |
      | valid   | CAN - Vacation    | 02-05-2026 | 02-10-2026 | Start Day Only    | Half Day - Afternoon |
      | valid   | US - Bereavement  | 2026-05-02 | 2026-10-02 | Start Day Only    | Specify Time         |
      | valid   | US - FMLA         | 2026-05-02 | 2026-10-02 | End Day Only      | Half Day - Morning   |
      | valid   | US - Matternity   | 2026-05-02 | 2026-10-02 | End Day Only      | Half Day - Afternoon |
      | valid   | US - Personal     | 2026-05-02 | 2026-10-02 | End Day Only      | Specify Time         |
      | valid   | US - Vacation     | 2026-05-02 | 2026-10-02 | Start And End Day | Half Day - Morning   |

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



