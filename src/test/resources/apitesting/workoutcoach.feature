Feature: Retrives Workout based on coach id

  Background: The user is logged in
    Given the url is collected and stored from Config file
    And the user is authenticated with correct  below credentials with email and password
    And the response should include correct valid id token and to be stored

  Scenario: Successfully retrieve all workouts of a coach with valid Bearer token
    Given the user has a coach ID "coach76677123"
    When the user sends a GET request to retrieve all workouts of the coach with BearerToken
    Then the response should return a status code 200 for GET coaches
    And the get coaches response body should contain message "Workouts retrieved for coach successfully" and a list of workouts
