Feature: Coach Selection Page

  Background:
    Given I open the GymManagement Application
    When I enter "sachintendulkar@gmail.com" and "Sachin@123"
    And I click the Login button
    Then I should be in the dashboard page

  @Responsive
  Scenario: Verify layout adaptability
    Given User logged in as Client
    When Resize the browser window to different sizes
    Then Check the adaptability of the layout

  @Coaches
  Scenario: Verify coach selection page accessibility
    Given User logged in as Client
    When From the dashboard locate and click on the coach selection page
    Then A list of available coaches each with a profile card, should be displayed


  @InvalidDate
  Scenario: Verify invalid date and time selection
    Given User logged in as Client
    When From the dashboard locate and click on the coach selection page
    Then click workout button of Coach selection
    And should select an invalid date for the workout
    Then I should receive a "Please Select Valid Date"

  @bookWorkout
  Scenario: Verify coach profile card details
    Given User logged in as Client
    When From the dashboard locate and click on the coach selection page
    Then For each coach profile card check for the following details:
      | Name                     |
      | Short Summary            |
      | Expertise/Specialization |
      | Client Reviews/Ratings   |
      | Workout button           |
    Then click workout button of Coach selection
    And should select date and time for the workout
    And check the testimonal and click book workout
    Then I should get  "Workout Created Successfully"








