package apitesting.stepdefinitions;

import apitesting.stepdefinitions.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetClientFeedbackSteps {

    private Response getFeedbackResponse;
    private String feedbackEndpoint;
    private String token;
    private String sessionId;
    private String createDate;
    private String workoutId;
    private String loginEndpoint;
    private Response loginResponse;


    private Properties config;
    public GetClientFeedbackSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("the API endpoint is present at the Config file for get client feedback")
    public void theAPIEndpointIsPresentAtTheConfigFileForGetClientFeedback() {
        loginEndpoint = "/login";
    }

    @And("the user is authenticated with correct credentials for clients feedback")
    public void theUserIsAuthenticatedWithCorrectCredentialsForClientsFeedback() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);

    }

    @And("the response for login contains valid id token")
    public void theResponseForLoginContainsValidIdToken() {
        token = loginResponse.jsonPath().getString("data.idToken");
    }

    @Given("the endpoint for client feedback is {string}")
    public void theEndpointForClientFeedbackIs(String endpoint) {
        feedbackEndpoint = endpoint;
    }

    @And("the feedback request parameters are provided")
    public void theFeedbackRequestParametersAreProvided() {
        sessionId = "sample_session_id"; // Example value; replace with actual session ID
        createDate = "2024-09-04"; // Example date
        workoutId = "c43a7ab6-2a9c-4be4-9aac-883940ed7147"; // Example workout ID
    }

    @When("I send a GET request to the client feedback endpoint")
    public void iSendAGETRequestToTheClientFeedbackEndpoint() {
        getFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .param("sessionId", sessionId)
                .param("createDate", createDate)
                .param("workoutId", workoutId)
                .when()
                .get(Config.BASE_URL + feedbackEndpoint);
        
    }

    @Then("the successful response should be {int}")
    public void theSuccessfulResponseShouldBe(int statusCode) {
        assertEquals(statusCode, getFeedbackResponse.getStatusCode());
    }

    @And("the response message for feedback retrieval should be {string}")
    public void theResponseMessageForFeedbackRetrievalShouldBe(String expectedMessage) {
        assertEquals(expectedMessage, getFeedbackResponse.jsonPath().getString("message"));
    }
}
/*  @Given("the API endpoint for client feedback is {string}")
    public void theApiEndpointForClientFeedbackIs(String endpoint) {
        feedbackEndpoint = endpoint;
    }

    @Given("the feedback request parameters are provided")
    public void theFeedbackRequestParametersAreProvided() {
        sessionId = "sample_session_id"; // Example value; replace with actual session ID
        createDate = "2024-09-04"; // Example date
        workoutId = "c43a7ab6-2a9c-4be4-9aac-883940ed7147"; // Example workout ID
    }

    @When("I send a GET request to the client feedback endpoint")
    public void iSendAGetRequestToTheClientFeedbackEndpoint() {
        getFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .param("sessionId", sessionId)
                .param("createDate", createDate)
                .param("workoutId", workoutId)
                .when()
                .get(Config.BASE_URL + feedbackEndpoint);
    }

    @Then("the successful response should be {int}")
    public void theSuccessfulResponseShouldBe(int statusCode) {
        assertEquals(statusCode, getFeedbackResponse.getStatusCode());
    }

    @Then("the response message for feedback retrieval should be {string}")
    public void theResponseMessageForFeedbackRetrievalShouldBe(String expectedMessage) {
        assertEquals(expectedMessage, getFeedbackResponse.jsonPath().getString("message"));
    }
}*/