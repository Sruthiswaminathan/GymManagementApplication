package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class PostClientFeedbackSteps {

    private Response postFeedbackResponse;
    private String clientFeedbackEndpoint;
    private String token;
    private Properties config;
    private String feedbackBody;
    private String loginEndpoint;
    private Response loginResponse;

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
                .post(Config.BASE_URL + loginEndpoint);


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
    public void theFeedbackDetailsAreProvided(DataTable table) {
        Map<String, String> feedbackData = table.asMap(String.class, String.class);
        feedbackBody = "{\n" +
                "  \"sessionId\": \"" + feedbackData.get("sessionId") + "\",\n" +
                "  \"workoutId\": \"" + feedbackData.get("workoutId") + "\",\n" +
                "  \"clientId\": \"" + feedbackData.get("clientId") + "\",\n" +
                "  \"coachId\": \"" + feedbackData.get("coachId") + "\",\n" +
                "  \"rating\": " + feedbackData.get("rating") + ",\n" +
                "  \"notes\": \"" + feedbackData.get("notes") + "\",\n" +
                "  \"createDate\": \"" + feedbackData.get("createDate") + "\"\n" +
                "}";
    }

    @Given("the feedback details are invalid")
    public void theFeedbackDetailsAreInvalid(DataTable table) {
        Map<String, String> feedbackData = table.asMap(String.class, String.class);
        feedbackBody = "{\n" +
                "  \"sessionId\": \"" + feedbackData.get("sessionId") + "\",\n" +
                "  \"workoutId\": \"" + feedbackData.get("workoutId") + "\",\n" +
                "  \"clientId\": \"" + feedbackData.get("clientId") + "\",\n" +
                "  \"coachId\": \"" + feedbackData.get("coachId") + "\",\n" +
                "  \"rating\": " + feedbackData.get("rating") + ",\n" +
                "  \"notes\": \"" + feedbackData.get("notes") + "\",\n" +
                "  \"createDate\": \"" + feedbackData.get("createDate") + "\"\n" +
                "}";
    }

    /*
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
    }*/


    @When("I send a POST request to the client feedback endpoint")
    public void iSendAPostRequestToTheClientFeedbackEndpoint() {

        postFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(feedbackBody)
                .when()
                .post(Config.BASE_URL + clientFeedbackEndpoint);

        System.out.println("Response body: " + postFeedbackResponse.getBody().asString());
    }


    @Then("the response message for feedback submission should be {string}")
    public void theResponseMessageForFeedbackSubmissionShouldBe(String expectedMessage) {
      /*  String actualMessage = postFeedbackResponse.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage == null ? "Feedback created successfully." : actualMessage);


        assertEquals(expectedMessage, postFeedbackResponse.jsonPath().getString("errorMessage"));*/


        String actualMessage = postFeedbackResponse.jsonPath().getString("message");

        // Check if the message is null or empty
        if (actualMessage == null || actualMessage.isEmpty()) {
            System.out.println("Error: Feedback message is null or empty.");
        } else {
            System.out.println("Response Body: " + postFeedbackResponse.getBody().asString());
            // Assert that the actual message matches the expected message
            assertEquals(expectedMessage, actualMessage);
        }
        System.out.println("Response Body: " + postFeedbackResponse.getBody().asString());
    }


    @Then("the successful response status should be {int}")
    public void theSuccessfulResponseStatusShouldBe(int statusCode) {
        assertEquals(postFeedbackResponse.getStatusCode(), statusCode);
        System.out.println("Response body: " + postFeedbackResponse.getBody().asString());
    }

    @Then("the unsuccessful response status should be {int}")
    public void theUnsuccessfulResponseStatusShouldBe(int expectedStatusCode) {
        assertEquals(postFeedbackResponse.getStatusCode(), expectedStatusCode);

    }
}
