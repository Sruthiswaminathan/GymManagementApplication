package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class PostClientFeedbackSteps {

    private Response postFeedbackResponse;
    private String clientFeedbackEndpoint;
    private String token;
    private String feedbackBody;
    private String loginEndpoint;
    private Response loginResponse;
    private Properties config;
    public PostClientFeedbackSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Given("the API endpoint is present at the Config file for post client feedback")
    public void theAPIEndpointIsPresentAtTheConfigFileForPostClientFeedback() {
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with correct credentials for client feedback")
    public void theUserIsAuthenticatedWithCorrectCredentialsForClientFeedback() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response contains valid id token")
    public void theResponseContainsValidIdToken() {
        token = loginResponse.jsonPath().getString("data.idToken");
    }



    @Given("the API endpoint for client feedback is {string}")
    public void theApiEndpointForClientFeedbackIs(String endpoint) {
        clientFeedbackEndpoint = endpoint;
    }
    @Given("the feedback details are provided")
    public void theFeedbackDetailsAreProvided() {
        feedbackBody = "{\n" +
                "  \"sessionId\": \"sample sessionId\",\n" +
                "  \"workoutId\": \"dab4f6a0-2f68-4bc7-a81d-5c462d3bdd50\",\n" +
                "  \"clientId\": \"c037e6a5-71b1-4e77-89b5-79cfc1f04d59\",\n" +
                "  \"coachId\": \"f20c869e-70c1-42b9-b92d-b893834c8562\",\n" +
                "  \"rating\": 4.2,\n" +
                "  \"notes\": \"sample 1234\",\n" +
                "  \"createDate\": \"2020-09-10\"\n" +
                "}";
    }
    @When("I send a POST request to the client feedback endpoint")
    public void iSendAPostRequestToTheClientFeedbackEndpoint() {
        postFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(feedbackBody)
                .when()
                .post(Config.BASE_URL + clientFeedbackEndpoint);
        System.out.println("Response Status Code: " + postFeedbackResponse.getStatusCode());
        System.out.println("Response Body: " + postFeedbackResponse.getBody().asString());
    }
    @Then("the response message for feedback submission should be {string}")
    public void theResponseMessageForFeedbackSubmissionShouldBe(String expectedMessage) {
        String actualMessage = postFeedbackResponse.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage == null ? "Rating must be between 0 and 5." : actualMessage);
    }
    @Then("the successfull response should be {int}")
    public void theSuccessfullResponseShouldBe(int statusCode) {
        assertEquals(postFeedbackResponse.getStatusCode(), statusCode);
    }


    @Given("the feedback details are invalid")
    public void theFeedbackDetailsAreInvalid() {
        feedbackBody = "{\n" +
                "  \"sessionId\": \"session32\",\n" + // invalid data for testing
                "  \"workoutId\": \"workout12\",\n" +
                "  \"clientId\": \"client12\",\n" +
                "  \"coachId\": \"f20c869e-70c1-42b9-b92d-b893834c8562\",\n" +
                "  \"rating\": \"909.0\",\n" +
                "  \"notes\": \"sample\",\n" +
                "  \"createDate\": \"1.23.2121\"\n" +
                "}";
    }
    @Then("the unsuccessfull response should be {int}")
    public void theUnsuccessfullResponseShouldBe(int expectedStatusCode) {
        assertEquals(postFeedbackResponse.getStatusCode(), expectedStatusCode);
    }
}
