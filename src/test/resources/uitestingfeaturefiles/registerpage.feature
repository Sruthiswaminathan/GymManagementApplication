Feature: User Registration Form Validation

  # TC01 - Verify UI elements on the registration page
  Scenario: Verify UI elements on the registration page
    Given I navigate to the registration page
    When I check for the presence of the following elements:
      | Element               | Type     |
      | Name field            | Input    |
      | Email field           | Input    |
      | Password field        | Input    |
      | Target                | Dropdown |
      | Preferable activity   | Dropdown |
      | Create account button | Button   |
      | Login link            | Link     |
    Then all elements should be visible and correctly placed

  #TC02 - Validate successful registration with correct data
  Scenario: Validate successful registration with correct data
    Given I navigate to the registration page
    When I enter "John Doe" in the Name field
    And I enter "johdojkenjkf@example.com" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form

  #TC03 - Validate with already registration with correct data
  Scenario: Validate with already registration with correct data
    Given I navigate to the registration page
    When I enter "John Doe" in the Name field
    And I enter "johdomebhbjk32r2nmhhh@example.com" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form

  # TC04 - Test mandatory fields for empty input
  Scenario: Test mandatory fields for empty input
    Given I navigate to the registration page
    When I leave all fields empty and submit the form
    Then I should see error messages for all mandatory fields

  # TC05 - Validate email format in Email field
  Scenario: Validate email format in Email field
    Given I navigate to the registration page
    When I enter "John Doe" in the Name field
    And I enter "johdomebnm@example" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form
    Then I should see the error message "Invalid email address. Please ensure it follows the format: username@domain.com"

   # TC06 - Password minimum standards check
  Scenario Outline: Validate password minimum standards
    Given I navigate to the registration page
    When I enter "John Doe" in the Name field
    And I enter "johdomebnm@example.com" in the Email field
    When I enter "<password>" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form
    Then I should see the error message for invalid password "<expected_error>"
    Examples:
      | password  | expected_error                                                                                                                        |
      |           | Password is required. Please enter your password to continue.                                                                         |
      | password  | Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters |
      | PASSWORD  | Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters |
      | Password  | Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters |
      | Password1 | Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters |
      | Pass1@    | Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters |

  # TC07 - Functional check of login redirection link/button
  Scenario: Functional check of login redirection link/button
    Given I navigate to the registration page
    When I click on the login redirection link
    Then I should be directed to the login page


  # TC08 - Validate name format first character caps
  Scenario: Validate name format first character caps
    Given I navigate to the registration page
    When I enter "john" in the Name field
    And I enter "johdomebemnnm@example.com" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form
    Then I should see the error message for name field

    # TC09 - Validate name format
  Scenario Outline: Validate name format
    Given I navigate to the registration page
    When I enter "<name>" in the Name field
    And I enter "johdomebemnnm@example.com" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I submit the form
    Then I should see the error message for invalid name
    Examples:
      | name      |
      | gdhjwq76  |
      | nbvn@&(@! |

  #TC10 - Ensure field tab order is sequential
  Scenario: Ensure field tab order is sequential
    Given I navigate to the registration page
    When I navigate through fields using the Tab key
    Then the fields should focus in a logical, sequential order


  #TC11 - Ensure field tab order is sequential
  Scenario: Password visibility toggle
    Given I navigate to the registration page
    When I enter "John Doe" in the Name field
    And I enter "johdomebhddhdnneh@example.com" in the Email field
    And I enter "Test@1234" in the Password field
    And I select "Lose weight" from the Target dropdown
    And I select "climbing" from the Preferable Activity dropdown
    And I toggle the password visibility


