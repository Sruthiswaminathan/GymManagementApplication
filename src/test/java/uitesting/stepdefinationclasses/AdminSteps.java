package uitesting.stepdefinationclasses;

import basetest.BrowserFactoryDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CoachProfileEdit;
import pages.LoginPage;
import pages.Admin;
import uitesting.apiurls.Config1;
import uitesting.apiurls.Config2;

import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.*;

public class AdminSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private Admin admin;
    private CoachProfileEdit coachProfileEdit;
    String email;
    String password;
    private Properties config2;

    public AdminSteps() {
        this.driver = BrowserFactoryDriver.getDriver();
        this.loginPage = new LoginPage(driver);
        this.admin = new Admin(driver);
        this.coachProfileEdit = new CoachProfileEdit(driver);


        try {
            config2 = Config2.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("User opened the Gym Management Application")
    public void userOpenedTheGymManagementApplication() {
       loginPage.openPage();

    }
    @When("I collect the email and password from config2 properties file")
    public void iCollectTheEmailAndPasswordFromConfig2PropertiesFile() {
        // Write code here that turns the phrase above into concrete actions
        email = config2.getProperty("email");
        password = config2.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);

    }


    @And("I should click Login button")
    public void iShouldClickLoginButton() {
      loginPage.clickLoginButton();
    }

    @Then("I should get a success message {string}")
    public void iShouldGetSuccessMessage(String message) {
        loginPage.successMessage();
    }

    @Then("redirected to the coaches list page")
    public void redirectedToTheCoachesListPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        assertTrue(admin.CoachesListPage());
    }

    @Then("I should see the list of coaches with details such as Coach Name, Expertise, and Ratings")
    public void iShouldSeeTheListOfCoachesWithDetails() {
        // Code to verify the list of coaches
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement coachProfileCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'MuiButton-containedPrimary')][span[contains(@class, 'MuiTouchRipple-root')]]")));
                //"//h6[contains(text(), 'Coach Josh')]")));
                //xpath("//img[@class='MuiCardMedia-root MuiCardMedia-media MuiCardMedia-img css-146e5oc' and @src='/assets/Avatar-o6SOcAyW.png']")));
        assertTrue(coachProfileCard.isDisplayed());
    }


    @And("I click on the view report button of {string}")
    public void iClickOnTheViewReportButtonOf(String coachName) {
        admin.clickViewReportButton();
    }
    @Then("I should see the weekly report with details such as:")
    public void iShouldSeeTheWeeklyReportWithDetails(DataTable dataTable) {
        dataTable.asList().forEach(details->{
            switch(details){
                case "Coach Name" :
                    admin.verifyCoachName();
                    break;
                case "Report Week":
                    admin.verifyReportWeek();
                    break;
                case "Total Sessions":
                    admin.verifyTotalSessions();
                    break;
                case "Total Duration (mins)":
                    admin.verifyTotalDuration();
                    break;
                case "Average Client Rating":
                    admin.verifyAverageClientRating();
                    break;
                case "Feedback Count":
                    admin.verifyFeedbackCount();
                    break;
                case "Total Clients":
                   admin.verifyTotalClients();
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + details);
            }
        });

    }

    @When("I view the report for {string}")
    public void iViewTheReportFor(String coachName) {
        assertTrue(admin.reportPage());
    }

    @And("I click the Download button")
    public void iClickTheDownloadButton() {
        admin.downloadReport();
    }

    @Given("I click on My profile")
    public void iClickOnMyProfile() {
        loginPage.clickOnProfileButton();
    }


    @Then("I should click on the  edit profile")
    public void iShouldClickOnTheEditProfile() {
      coachProfileEdit.clickOnEditProfile();
    }

    @And("I should click on profile picture button")
    public void iShouldClickOnProfilePictureButton() {
        coachProfileEdit.clickOnProfilePicture();
    }

    @Then("I click logout")
    public void iClickLogout() {
        loginPage.clickLogoutButton();
    }
}