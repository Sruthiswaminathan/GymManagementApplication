Feature: Retrives Workout based on client id

  Background: The user is logged in
    Given the url is collected from Config file
    And the user is authenticated with below credentials with email and password
    And the response should contain a correct valid id token


  Scenario: Successfully retrieve all workouts of a client with valid Bearer token
    Given the user has a client ID "client46556456"
    When the user sends a GET request to retrieve all workouts of the client with BearerToken
    Then the response should return a status code 200 for GET clients
    And the get clients response body should contain message "Workouts retrieved for client successfully." and a list of workouts