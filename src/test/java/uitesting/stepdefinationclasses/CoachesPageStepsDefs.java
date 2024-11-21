package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CoachesPage;
import pages.LoginPage;


import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoachesPageStepsDefs {

    private WebDriver driver;
    private LoginPage loginPage;
    private CoachesPage coachesPage;

    public CoachesPageStepsDefs() {
        this.driver = BrowserFactoryDriver.getDriver();
        this.loginPage = new LoginPage(driver);
        this.coachesPage = new CoachesPage(driver);
    }

    @Given("I open Gym Management App")
    public void iOpenGymManagementApplication() {
        loginPage.openPage();
    }

    @When("I enter {string} and {string}")
    public void iEnterCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("I click on Login button")
    public void iClickLogin() {
        loginPage.clickLoginButton();
    }

    @Then("I should land on the dashboard page")
    public void iShouldSeeTheDashboardPage() {
        assertTrue(coachesPage.isDashboardPageDisplayed());
    }

    @Given("User logged in as Client")
    public void userLoggedInAsClient() {
        assertTrue(coachesPage.isDashboardPageDisplayed());
    }

    @When("From the dashboard locate and click on the coach selection page")
    public void fromTheDashboardLocateAndClickOnTheCoachSelectionPage() {
        coachesPage.clickCoachesLink();
    }


    @Then("A list of available coaches each with a profile card, should be displayed")
    public void aListOfAvailableCoachesEachWithAProfileCardShouldBeDisplayed() {
        //coachesPage.availableCoachesList();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        WebElement coachProfileCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='MuiCardMedia-root MuiCardMedia-media MuiCardMedia-img css-146e5oc' and @src='/assets/Avatar-o6SOcAyW.png']")));
        assertTrue(coachProfileCard.isDisplayed());
    }

    @Then("For each coach profile card check for the following details:")
    public void forEachCoachProfileCardCheckForTheFollowingDetails(DataTable dataTable) {
        dataTable.asList().forEach(detail -> {
            switch (detail) {
                case "Name":
                    coachesPage.nameOfCoaches();
                    break;
                case "Short Summary":
                    coachesPage.shortSummary();
                    break;
                case "Expertise/Specialization":
                    coachesPage.expertiseOrSpecialization();
                    break;
                case "Client Reviews/Ratings":
                    coachesPage.clientReviewsOrRatings();
                    break;
                case "Workout button":
                    coachesPage.workoutButton();
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + detail);
            }
        });

    }

    @Then("click workout button of Coach selection")
    public void clickWorkoutButtonOfCoachSelection() {
        coachesPage.clickWorkoutButton();
    }

    @And("List of available coaches is visible")
    public void listOfAvailableCoachesIsVisible() {
        coachesPage.CoachesList();
    }

    @And("should select date and time for the workout")
    public void shouldSelectDateAndTimeForTheWorkout() {
        coachesPage.selectDateAndTime();

    }

    @And("check the testimonal and click book workout")
    public void checkTheTestimonalAndClickBookWorkout() {
        coachesPage.scrollDown();
        coachesPage.checkTestimonal();
        // coachesPage.scrollUp();

    }

    @Then("I should get  {string}")
    public void iShouldGet(String successMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased to 20 seconds
        wait.until(ExpectedConditions.visibilityOf(CoachesPage.getSuccessMessageElement()));

        String actualSuccessMessage = coachesPage.successMessage();
        assertEquals(successMessage, actualSuccessMessage);
    }

    @And("I should see the workouts in dashboard")
    public void iShouldSeeTheWorkoutsInDashboard() {
        coachesPage.seeBookings();
    }


    @And("close the browser")
    public void closeTheBrowser() {
        BrowserFactoryDriver.cleanUp();
        this.driver = BrowserFactoryDriver.getDriver();
        this.coachesPage = new CoachesPage(driver);
    }

    @And("should select an invalid date for the workout")
    public void shouldSelectAnInvalidDateForTheWorkout() {
        coachesPage.selectInvalidDate();
    }

    @Then("I should receive a {string}")
    public void iShouldReceiveA(String InvalidErrorMessage) {
        String actualInvalidErrorMessage = coachesPage.invalidDateErrorMessage();
        assertEquals(InvalidErrorMessage,actualInvalidErrorMessage);
    }

}

