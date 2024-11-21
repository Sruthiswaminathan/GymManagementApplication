package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;
import uitesting.apiurls.Config3;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class CoachRoleSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private WorkoutPage workoutPage;
    private CoachesPage coachesPage;
    private ProfilePage profilePage;
    private Coach coach;
    String email;
    String password;
    private Properties config3;
    private CoachProfileEdit coachProfileEdit;
    private static WebDriverWait wait;


    public CoachRoleSteps() {

        this.driver = BrowserFactoryDriver.getDriver();
        this.loginPage = new LoginPage(driver);
        this.coach = new Coach(driver);
        this.workoutPage = new WorkoutPage(driver);
        this.coachesPage = new CoachesPage(driver);
        this.profilePage = new ProfilePage(driver);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            config3 = Config3.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("Coach opened the Gym Management Application")
    public void coachOpenedTheGymManagementApplication() {
        loginPage.openPage();
    }

    @When("Coach collect the email and password from config3 properties file")
    public void coachCollectTheEmailAndPasswordFromConfig3PropertiesFile() {
        email = config3.getProperty("email");
        password = config3.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("Coach should click Login button")
    public void coachShouldClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("Coach should get a success message {string}")
    public void coachShouldGetASuccessMessage(String successMessage) {
        loginPage.successMessage();
    }

    @Then("redirected to the workouts list page")
    public void redirectedToTheWorkoutsListPage() {
        workoutPage.isDashboardPageDisplayed();
    }
//---------------finish workout--------------------------------------------------------------------------------------------
    @When("I should see the list of scheduled workouts with details such as Workout Name, Date and short summary")
    public void iShouldSeeTheListOfScheduledWorkoutsWithDetailsSuchAsWorkoutNameDateAndShortSummary() {
        //coach.listOfWorkouts();
        List<WebElement> workouts = driver.findElements(By.cssSelector(".workout-item"));
        for (WebElement workout : workouts) {
            String workoutName = workout.findElement(By.cssSelector(".workout-name")).getText();
            String date = workout.findElement(By.cssSelector(".workout-date")).getText();
            String summary = workout.findElement(By.cssSelector(".workout-summary")).getText();
            System.out.println("Workout Name: " + workoutName);
            System.out.println("Date: " + date);
            System.out.println("Summary: " + summary);
        }
    }

    @And("I click on the finish workout button")
    public void iClickOnTheFinishWorkoutButton() {
        coachesPage.scrollDown();
        coach.finishWorkout();
    }

    @Then("I should see Leave feedback button and Click on it")
    public void iShouldSeeLeaveFeedbackButtonAndClickOnIt() {
        coachesPage.scrollDown();
        WebElement feedbackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Leave Feedback'])[1]")));
        Assert.assertTrue(feedbackButton.isDisplayed());
        feedbackButton.click();
//        coachesPage.scrollDown();
//        coach.leaveFeedback();
    }


    @Then("I fill the feedback and click on submit button")
    public void iFillTheFeedbackAndClickOnSubmitButton() {
        coach.clickOnSubmit();
    }

    @Then("I should see the message Finished")
    public void iShouldSeeTheMessageFinished() {
        coachesPage.scrollDown();
        coach.finishMessage();
        loginPage.clickOnProfileButton();
        loginPage.clickLogoutButton();

    }

    @When("I should see the list of scheduled workouts")
    public void iShouldSeeTheListOfScheduledWorkouts() {
        List<WebElement> workouts = driver.findElements(By.cssSelector(".workout-item"));
     //   Assert.assertFalse(workouts.isEmpty(), "Workouts list should not be empty");

        for (WebElement workout : workouts) {
            String workoutName = workout.findElement(By.cssSelector(".workout-name")).getText();
            String date = workout.findElement(By.cssSelector(".workout-date")).getText();
            String summary = workout.findElement(By.cssSelector(".workout-summary")).getText();

            Assert.assertNotNull(workoutName, "Workout Name should not be null");
            Assert.assertNotNull(date, "Date should not be null");
            Assert.assertNotNull(summary, "Summary should not be null");

            System.out.println("Workout Name: " + workoutName);
            System.out.println("Date: " + date);
            System.out.println("Summary: " + summary);
        }

    }

    @And("I should see the workout i have to cancel and  click on cancel workout button")
    public void iShouldSeeTheWorkoutIHaveToCancelAndClickOnCancelWorkoutButton() {
       // coachesPage.scrollDown();
        coach.cancelWorkout();
    }

    @Then("I should see a window having option cancel workout and click on it")
    public void iShouldSeeAWindowHavingOptionCancelWorkoutAndClickOnIt() {
        coach.confirmCancelWorkout();
    }

    @Then("I should see the cancelled message")
    public void iShouldSeeTheCancelledMessage() {
        coach.cancelledMessage();
        loginPage.clickOnProfileButton();
        loginPage.clickLogoutButton();
    }
//---client feedback-----------------------------------------------------------------------------------------

    @When("I click on the profile")
    public void iClickOnTheProfile() {
      loginPage.clickOnProfileButton();
    }


    @And("I should see client feedback link and Click to view feedback")
    public void iShouldSeeClientFeedbackLinkAndClickToViewFeedback() {
        coach.clickOnClientFeedback();
    }

    @Then("I should see the feedbacks")
    public void iShouldSeeTheFeedbacks() {
        coach.visibleFeedback();

    }
    @And("I should click on the Logout button")
    public void iShouldClickOnTheLogoutButton() {
        loginPage.clickLogoutButton();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String feedbackSuccessMessage) {
        coach.successMessageOfFeedback();
    }

    @Then("I clicked on logout button")
    public void iClickedOnLogoutButton() {
        loginPage.clickLogoutButton();
    }
}
