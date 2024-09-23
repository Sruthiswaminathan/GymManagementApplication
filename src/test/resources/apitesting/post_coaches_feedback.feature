Feature: Submit Coach Feedback

  Background:
    Given the API endpoint is present at the Config file for post coaches feedback
    And the user is authenticated with correct credentials for coach feedback
    And the response contains valid id token for logging

  Scenario: Submit valid coach feedback
    Given the API endpoint for coach feedback is "/coaches/feedback"
    And the feedback details are valid for coach feedback
      | sessionId | workoutId                            | clientId                             | coachId                              | rating | notes          | createDate |
      | session56 | ffbe7a54-ef39-4078-97b4-333503875ead | c037e6a5-71b1-4e77-89b5-79cfc1f04d59 | c43a7ab6-2a9c-4be4-9aac-883940ed7147 | 5      | Great session! | 2024-09-04 |
    When I send a POST request to the coach feedback endpoint
    Then successful response should be 200
    And the response message for feedback should be "Feedback created successfully."

  Scenario: Submit invalid coach feedback
    Given the API endpoint for coach feedback is "/coaches/feedback"
    And the Invalid feedback details are:
      | sessionId | workoutId                            | clientId                             | coachId                              | rating | notes          | createDate |
      | Session56 | f136fc1f-517c-4bc9-8903-f2dc1b7f057d | c037e6a5-71b1-4e77-89b5-79cfc1f04d59 | c43a7ab6-2a9c-4be4-9aac-883940ed7147 | 5      | Great session! | 2024-09-04 |
    When I send a POST request to the coach feedback endpoint
    Then the unsuccessful response should be 400
    And the response error message for feedback should be "Feedback already exists"
