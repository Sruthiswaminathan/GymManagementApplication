Feature: Profile Page

  Background:
    Given I open the Gymmanagement Application
    When Collects the email ad password from Config1 properties file
    And I click the Login
    Then I should be in the workout page

  Scenario: Update personal information on the Profile Page
    Given I click on the profile icon
    And I click on My Accounts
    When I update the name with "John Doenkm"
    And I update the target with "Lose weight"
    And I update the preferred activity to "yoga"
    And click on save changes
    Then I should get message as "Profile Updated Sucessfully"


  Scenario: Update personal information on the Profile Page
    Given I click on the profile icon
    And I click on My Accounts
    When I update the name with "John Doenkm"
    And I update the target with "Lose weight"
    And I update the preferred activity to "yoga"
    And click on save changes
    And I should get error message as "Same Data"
    And click on logout button
    Then I should receive  a success message for logout as "Successfully Logged Out"
    #Then close the browser
  #Scenario: Update personal information on the Profile Page with empty name
    #Given I click on the profile icon
    #And I click on My Accounts
    #When I update the name with ""
    #And I update the target with "Lose weight"
    #And I update the preferred activity to "climbing"
    #And click on save changes
    # Then I should get error message for empty name feild as "Name is required."

  #Scenario: Update personal information on the Profile Page name without starting with capital letter
    #Given I click on the profile icon
    #And I click on My Accounts
    #When I update the name with "hgg"
    #And I update the target with "Lose weight"
    #And I update the preferred activity to "climbing"
    #And click on save changes
    #Then I should get error message for name without caps letter as "First Letter of name should be Capital."

  #Scenario: Update personal information on the Profile Page name with special character
   # Given I click on the profile icon
   # And I click on My Accounts
   # When I update the name with "Johngh DoengKg@##"
     # And I update the target with "Lose weight"
    #And I update the preferred activity to "climbing"
    #And click on save changes
    #Then I should get error message for name with special character as "Please enter a valid name. Only alphabetic characters and spaces are allowed ."