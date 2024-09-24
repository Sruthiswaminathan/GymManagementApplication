package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.WorkoutPage;
import uitesting.apiurls.Config1;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class WorkoutPageStepDefs {
    private WebDriver driver;
    private LoginPage loginPage;
    private WorkoutPage workoutPage;
    String email;
    String password;
    private Properties config1;

    public WorkoutPageStepDefs() {

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
        this.workoutPage = new WorkoutPage(driver);
    }
    @Given("I open Gym Management Application")
    public void iOpenGymManagementApplication() {
        loginPage.openPage();
    }
    @When("Collects the email ad password from Config1 file")
    public void collectsTheEmailAdPasswordFromConfigFile() {
        email = config1.getProperty("email");
        password = config1.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
    @And("I click Login")
    public void iClickLogin() {
        loginPage.clickLoginButton();
    }
    @Then("I should see the dashboard page")
    public void iShouldSeeTheDashboardPage() {
        assertTrue(workoutPage.isDashboardPageDisplayed());
    }

    //TC01
    @When("the user navigates to the Workout page")
    public void theUserNavigatesToTheWorkoutPage() {
        assertTrue(workoutPage.goToSection());
    }
    @When("the user views the scheduled workout for {string} on date {string}")
    public void theUserViewsTheScheduledWorkoutForOnDate(String workoutType, String expectedDate) {
        boolean isDisplayed = workoutPage.viewScheduledWorkout(workoutType, expectedDate);
        assert isDisplayed : "The scheduled workout for " + workoutType + " on " + expectedDate + " is not displayed.";
    }
    @And("the user clicks on the Cancel Workout button")
    public void theUserClicksOnTheButton() {
        workoutPage.cancelScheduledWorkout();
    }

    //TC02
    @And("the user clicks on the Resume Workout button")
    public void theUserClicksOnTheResumeWorkoutButton() {
        workoutPage.resumeScheduledWorkout();
    }

    //TC03
    @And("the user clicks on the Finish Workout button")
    public void theUserClicksOnTheFinishWorkoutButton() {
        workoutPage.finishScheduledWorkout();
    }

    //TC04
    @When("the user views the finished workout for {string} on date {string}")
    public void theUserViewsTheFinishedWorkoutForOnDate(String workoutType, String expectedDate) {
        boolean isDisplayed = workoutPage.viewFinishedWorkout(workoutType, expectedDate);
        assert isDisplayed : "The finished workout for " + workoutType + " on " + expectedDate + " is not displayed.";
    }

    @And("the user leaves feedback with rating {string} and comments {string}")
    public void theUserClicksOnTheLeaveFeedbackWithRatingAndComments(String rating,String comments) {
        workoutPage.leaveFeedback(rating, comments);
    }

    @Then("I should get  the error as {string}")
    public void iShouldGetTheErrorAs(String InvalidErrorMessage) {
        String actualInvalidErrorMessage = workoutPage.invalidErrorMessage();
        assertEquals(InvalidErrorMessage,actualInvalidErrorMessage);
    }
}
