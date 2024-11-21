Feature: Workout Page

  Background:
    Given I open Gym Management Application
    When Collects the email ad password from Config1 file
    And I click Login
    Then I should see the dashboard page

  @CancelWorkout
  Scenario: Canceling a scheduled workout
    When the user navigates to the Workout page
    When the user views the scheduled workout for "Yoga" on date "27/9/2024"
    And the user clicks on the Cancel Workout button

  @ResumeWorkout
  Scenario: Resuming a scheduled workout
    When the user navigates to the Workout page
    When the user views the scheduled workout for "Climbing" on date "28/9/2024"
    And the user clicks on the Resume Workout button

  @FinishWorkout
  Scenario: Finish a scheduled workout
    When the user navigates to the Workout page
    When the user views the scheduled workout for "Climbing" on date "28/9/2024"
    And the user clicks on the Finish Workout button

  @LeaveFeedback
  Scenario: Leave a feedback for finished workout
    When the user navigates to the Workout page
    When the user views the finished workout for "Climbing" on date "28/9/2024"
    When the user leaves feedback with rating "5" and comments "Great workout!"

  @LeaveFeedback
  Scenario: Leave a feedback for finished workout with empty notes
    When the user navigates to the Workout page
    When the user views the finished workout for "Strength training" on date "29/9/2024"
    When the user leaves feedback with rating "5" and comments ""
    #Then submit the form


