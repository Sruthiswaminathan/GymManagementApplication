Feature: Update User's Profile Information

  Background: The user is logged in
    Given the url is fetched from Config file
    And the user is authenticated with email and password details
    And checking the response contains a correct valid id token

  Scenario Outline: Successfully update profile information
    Given the user has a valid token and is authenticated "/profile"
    When the user sends a PUT request to the profile endpoint with the updated "<fullName>", "<target>", "<preferableActivity>"
    Then the response status should be 200
    And the success message should be "User info updated successfully"
    And check the name is updated or not

    Examples:
      | fullName   | target       | preferableActivity |
      | Jon Den   | gain weight  | Running            |
      | Jae Shth | loose weight | Weightlifting      |
