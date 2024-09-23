package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = BrowserFactoryDriver.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("I open the Gym Management Application")
    public void i_open_the_gym_management_application() {
        loginPage.openPage();
    }

    @When("I enter {string}")
    public void i_enter(String email) {
        loginPage.enterEmail(email);
    }

    @Then("I enter the {string}")
    public void i_enter_the(String password) {
        loginPage.enterPassword(password);
    }

    @Then("I click Login button")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the {string}")
    public void i_should_see_the(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @When("I enter email {string}")
    public void iEnterEmail(String emailId) {
        loginPage.enterEmail(emailId);
    }

    @Then("I enter password {string}")
    public void iEnterPassword(String validPassword) {
        loginPage.enterPassword(validPassword);
    }


    @Then("^I should get \"([^\"]*)\"$")
    public void i_should_get(String expectedMessage) {
        String actualMessage = loginPage.successMessage();
        assertEquals(expectedMessage, actualMessage);
    }



    @And("I should get an {string}")
    public void iShouldGetAnErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage;
        if (expectedErrorMessage.contains("Invalid email address")) {
            actualErrorMessage = loginPage.inValidEmailErrorMessage();
        } else if (expectedErrorMessage.contains("Your password must be 8-16 characters long")) {
            actualErrorMessage = loginPage.inValidPasswordErrorMessage();
        } else if (expectedErrorMessage.contains("Email address is required")) {
            actualErrorMessage = loginPage.emptyEmailErrorMessage();
        } else if (expectedErrorMessage.contains("Password is required")) {
            actualErrorMessage = loginPage.emptyPasswordErrorMessage();
        } else {
            actualErrorMessage = loginPage.errorMessage();
        }
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @And("I should click on profile button")
    public void iShouldClickOnProfileButton() {

        loginPage.clickOnProfileButton();
    }

    @And("I click Logout button")
    public void iClickLogoutButton() {
        loginPage.clickLogoutButton();
    }
    @Then("I should receive {string}")
    public void iShouldReceive(String logoutMessage) {
        String actualLogoutMessage = loginPage.logoutMessage();
        assertEquals(logoutMessage,actualLogoutMessage);

    }


    @Then("close the tab")
    public void closeTheTab() {
        BrowserFactoryDriver.cleanUp();
        this.driver=BrowserFactoryDriver.getDriver();
        this.loginPage=new LoginPage(driver);

    }

}