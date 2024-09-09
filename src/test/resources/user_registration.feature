Feature: User Registration

  Scenario Outline: Attempt to register a new user
    Given I am a user
    When I send a POST request to the "/register" endpoint with the following details:
      | fullName           | <fullName>           |
      | email              | <email>              |
      | password           | <password>           |
      | target             | <target>             |
      | preferableActivity | <preferableActivity> |
    Then I should receive a <status> status
    And the response should contain a message "<message>"

    Examples:
      | fullName    | email                         | password                | target       | preferableActivity | status | message                                                                          |
      | Hemavathi   | Hehhh@gmail.com               | HHpmawnj@20             | loose weight | yoga               | 201    | User registered successfully.                                                    |
      | Hemavathi V | Hemavathbbbij1inhhh@gmail.com | HHpnemajhjwnj@2001      | loose weight | yoga               | 400    | User account already exists (Service: CognitoIdentityProvider, Status Code: 400, |
      | Hemavathi V | Hemavathbibij1inhhh@gmail     | HHpnemajhjwnhhj@2001    | loose weight | yoga               | 400    | Email must contain a domain with a '.' symbol.                                   |
      | Hemavathi V |                               | HHpnemajhjwnhhj@2001    | loose weight | yoga               | 400    | Email cannot be null.                                                            |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | short                   | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | pnemajjwhhj2001         | loose weight | yoga               | 400    | Password must contain at least one uppercase letter.                             |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | HHpnemajjwhhj@          | loose weight | yoga               | 400    | Password must contain at least one digit.                                        |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | pnemajjwhhj@2001        | loose weight | yoga               | 400    | Password must contain at least one uppercase letter.                             |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | HH@2001                 | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
      | Hemavathi V | Hemavathbibij1inhhh@gmail.com | John@123456789012347856 | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
