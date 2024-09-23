Feature: Retrieve Client Feedback

  Background:
    Given the API endpoint is present at the Config file for get client feedback
    And the user is authenticated with correct credentials for clients feedback
    And the response for login contains valid id token

  Scenario: Retrieve client feedback with valid parameters
    Given the endpoint for client feedback is "/clients/feedback"
    And the feedback request parameters are provided
    When I send a GET request to the client feedback endpoint
    Then the successful response should be 200
    And the response message for feedback retrieval should be "Feedback retrieved successfully."
