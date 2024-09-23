Feature: Update User's Profile Information

  Background: The user is logged in
    Given the url is fetched from Config file
    And the user is authenticated with email and password details
    And checking the response contains a correct valid id token

  Scenario Outline: Update profile information for different scenarios
    Given the user has a valid token and is authenticated "/profile"
    When the user sends a PUT request to the profile endpoint with the updated "<fullName>", "<target>", "<preferableActivity>"
    Then the response status should be <status>
    And the success message should be "<message>"
    And check the name is updated or not

    Examples:
      | fullName | target       | preferableActivity | status | message                                              |
      | Jon Dennnn  | gain weight  | Running            | 200    | User info updated successfully.                      |
      | Jae Shjhthn | loose weight | Weightlifting      | 200    | User info updated successfully.                      |
      |          | loose weight | Weightlifting      | 400    | Full name cannot be empty, while updating.           |
      | Jae Shth |              | Weightlifting      | 400    | Invalid target value, while updating.                |
      | Jae Shth | loose weight |                    | 400    | Preferable activity cannot be empty, while updating. |
