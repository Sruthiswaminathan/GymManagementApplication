Feature: Login to EnergyXGain

  Background:
    Given I open the Gym Management Application

  @InCorrect
  Scenario Outline: Logging to Gym Management Application with given credentials
    When I enter "<Email>"
    Then I enter the "<password>"
    And I click Login button
    And I should see the <ErrorMessage>

    Examples:
      | Email              | password  | ErrorMessage                     |
      | sachin..@gmail.com | Sachu@123 | "Incorrect username or password" |

  @InValid
  Scenario Outline: Logging to Gym Management Application with given credentials
    When I enter "<Email>"
    Then I enter the "<password>"
    And I click Login button
    And I should get an <ErrorMessage>

    Examples:
      | Email                     | password   | ErrorMessage                                                                                                                            |
      | sachintendulkar.com       | Sachuu@123 | "Invalid email address. Please ensure it follows the format: username@domain.com"                                                       |
      | sachintendulkar@gmail     | Sachu@123  | "Invalid email address. Please ensure it follows the format: username@domain.com"                                                       |
      | sachintendulkar@gmail.com | sachin@123 | "Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters" |
      | sachintendulkar@gmail.com | sachin123  | "Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters" |
      | sachintendulkar@gmail.com | sachin     | "Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters" |
      | sachintendulkar@gmail.com | 56789023   | "Your password must be 8-16 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters" |

  @Empty
  Scenario Outline: Logging to Gym Management Application with given credentials
    When I enter "<Email>"
    Then I enter the "<password>"
    And I click Login button
    And I should get an <ErrorMessage>

    Examples:
      | Email                     | password  | ErrorMessage                                                      |
      |                           | Sachu@123 | "Email address is required. Please enter your email to continue." |
      | sachintendulkar@gmail.com |           | "Password is required. Please enter your password to continue."   |


  @validatelogout
  Scenario Outline: Logging to Gym Management Application with given credentials
    When I enter email "<Email>"
    Then I enter password "<password>"
    And I click Login button


    And I should click on profile button
    And I click Logout button
    Then I should receive "Successfully Logged Out"
    Then close the tab

    Examples:
      | Email                | password  |
      | Testing8@example.com | Test@1234 |


  @valid
  Scenario Outline: Logging to Gym Management Application with given credentials
    When I enter email "<Email>"
    Then I enter password "<password>"
    And I click Login button
    Then I should get "Successfully Logged in"

    Examples:
      | Email                | password  |
      | Testing8@example.com | Test@1234 |

