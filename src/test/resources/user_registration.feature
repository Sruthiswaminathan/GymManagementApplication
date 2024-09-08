Feature: User Registration

  Scenario Outline: Attempt to register a new user
    Given I am a user
    When I send a POST request to the "/register" endpoint with the following details:
      | name               | <name>               |
      | email              | <email>              |
      | password           | <password>           |
      | target             | <target>             |
      | preferableActivity | <preferableActivity> |
    Then I should receive a <status> status
    And the response should contain a message "<message>"

    Examples:
      | name        | email                      | password                | target  | preferableActivity | status | message                                              |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | HHpnemajhjwnhh@2001       | Fitness | yoga               | 201    | User registered successfully.                        |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | HHpnemajhjwnhh@2001       | Fitness | yoga               | 400    | User with email <email> already exists               |
      | Hemavathi V | Hemavathbibij1inhh@gmail     | HHpnemajhjwnhh@2001       | Fitness | yoga               | 400    | Email must contain a domain with a '.' symbol.      |
      | Hemavathi V |                            | HHpnemajhjwnhh@2001       | Fitness | yoga               | 400    | Email must contain an '@' symbol.                    |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | short                   | Fitness | yoga               | 400    | Password must be between 8 and 20 characters.        |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | pnemajjwhh2001            | Fitness | yoga               | 400    | Password must contain at least one uppercase letter. |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | HHpnemajjwhh@             | Fitness | yoga               | 400    | Password must contain at least one digit.            |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | pnemajjwhh@2001           | Fitness | yoga               | 400    | Password must contain at least one uppercase letter. |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | HH@2001                 | Fitness | yoga               | 400    | Password must be between 8 and 20 characters.        |
      | Hemavathi V | Hemavathbibij1inhh@gmail.com | John@123456789012347856 | Fitness | yoga               | 400    | Password must be between 8 and 20 characters.        |
