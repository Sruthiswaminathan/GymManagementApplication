Feature: Admin functionalities

  Background:
    Given User opened the Gym Management Application
    When I collect the email and password from config2 properties file
    And I should click Login button
    Then I should get a success message "Successfully Logged in"
    Then redirected to the coaches list page

  @AdminProfilePicture
    Scenario: Admin views profile and updates profile picture
    Given I click on My profile
    Then I should click on the  edit profile
    And I should click on profile picture button
    Then I click logout

#  @completeAdminFlow
#  Scenario: Admin logs in, views coaches list, checks weekly report, and downloads report
#    Then I should see the list of coaches with details such as Coach Name, Expertise, and Ratings
#    And I click on the view report button of "John"
#    Then I should see the weekly report with details such as:
#      | Coach Name            |
#      | Report Week           |
#      | Total Sessions        |
#      | Total Duration (mins) |
#      | Average Client Rating |
#      | Feedback Count        |
#      | Total Clients         |
#    When I click the Download button
