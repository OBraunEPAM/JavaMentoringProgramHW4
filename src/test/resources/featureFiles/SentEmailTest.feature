Feature: Cucumber Sent Email Test

  Background:
    Given I open 'MAIL_RU_URL' site
    When I login as 'AUTOTEST_USER'
    Then Title should be 'LOG_IN_TITLE'

  @SmokeScenario
  Scenario: Sent e-mail is presented in Inbox folder
    When I open create new e-mail window
    And Fill e-mail with 'AUTOTEST_EMAIL' data
    And Click 'SEND' button
    And Open 'INBOX' folder
    Then 'AUTOTEST_EMAIL' should be presented there
    When I open 'AUTOTEST_EMAIL'
    Then Content of 'AUTOTEST_EMAIL' should match sent e-mail

  @ScenarioOutlineTag
  Scenario Outline: Deleting e-mail from INBOX, DRAFT and SENT folders
    When I clear emails from <folder>
    Then 'AUTOTEST_EMAIL' shouldn't be in <folder>

    Examples:
      | folder |
      | DRAFT  |
      | INBOX  |
      | SENT   |
