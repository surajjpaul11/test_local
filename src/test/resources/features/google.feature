Feature: Check with google search

  @google
  Scenario: User does a google search
    Given User is on "https://google.com" site
#    Given User is on the site home page
    When title of page is "Google"
    Then User searches for "Test NG"
    Then User clicks on first result on page
    Then title of page contains "TestNG"

