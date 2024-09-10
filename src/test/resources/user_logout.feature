Feature: User Logout Functionality

  Background: The user is logged in
    Given the API endpoint is present at "baseURI"
    And the user is authenticated with email and password
      | email    | admin@epam.com    |
      | password | Admin@123 |
    And the response contains a valid id token and access token

  Scenario Outline: User attempts to log out with different token conditions
    When I send a POST request to the logout endpoint
    Then I should receive a <statusCode> status code for logout successful
    And the response should contain the message "<message>"
    Examples:
      | statusCode | message |
    |200         |User logged out successfully.|
