Feature: User Login

  Scenario Outline: Login with valid credentials
    Given I am the user trying to login
    When the user provides the login details with email and password
    Then I should receive a <status> status code
    And the response should contain tokens idToken, accessToken, refreshToken and "<message>"

    Examples:
      | status | message                      |
      | 200    | User logged in successfully. |

  Scenario Outline: Login with various email and password combinations
    Given I am the user trying to login
    When the user provides the login details with below details:
      | email   | password   | status   | errorMessage   |
      | <email> | <password> | <status> | <errorMessage> |
    Then I should receive a <status> status code for invalid
    And the response should contain the error message "<errorMessage>"

    Examples:
      | email          | password        | status | errorMessage                                      |
      | [empty]        | Admin@123       | 400    | Email must contain an '@' symbol.                 |
      | admin@epam.com | [empty]         | 400    | Failed to authenticate user. Invalid credentials. |
      | admin@epam     | Admin@123       | 400    | Email must contain a domain with a '.' symbol.    |
      | admin@epam.com | wrongPassword   | 400    | Failed to authenticate user. Invalid credentials. |
      | admin@epam.com | Sweetsyhnw@2001 | 400    | Failed to authenticate user. Invalid credentials. |
