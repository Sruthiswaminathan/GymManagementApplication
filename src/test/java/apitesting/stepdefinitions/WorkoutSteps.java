package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.AssertJUnit.assertEquals;

public class WorkoutSteps {
    private Response postResponse;
    private Response patchResponse;
    private String workoutEndpoint;
    private Response loginResponse;
    private String loginEndpoint;
    String idToken;
    private Properties config;
    private Map<String,String> workoutDetails;
    private String workoutId;
    private String newStatus;

    public WorkoutSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //login
    @Given("the url is collected from Config")
    public void theUrlIsPresentInConfigFile() {
        loginEndpoint = "/login";
        workoutEndpoint="/workout";
    }
    @When("the user is authenticated with correct credentials with email and password")
    public void theUserIsAuthenticatedWithCorrectCredintialsWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response should include correct valid id token")
    public void theResponseShouldIncludeCorrectValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }

    //post
    @Given("the user has the workout details")
    public void theUserHasTheWorkoutDetails(Map<String, String> workoutDetails) {
        this.workoutDetails = workoutDetails;
    }
    @When("the user sends a POST request to create a new workout with BearerToken")
    public void theUserSendsPostRequestToCreateNewWorkout() {
        postResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .body(workoutDetails)
                .when()
                .post(Config.BASE_URL + workoutEndpoint)
                .thenReturn();
        System.out.println("Response body:"+postResponse.getBody().asString());
    }

    @Then("For post method the response should return a status code {int}")
    public void forPostMEthodtheResponseShouldReturnAStatusCode(int statusCode) {
       assertEquals(postResponse.getStatusCode(),statusCode);
    }
    @Then("the response body should contain message {string} and an id for POST")
    public void theResponseBodyShouldContainMessageAndIdForPost(String expectedMessage) {
        if(postResponse.getStatusCode()==200) {
            Assert.assertEquals(postResponse.jsonPath().getString("message"), expectedMessage);
            assertThat(postResponse.jsonPath().getString("data.id"), notNullValue());
        }
        else if(postResponse.getStatusCode()==400){
            Assert.assertEquals(postResponse.jsonPath().getString("errorMessage"), expectedMessage);
        }
    }


    //patch
    @Given("the user has a workout id {string} and a new status {string}")
    public void theUserHasAWorkoutIdAndNewStatus(String id,String status) {
        this.workoutId=id;
        this.newStatus = status;
    }
    @When("the user sends a PATCH request to update the workout status with BearerToken")
    public void theUserSendsPatchRequestToUpdateWorkoutStatus() {
        patchResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .when()
                .patch(Config.BASE_URL + "/workout?id=" + workoutId + "&status=" + newStatus)
                .thenReturn();
        System.out.println("Response body: " + patchResponse.getBody().asString());
    }
    @Then("the response should return a status code {int} for PATCH")
    public void theResponseShouldReturnAStatusCodeForPatch(int statusCode) {
        patchResponse.then().statusCode(statusCode);
    }
    @Then("the response body should contain message {string} for PATCH")
    public void theResponseBodyShouldContainMessageForPatch(String expectedMessage) {
        if(patchResponse.getStatusCode()==200) {
            Assert.assertEquals(patchResponse.jsonPath().getString("message"), expectedMessage);
        }
        else{
            Assert.assertEquals(patchResponse.jsonPath().getString("errorMessage"), expectedMessage);
        }
    }
}
