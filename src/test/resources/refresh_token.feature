Feature: Refresh Tokens with Various Combinations

  Background: The user is logged in
    Given the API endpoint is present
    And the user is authenticated with below email and password
    And the response stores a valid id token and access token

  Scenario Outline: Refresh tokens after successful login
    Given I have successfully logged in
    When I use the refresh token to request new tokens
    Then I should receive a message as "<message>"
    And the response should contain "idToken" and "accessToken" that are not empty

    Examples:
      | message |
      | Tokens refreshed successfully.    |

