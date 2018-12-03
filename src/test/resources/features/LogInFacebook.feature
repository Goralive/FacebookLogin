Feature:
  As an existing user
  I want to be able to add to log in to my Facebook account.
  Scenario: Login with a Facebook account.
    Given I am on Facebook login page
    When I fill my email "1501sez1@gmail.com"
    And I fill my password "aB123456"
    And I click on 'LogIn'
    Then I'm login with current profile
