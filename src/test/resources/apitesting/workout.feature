Feature: Crud Operation Workout Management

  Background: The user is logged in
    Given the url is collected from Config
    And the user is authenticated with correct credentials with email and password
    And the response should include correct valid id token


  Scenario: Successfully create a new workout with valid Bearer token
    Given the user has the workout details
      | name     | Daily Caxrdiho       |
      | duration | PT30M           |
      | date     | 2024-09-04T08:00:00 |
      | summary  | Morning workout     |
      | coachId  | coach12368           |
      | clientId | client48756           |
    When the user sends a POST request to create a new workout with BearerToken
    Then For post method the response should return a status code 201
    And the response body should contain message "Workout created successfully." and an id for POST

  Scenario Outline: create a new workout with different values
    Given the user has the workout details
      | name     | <name>     |
      | duration | <duration> |
      | date     | <date>     |
      | summary  | <summary>  |
      | coachId  | <coachId>   |
      | clientId | <clientId> |
    When the user sends a POST request to create a new workout with BearerToken
    Then For post method the response should return a status code 400
    And the response body should contain message "<message>" and an id for POST
    Examples:
      | name          | duration    | date       | summary           | coachId | clientId | message                           |
      |               | 45 minutes  | 2024-09-10 | Morning cardio    | 123     | 456      | Missing or empty 'name' field     |
      | Running       | -30 minutes |            | Morning run       | 123     | 456      | Missing or empty 'date' field     |
      | Yoga          | 30 minutes  | 2024-02-30 |                   | 123     | 456      | Missing or empty 'summary' field  |
      | Weightlifting | 60 minutes  | 2024-09-10 | Strength training |         | 456      | Missing or empty 'coachId' field  |
      | Weightlifting | 60 minutes  | 2024-09-10 | Strength training | 564     |          | Missing or empty 'clientId' field |

  Scenario: Successfully update workout status with valid Bearer token
    Given the user has a workout id "864f2314-82f5-4fcc-8a7c-8fa021b842f1" and a new status "FINISHED"
    When the user sends a PATCH request to update the workout status with BearerToken
    Then the response should return a status code 200 for PATCH
    And the response body should contain message "Workout marked as FINISHED" for PATCH

  Scenario: update workout status with invalid workout id
    Given the user has a workout id "bjhsbjdbej" and a new status "FINISHED"
    When the user sends a PATCH request to update the workout status with BearerToken
    Then the response should return a status code 404 for PATCH
    And the response body should contain message "Workout not found." for PATCH

