package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProfilePage;
import uitesting.apiurls.Config1;

import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ProfilePageStepDefs {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    String email;
    String password;
    private Properties config1;

    public ProfilePageStepDefs() {
        try {
            config1 = Config1.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Before
    public void setUp() {
        this.driver = BrowserFactoryDriver.getDriver();
        this.loginPage = new LoginPage(driver);
        this.profilePage = new ProfilePage(driver);
    }
    @Given("I open the Gymmanagement Application")
    public void iOpenTheGymmanagementApplication() {
        loginPage.openPage();
    }
    @When("Collects the email ad password from Config1 properties file")
    public void collectsTheEmailAdPasswordFromConfig1PropertiesFile() {
        email = config1.getProperty("email");
        password = config1.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
    @And("I click the Login")
    public void iClickTheLogin() {
        loginPage.clickLoginButton();
    }
    @Then("I should be in the workout page")
    public void iShouldBeInTheWorkoutPage() {
        assertTrue(profilePage.isDashboardPageDisplayed());
    }

    //TC01
    @Given("I click on the profile icon")
    public void iClickOnTheProfileIcon() {
        profilePage.clickProfileIcon();
    }
    @And("I click on My Accounts")
    public void iClickOnMyAccounts() {
        profilePage.clickMyAccounts();
    }
    @When("I update the name with {string}")
    public void iUpdateTheNameWith(String name) {
        profilePage.enterName(name);
    }
    @And("I update the preferred activity to {string}")
    public void iUpdateThePreferredActivityTo(String activity) {
        profilePage.selectPreferableActivityDropdown(activity);
    }
    @And("I update the target with {string}")
    public void iUpdateTheTargetWith(String target) {
        profilePage.selectTargetDropdown(target);
    }
    @And("click on save changes")
    public void clickOnSaveChanges() {
        profilePage.clickOnSaveChanges();
    }
    @Then("I should get message as {string}")
    public void iShouldGetMessageAs(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased to 20 seconds
        wait.until(ExpectedConditions.visibilityOf(profilePage.getSuccessMessageElement()));
        String updatedMessage = profilePage.successMessage();
        assertEquals(message,updatedMessage);
    }
    //TC02
    @Then("I should get error message as {string}")
    public void iShouldGetErrorMessageAs(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased to 20 seconds
        wait.until(ExpectedConditions.visibilityOf(profilePage.getErrorMessageElement()));
        String updatedMessage = profilePage.errorMessage();
        assertEquals(message,updatedMessage);
    }
    //TC03
    @Then("I should get error message for empty name feild as {string}")
    public void iShouldGetErrorMessageForEmptyNameFeildAs(String message) {
        assertTrue(message,profilePage.errorMessageForEmptyName());
    }

    @Then("I should get error message for name without caps letter as {string}")
    public void iShouldGetErrorMessageForNameWithoutCapsLetterAs(String message) {
        assertTrue(message,profilePage.errorMessageForWithoutCapsName());
    }

    @Then("I should get error message for name with special character as {string}")
    public void iShouldGetErrorMessageForNameWithSpecialCharacterAs(String message) {
        assertTrue(message,profilePage.errorMessageForWithSpecialCharacters());
    }

    @And("click on logout button")
    public void iClickLogoutButton() {
        profilePage.clickLogoutButton();
    }
    @Then("I should receive  a success message for logout as {string}")
    public void iShouldReceive(String logoutMessage) {
        String actualLogoutMessage = profilePage.logoutMessage();
        assertEquals(logoutMessage,actualLogoutMessage);

    }

}
