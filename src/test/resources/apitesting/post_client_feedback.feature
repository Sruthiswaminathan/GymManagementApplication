Feature: Post Client Feedback

  Background: The user is logged in
    Given the API endpoint is present at the Config file for post client feedback
    And the user is authenticated with correct credentials for client feedback
    And the response contains valid id token

  Scenario: Submit client feedback with valid input
    Given the API endpoint for client feedback is "/clients/feedback"
    And the feedback details are provided
      | sessionId  | session82                            |
      | workoutId  | b313a5fe-206e-46ac-aa89-ce7ef1e794d2 |
      | clientId   | c037e6a5-71b1-4e77-89b5-79cfc1f04d59 |
      | coachId    | c43a7ab6-2a9c-4be4-9aac-883940ed7147 |
      | rating     | 4                                    |
      | notes      | sample                               |
      | createDate | 2020-09-13                           |
    When I send a POST request to the client feedback endpoint
    Then the successful response status should be 200
    And the response message for feedback submission should be "Feedback created successfully."

  Scenario: Submit client feedback with invalid input
    Given the API endpoint for client feedback is "/clients/feedback"
    And the feedback details are invalid
      | sessionId  | session32                            |
      | workoutId  | b313a5fe-206e-46ac-aa89-ce7ef1e794d2 |
      | clientId   | c43a7ab6-2a9c-4be4-9aac-883940ed7147 |
      | coachId    | c43a7ab6-2a9c-4be4-9aac-883940ed7147 |
      | rating     | 3                                    |
      | notes      | sample                               |
      | createDate | 2024-10-13                           |
    When I send a POST request to the client feedback endpoint
    Then the unsuccessful response status should be 400
    And the response message for feedback submission should be "Feedback already exists"
