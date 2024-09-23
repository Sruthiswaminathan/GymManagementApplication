Feature: Get Coach Feedback

  Background:
    Given the API endpoint is present at the Config file for get coaches feedback
    And the user is authenticated with correct credentials for coach feedback retrieval
    And the response for login must contains valid id token

  Scenario: Retrieve feedback with valid parameters
    Given the API endpoint for getting coach feedback is "/coaches/feedback"
    And the query parameters are sessionId="string", createDate="2024-09-04", workoutId="c43a7ab6-2a9c-4be4-9aac-883940ed7147"
    When I send a GET request to the coach feedback endpoint
    Then the successful response statuscode should be 200
    And the response success message should be "Feedback retrieved successfully."
   # And the response data should contain empty feedback items

