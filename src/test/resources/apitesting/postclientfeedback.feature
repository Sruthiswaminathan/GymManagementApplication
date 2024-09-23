Feature: Post Client Feedback

  Background: The user is logged in
    Given the API endpoint is present at the Config file for post client feedback
    And the user is authenticated with correct credentials for client feedback
    And the response contains valid id token


  Scenario: Submit client feedback with valid input
    Given the API endpoint for client feedback is "/clients/feedback"
    And the feedback details are provided
    When I send a POST request to the client feedback endpoint
    Then the successfull response should be 200
    And the response message for feedback submission should be "Feedback submitted successfully."

  Scenario: Submit client feedback with invalid input
    Given the API endpoint for client feedback is "/clients/feedback"
    And the feedback details are invalid
    When I send a POST request to the client feedback endpoint
    Then the unsuccessfull response should be 400
    And the response message for feedback submission should be "Rating must be between 0 and 5."

