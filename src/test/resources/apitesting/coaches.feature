Feature: Retrieve Coaches Information

  Background: The user is logged in
    Given the API endpoint is present at the Config file
    And the user is authenticated with correct credentials
    And the response contains a valid id token


  Scenario: Retrieve list of coaches with valid status code
    Given the API endpoint for coaches is "/coaches"
    When I send a GET request to the coaches endpoint
    Then the response should be 200
    And the response message for retrieve coaches should be "Coaches retrieved successfully."

