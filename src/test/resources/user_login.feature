Feature: User Login

  Scenario Outline: Login with valid credentials
    Given I am the user trying to login
    When the user provides the login details with email "<email>" and password "<password>"
    Then I should receive a <status> statuscode
    And the response should contain tokens idToken, accessToken, refreshToken

    Examples:
      | email                       | password           | status |
      | Hehhh@gmail.com | HHpmawnj@20    | 200    |

  Scenario Outline: Login with various email and password combinations
    Given I am the user trying to login
    When the user provides the login details with below details:
      | email                     | password        | status | errorMessage                         |
      | <email>                   | <password>      | <status> | <errorMessage>                       |
    Then I should receive a <status> statuscode for invalid
    And the response should contain the error message "<errorMessage>"

    Examples:
      | email                     | password        | status | errorMessage                         |
      | [empty]                   | Sweetsyhnw@2001 | 400    | Email must contain an '@' symbol.                |
      | Hemavathbibij1inhhh@gmail.com | [empty]         | 400    | Failed to authenticate user. Invalid credentials. |
      | Hemavathbibij1inhhh@gmail     | HHpnemajhjwnhhj@2001 | 400    | Email must contain a domain with a '.' symbol. |
      | Hemavathbibij1inhhh@gmail.com  | wrongPassword   | 400    | Failed to authenticate user. Invalid credentials.     |
      | Hemavathbiij1inh@gmail.com     | Sweetsyhnw@2001 | 400    | Failed to authenticate user. Invalid credentials.                |
