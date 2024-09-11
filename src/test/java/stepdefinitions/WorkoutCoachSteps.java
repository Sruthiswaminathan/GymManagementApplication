package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class WorkoutCoachSteps {
    private Response getResponse;
    private String workoutCoachEndpoint;
    private Response loginResponse;
    private String loginEndpoint;
    String idToken;
    private Properties config;
    private String coachId;

    public WorkoutCoachSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //login
    @Given("the url is collected and stored from Config file")
    public void theUrlIsCollectedAndStoredFromConfigFile() {
        loginEndpoint = "/login";
        workoutCoachEndpoint="/workout/coach";
    }
    @When("the user is authenticated with correct  below credentials with email and password")
    public void theUserIsAuthenticatedWithCorrectBelowCredentialsWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response should include correct valid id token and to be stored")
    public void theResponseShouldIncludeCorrectValidIdTokenAndToBeStored() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }

    //get
    @Given("the user has a coach ID {string}")
    public void theUserHasACoachId(String coachId) {
        this.coachId = coachId;
    }
    @When("the user sends a GET request to retrieve all workouts of the coach with BearerToken")
    public void theUserSendsGetRequestToRetrieveAllWorkoutsOfCoach() {
        getResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .queryParam("coachId", coachId)
                .when()
                .get(Config.BASE_URL +workoutCoachEndpoint)
                .thenReturn();
        System.out.println("Response body: " + getResponse.getBody().asString());
    }
    @Then("the response should return a status code {int} for GET coaches")
    public void theResponseShouldReturnAStatusCodeForGetCoaches(int statusCode) {
        assertEquals(getResponse.getStatusCode(),statusCode);
    }
    @And("the get coaches response body should contain message {string} and a list of workouts")
    public void theGetCoachesResponseBodyShouldContainMessageAndListOfWorkouts(String expectedMessage) {
        Assert.assertEquals(getResponse.jsonPath().getString("message"), expectedMessage);
    }
}
