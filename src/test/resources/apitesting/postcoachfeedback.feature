Feature: Submit Coach Feedback

  Background:
    Given the API endpoint is present at the Config file for post coaches feedback
    And the user is authenticated with correct credentials for coach feedback
    And the response contains valid id token for logging

  Scenario: Submit valid coach feedback
    Given the API endpoint for coach feedback is "/coaches/feedback"
    And the feedback details are valid for coach feedback
      | sessionId | workoutId                            | clientId  | coachId  | rating | notes          | createDate |
      | string    | 33bf7f86-a9af-4d18-88d8-fca34d7212a2 | client123 | coach456 | 5      | Great session! | 2024-09-04 |
    When I send a POST request to the coach feedback endpoint
    Then successful response should be 200
    And the response message for feedback should be "Feedback created successfully."

  Scenario: Submit invalid coach feedback
    Given the API endpoint for coach feedback is "/coaches/feedback"
    And the Invalid feedback details are:
      | sessionId | workoutId                            | clientId                             | coachId                              | rating | notes   | createDate |
      | Session45 | dab4f6a0-2f68-4bc7-a81d-5c462d3bdd50 | 2d34b846-e617-496f-bfed-bc2f7c509d87 | c43a7ab6-2a9c-4be4-9aac-883940ed7147 | 3      | average | 2024-02-20  |
    When I send a POST request to the coach feedback endpoint
    Then the unsuccessful response should be 400
    And the response error message for feedback should be "Feedback already exists"
