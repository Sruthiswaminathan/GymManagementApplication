package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import uitesting.apiurls.Config3;
import uitesting.apiurls.Config4;

import java.time.Duration;
import java.util.Properties;

public class CoachRoleProfileEditSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private WorkoutPage workoutPage;
    private CoachesPage coachesPage;
    private CoachProfileEdit coachProfileEdit;
    String email;
    String password;
    private Properties config4;
    private static WebDriverWait wait;



    public CoachRoleProfileEditSteps() {

        this.driver = BrowserFactoryDriver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.loginPage = new LoginPage(driver);
        this.coachProfileEdit = new CoachProfileEdit(driver);
        this.workoutPage = new WorkoutPage(driver);
        this.coachesPage = new CoachesPage(driver);


        try {
            config4 = Config4.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("Coach open the Gym Management Application")
    public void coach_open_the_gym_management_application() {
        loginPage.openPage();
    }

    @When("Coach collect the email and password from config4 properties file")
    public void coach_collect_the_email_and_password_from_config4_properties_file() {
        email = config4.getProperty("email");
        password = config4.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("Coach click Login button")
    public void coach_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("Coach should get success message {string}")
    public void coach_should_get_success_message(String string) {
        loginPage.successMessage();
    }

    @Then("redirected to the workout list page")
    public void redirected_to_the_workout_list_page() {
        workoutPage.isDashboardPageDisplayed();
    }

    @When("I clicked on the profile icon")
    public void iClickedOnTheProfileIcon() {
        loginPage.clickOnProfileButton();
    }

    @Then("I should click on the edit profile button")
    public void iShouldClickOnTheEditProfileButton() {
        coachProfileEdit.clickOnEditProfile();
    }
    @Then("I Should click on the My Account edit button")
    public void iShouldClickOnTheMyAccountEditButton() {
        coachProfileEdit.clickOnEditProfile();
    }

    @Then("I should fill the form")
    public void iShouldFillTheForm() {
        coachProfileEdit.fillProfileForm();
        coachProfileEdit.uploadFile();


    }
    @And("click on the update button")
    public void clickOnTheUpdateButton() {
        coachesPage.scrollDown();
        coachProfileEdit.clickOnSaveChanges();
    }


    @Then("I should see the message profile updated.")
    public void iShouldSeeTheMessageProfileUpdated() {
        coachProfileEdit.successfullyUpdateMessage();
    }

    @And("click logout button")
    public void clickLogoutButton() {
        loginPage.clickLogoutButton();
    }


}

//
//    @Given("Coach open the Gym Management Application")
//    public void coachOpenTheGymManagementApplication() {
//        loginPage.openPage();
//    }
//
//    @When("Coach collect the email and password from config4 properties file")
//    public void coachCollectTheEmailAndPasswordFromConfig4PropertiesFile() {
//        email = config4.getProperty("email");
//        password = config4.getProperty("password");
//        loginPage.enterEmail(email);
//        loginPage.enterPassword(password);
//    }
//
//    @And("Coach click Login button")
//    public void coachClickLoginButton() {
//        loginPage.clickLoginButton();
//    }
//
//    @Then("Coach should get success message {string}")
//    public void coachShouldGetSuccessMessage(String successMessage) {
//        loginPage.successMessage();
//    }
//
//    @Then("redirected to the workout list page")
//    public void redirectedToTheWorkoutListPage() {
//        workoutPage.isDashboardPageDisplayed();
//    }




