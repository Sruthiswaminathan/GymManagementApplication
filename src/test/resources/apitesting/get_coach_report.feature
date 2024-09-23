Feature: Retrieve Coach Report

  Background: The user is logged in
    Given the endpoint is present at the Config file for reports
    And the user is authenticated with correct credentials for coach report
    And  response contains valid id token

  Scenario: Retrieve coach report with valid status code
    Given the API endpoint for coach report is "/reports"
    And the coach ID is "c43a7ab6-2a9c-4be4-9aac-883940ed7147"
    When I send a GET request to the coach report endpoint
    Then the response code should be 200
    And the response message for retrieve coach report should be "Retrieved reports for coach successfully."



  Scenario: Retrieve coach report without authorization token
    Given the API endpoint for coach report is "/reports"
    And the coach ID is "c43a7ab6-2a9c-4be4-9aac-883940ed7147"
    When I send a GET request to the coach report endpoint without authorization
    Then the responsestatus code should be 401
