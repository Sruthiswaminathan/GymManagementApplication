Feature: Coach functionalities

  Background:
    Given Coach opened the Gym Management Application
    When Coach collect the email and password from config3 properties file
    And Coach should click Login button
    Then Coach should get a success message "Successfully Logged in"
    Then redirected to the workouts list page


  @Workout
  Scenario: Views scheduled workouts list
    When I should see the list of scheduled workouts with details such as Workout Name, Date and short summary
    And I click on the finish workout button
    Then I should see Leave feedback button and Click on it
    Then I fill the feedback and click on submit button
    #Then I should see the message "Feedback submitted successfully"
    Then I should see the message Finished
    Then I clicked on logout button


  @CancelWorkout
  Scenario: views scheduled workouts list and cancel workout
    When I should see the list of scheduled workouts
    And I should see the workout i have to cancel and  click on cancel workout button
    Then I should see a window having option cancel workout and click on it
    Then I should see the cancelled message
