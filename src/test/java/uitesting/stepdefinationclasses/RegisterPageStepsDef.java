package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RegisterPageStepsDef {

    private WebDriver driver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = BrowserFactoryDriver.getDriver();
        registerPage = new RegisterPage(driver);
        driver.get("http://52.221.11.140/login");
    }

    /*  @After
      public void tearDown() {
          if (driver != null) {
              driver.quit();
          }
      }*/
    @Given("I navigate to the registration page")
    public void iNavigateToTheRegistrationPage() {
        registerPage.clickCreateNewAccount();
    }

    //TC01
    @When("I check for the presence of the following elements:")
    public void iCheckForThePresenceOfTheFollowingElements(io.cucumber.datatable.DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String element = row.get("Element");
            String type = row.get("Type");

            switch (type) {
                case "Input", "Button", "Dropdown", "Link":
                    assertTrue(isElementPresent(element));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown element type: " + type);
            }
        }
    }

    private boolean isElementPresent(String elementName) {
        return switch (elementName) {
            case "Name field" -> registerPage.isNameFieldPresent();
            case "Email field" -> registerPage.isEmailFieldPresent();
            case "Password field" -> registerPage.isPasswordFieldPresent();
            case "Target" -> registerPage.isTargetDropdownPresent();
            case "Preferable activity" -> registerPage.isPreferableActivityDropdownPresent();
            case "Create account button" -> registerPage.isCreateAccountButtonPresent();
            case "Login link" -> registerPage.isLoginLinkPresent();
            default -> throw new IllegalArgumentException("Unknown element name: " + elementName);
        };
    }

    @Then("all elements should be visible and correctly placed")
    public void allElementsShouldBeVisibleAndCorrectlyPlaced() {
        assertTrue(registerPage.isNameFieldPresent());
        assertTrue(registerPage.isEmailFieldPresent());
        assertTrue(registerPage.isPasswordFieldPresent());
        assertTrue(registerPage.isTargetDropdownPresent());
        assertTrue(registerPage.isPreferableActivityDropdownPresent());
        assertTrue(registerPage.isCreateAccountButtonPresent());
        assertTrue(registerPage.isLoginLinkPresent());
    }

    //TC02
    @When("I enter {string} in the Name field")
    public void iEnterInTheNameField(String name) {
        registerPage.enterName(name);
    }

    @When("I enter {string} in the Email field")
    public void iEnterInTheEmailField(String email) {
        registerPage.enterEmail(email);
    }

    @When("I enter {string} in the Password field")
    public void iEnterInThePasswordField(String password) {
        registerPage.enterPassword(password);
    }

    @When("I select {string} from the Target dropdown")
    public void iSelectFromTheTargetDropdown(String option) {
        registerPage.selectTargetDropdown(option);
    }

    @When("I select {string} from the Preferable Activity dropdown")
    public void iSelectFromThePreferableActivityDropdown(String option) {
        registerPage.selectPreferableActivityDropdown(option);
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        registerPage.submitForm();
    }


    //TC04
    @When("I leave all fields empty and submit the form")
    public void leaveAllFieldsEmptyAndSubmitTheForm() {
        registerPage.enterName("");
        registerPage.enterEmail("");
        registerPage.enterPassword("");
        registerPage.submitForm();
    }

    @Then("I should see error messages for all mandatory fields")
    public void iShouldSeeErrorMessagesForAllMandatoryFields() {
        assertTrue(registerPage.verifyMandatoryFieldErrors());
    }


    //TC05
    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String Message) {
        assertTrue(registerPage.verifyEmailFieldErrors(Message));
    }

    //TC06
    @Then("I should see the error message for invalid password {string}")
    public void iShouldSeeTheErrorMessageForInvalidPassword(String Message) {
        assertTrue(registerPage.verifyPasswordFieldErrors(Message));
    }

    //TC07
    @When("I click on the login redirection link")
    public void iClickOnTheLoginRedirectionLinkButton() {
        registerPage.clickLoginLink();
    }

    @Then("I should be directed to the login page")
    public void iShouldBeDirectedToTheLoginPage() {
        registerPage.isLoginPage();
    }

    //TC08
    @Then("I should see the error message for name field")
    public void iShouldSeeTheErrorMessageForNameField() {
        assertTrue("The expected error message was not displayed.", registerPage.verifyNameFieldErrors1());
    }

    //TC09
    @Then("I should see the error message for invalid name")
    public void iShouldSeeTheErrorMessageForInvalidName() {
        assertTrue("The expected error message was not displayed.", registerPage.verifyNameFieldErrors2());
    }


    //TC10
    @When("I navigate through fields using the Tab key")
    public void iNavigateThroughFieldsUsingTheTabKey() {
        registerPage.nameField.click();
    }

    @Then("the fields should focus in a logical, sequential order")
    public void theFieldsShouldFocusInALogicalSequentialOrder() {
        registerPage.verifyTabOrder();

    }


    //TC11
    @And("I toggle the password visibility")
    public void iToggleThePasswordVisibility() {
        registerPage.togglePasswordVisibility();
    }
}
