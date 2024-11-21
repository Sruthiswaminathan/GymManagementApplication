Feature: Coach Profile functionalities

  Background:
    Given Coach open the Gym Management Application
    When Coach collect the email and password from config4 properties file
    And Coach click Login button
    Then Coach should get success message "Successfully Logged in"
    Then redirected to the workout list page


  @ProfileUpdate
  Scenario: Views profile and updates profile
    When I clicked on the profile icon
    Then I should click on the edit profile button
    And I should fill the form
    And click on the update button
    Then I should see the message profile updated.
    And click logout button

  @clientFeedback
  Scenario: Views clients feedback
    When I click on the profile
    Then I Should click on the My Account edit button
    And I should see client feedback link and Click to view feedback
    Then I should see the feedbacks
    And I should click on the Logout button
