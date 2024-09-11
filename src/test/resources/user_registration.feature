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
      | fullName | email          | password                | target       | preferableActivity | status | message                                                                          |
      | admin    | admin09@epam.com | Admin09@123               | loose weight | yoga               | 201    | User registered successfully.                                                    |
      | admin    | admin@epam.com | Admin@123               | loose weight | yoga               | 400    | User account already exists (Service: CognitoIdentityProvider, Status Code: 400, |
      | admin    | admin@epam     | HHpnemajhjwnhhj@2001    | loose weight | yoga               | 400    | Email must contain a domain with a '.' symbol.                                   |
      | admin    |                | HHpnemajhjwnhhj@2001    | loose weight | yoga               | 400    | Email cannot be null.                                                            |
      | admin    | admin@epam.com | short                   | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
      | admin    | admin@epam.com | pnemajjwhhj2001         | loose weight | yoga               | 400    | Password must contain at least one uppercase letter.                             |
      | admin    | admin@epam.com | HHpnemajjwhhj@          | loose weight | yoga               | 400    | Password must contain at least one digit.                                        |
      | admin    | admin@epam.com | pnemajjwhhj@2001        | loose weight | yoga               | 400    | Password must contain at least one uppercase letter.                             |
      | admin    | admin@epam.com | HH@2001                 | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
      | admin    | admin@epam.com | John@123456789012347856 | loose weight | yoga               | 400    | Password must be between 8 and 20 characters.                                    |
