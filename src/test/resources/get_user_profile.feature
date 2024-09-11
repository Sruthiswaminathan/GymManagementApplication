Feature: Retrieve User's Profile Information

  Background: The user is logged in
    Given the API endpoint is present at Config file
    When the user is authenticated correct credentials with above email and password
    And the response contains the valid id token


  Scenario Outline: Retrieve user's profile information with valid status code
    Given the API endpoint for profile is "/profile"
    When I send a GET request to the profile endpoint
    Then the response status code should be 200
    And the response message should be "<message>"
    And the response should include fullName, email, target, preferableActivity
    Examples:
      | message |
      |  User data retrieved successfully.|
