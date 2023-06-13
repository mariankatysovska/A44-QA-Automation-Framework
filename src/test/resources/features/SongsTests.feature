Feature: Songs Tests

  Scenario: Play song
    Given I open Login Page
    When I enter email
    And I enter password
    And I click submit
    And I click play button
    Then I see equalizer
