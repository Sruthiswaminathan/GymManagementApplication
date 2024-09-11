Feature: Retrieve Specific Coach Information by ID

  Background: The user is logged in
    Given the endpoint is present at the Config file
    And the user is authenticated with below credentials for Coaches
    And the response contains a valid token

  Scenario: Retrieve a coach's details by valid ID with status code 200
    Given the API endpoint for coach details is "/coaches/{id}"
    When I send a GET request to the coach details endpoint with ID "7aec6bd5-ec62-4640-a951-97ec195d301e"
    Then the response should be statuscode 200 for successful
    And the response message for coaches Id should be "Coach retrieved by id successfully."
    And the response should include "id", "name", "summary", "specialization", "rating", and "reviews"

  Scenario Outline:  Retrieve a coach's details with invalid ID and receive 404 error
    Given the API endpoint for coach details is "/coaches/{id}"
    When I send a GET request to the coach details endpoint with ID "e6791b2b-5e80-"
    Then the response should be status code <status> for invalid id
    And the response error message should be "<message>"
    Examples:
      | status | message         |
      | 404    | Coach not found |
