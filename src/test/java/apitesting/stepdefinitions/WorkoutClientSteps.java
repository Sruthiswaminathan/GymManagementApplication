package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class WorkoutClientSteps {
    private Response getResponse;
    private String workoutClientEndpoint;
    private Response loginResponse;
    private String loginEndpoint;
    String idToken;
    private Properties config;
    private String clientId;

    public WorkoutClientSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //login
    @Given("the url is collected from Config file")
    public void theUrlIsCollectedFromConfigFile() {
        loginEndpoint = "/login";
        workoutClientEndpoint="/workout/client";
    }
    @When("the user is authenticated with below credentials with email and password")
    public void theUserIsAuthenticatedWithBelowCredentialsWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response should contain a correct valid id token")
    public void theResponseShouldContainACorrectValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }


    //get 
    @Given("the user has a client ID {string}")
    public void theUserHasAClientId(String clientId) {
        this.clientId = clientId;
    }
    @When("the user sends a GET request to retrieve all workouts of the client with BearerToken")
    public void theUserSendsGetRequestToRetrieveAllWorkoutsOfClient() {
        getResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .queryParam("clientId", clientId)
                .when()
                .get(Config.BASE_URL + workoutClientEndpoint)
                .thenReturn();
        System.out.println("Response body: " + getResponse.getBody().asString());
    }
    @Then("the response should return a status code {int} for GET clients")
    public void theResponseShouldReturnAStatusCodeForGetClients(int statusCode) {
        System.out.println(getResponse.getStatusCode());
        assertEquals(getResponse.getStatusCode(),statusCode);
    }
    @Then("the get clients response body should contain message {string} and a list of workouts")
    public void theGetClientsResponseBodyShouldContainMessageAndListOfWorkouts(String expectedMessage) {
        Assert.assertEquals(getResponse.jsonPath().getString("message"), expectedMessage);
    }

}

