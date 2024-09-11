Feature: User Logout Functionality

  Background: The user is logged in
    Given the Url is present in Config file
    And the user is authenticated with email and password
    And the response contains a valid id token and access token

  Scenario Outline: User attempts to log out with different token conditions
    When I send a POST request to the logout endpoint
    Then I should receive a <statusCode> status code for logout successful
    And the response should contain the message "<message>"
    Examples:
      | statusCode | message |
    |200         |User logged out successfully.|
