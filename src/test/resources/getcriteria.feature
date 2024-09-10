Feature: Criteria Management

  Background: The user is logged in
    Given the API endpoint is present at Config
    And the user is authenticated correct credintials with email and password
      | email    | admin@epam.com    |
      | password | Admin@123 |
    And the response stores the valid id token

  Scenario: Perform CRUD operations on criteria
    Given I have a valid email "admin@epam.com" for criteria
    When I send a get request
    Then I should receive a statuscode as 200 for get request