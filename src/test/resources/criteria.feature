Feature: Criteria Management

  Background: The user is logged in
    Given the API endpoint is present at Config
    And the user is authenticated correct credentials with email and password
    And the response stores the valid id token

  Scenario Outline: Perform fetch the criteria using email
    Given I have a valid email "<email>" for criteria
    When I send a get request
    Then I should receive a status code as <status> for get request
    And the response body should contain message as "<message>" for get
    Examples:
      | email            | status | message                          |
      | adminqa@epam.com | 200    | Retrieved criteria successfully. |
      |                  | 400    | Email cannot be empty            |

  Scenario Outline: Successfully update criteria with valid Bearer token
    Given the user has a valid email ,role and new role to be updated
    When the user sends a PUT request to endpoint with BearerToken and the request body
      | email | <email> |
      | role  | <role>  |
    Then the response should return a status code <status>
    And the response body should contain message as "<message>" for put
    Examples:
      | email            | role  | status | message                                        |
      | adminqa@epam.com | ADMIN | 200    | Criteria updated successfully.                 |
      | abc@epam         | ADMIN | 400    | Email must contain a domain with a '.' symbol. |


  Scenario Outline: Successfully add criteria with valid Bearer token
    Given the user has a valid email "<email>" and role "<role>" to add
    When the user sends a POST request to add criteria with BearerToken
    Then the response should return a status code <status> for POST
    And the response body should contain message "<message>" for POST
    Examples:
      | email            | role  | status | message                                        |
      | admin89@epam.com | ADMIN | 200    | Criteria updated successfully.                 |
      | abc@epam         | ADMIN | 400    | Email must contain a domain with a '.' symbol. |

  Scenario: Successfully remove criteria with valid Bearer token
    Given the user has a valid email "admin89@epam.com" to remove and there is "admin90@epam.com" admin exists
    When the user sends a DELETE request to remove criteria with email "admin90@epam.com"
    Then the response should return a status code 200 for DELETE
    And the response body should contain message "Criteria removed successfully." for DELETE
