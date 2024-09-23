package apitesting.stepdefinitions;

import apitesting.stepdefinitions.Config;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostCoachFeedbackSteps {

    private Response postFeedbackResponse;
    private String feedbackEndpoint;
    private String token;
    //private String requestBody;
    private Map<String, Object> requestBody;

    private String loginEndpoint;
    private Response loginResponse;


    private Properties config;
    public PostCoachFeedbackSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the API endpoint is present at the Config file for post coaches feedback")
    public void theAPIEndpointIsPresentAtTheConfigFileForPostCoachesFeedback() {
        loginEndpoint = "/login";
    }

    @And("the user is authenticated with correct credentials for coach feedback")
    public void theUserIsAuthenticatedWithCorrectCredentialsForCoachFeedback() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);

    }

    @And("the response contains valid id token for logging")
    public void theResponseContainsValidIdTokenForLogging() {
        token = loginResponse.jsonPath().getString("data.idToken");
    }

    @Given("the API endpoint for coach feedback is {string}")
    public void theApiEndpointForCoachFeedbackIs(String endpoint) {
        feedbackEndpoint = endpoint;
    }

    @Given("the feedback details are valid for coach feedback")
    public void theFeedbackDetailsAreValidForCoachFeedback(DataTable dataTable) {

        List<Map<String, String>> feedbackDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> feedbackDetails = feedbackDetailsList.get(0); // Assuming only one set of details

        requestBody = new HashMap<>();
        requestBody.put("sessionId", feedbackDetails.get("sessionId"));
        requestBody.put("workoutId", feedbackDetails.get("workoutId"));
        requestBody.put("clientId", feedbackDetails.get("clientId"));
        requestBody.put("coachId", feedbackDetails.get("coachId"));
        requestBody.put("rating", Double.parseDouble(feedbackDetails.get("rating")));
        requestBody.put("notes", feedbackDetails.get("notes"));
        requestBody.put("createDate", feedbackDetails.get("createDate"));
    }

    /*@Given("the feedback details are invalid for coach feedback")
    public void theFeedbackDetailsAreInvalidForCoachFeedback() {
        requestBody = "{\n" +
                "  \"id\": \"client-id-123\",\n" +
                "  \"sessionId\": \"Session45\",\n" +
                "  \"workoutId\": \"c43a7ab6-2a9c-4be4-9aac-883940ed7147\",\n" +
                "  \"rating\": \"809.4\",\n" +
                "  \"notes\": \"average\",\n" +
                "  \"createDate\": \"32.2.2024\"\n" +
                "}";
    }*/

   /* @Given("the feedback details cause an internal server error for coach feedback")
    public void theFeedbackDetailsCauseAnInternalServerErrorForCoachFeedback() {
        requestBody = "{\n" +
                "  \"id\": \"client-id-123\",\n" +
                "  \"sessionId\": \"session-0\",\n" +
                "  \"workoutId\": \"c65a7b6-2a9c-4be4-9aac-883940e124\",\n" +
                "  \"rating\": 3,\n" +
                "  \"notes\": \"This should cause an internal error.\",\n" +
                "  \"createDate\": \"2024-09-04\"\n" +
                "}";
    }*/

    @When("I send a POST request to the coach feedback endpoint")
    public void iSendAPostRequestToTheCoachFeedbackEndpoint() {
        postFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(Config.BASE_URL + feedbackEndpoint);


        System.out.println("Response body: " + postFeedbackResponse.getBody().asString());
        System.out.println("Response status code: " + postFeedbackResponse.getStatusCode());

        String fullUrl = Config.BASE_URL + feedbackEndpoint;
        System.out.println("Sending POST request to: " + fullUrl);


    }

    @When("I send a POST request to the coach feedback endpoint for successfull submission")
    public void iSendAPOSTRequestToTheCoachFeedbackEndpointForSuccessfullSubmission() {

        String fullUrl = Config.BASE_URL + feedbackEndpoint;
        System.out.println("Sending POST request to: " + fullUrl);
     //   System.out.println("Response body: " + postFeedbackResponse.getBody().asString());


        postFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(Config.BASE_URL + feedbackEndpoint);

        System.out.println("Response status code: " + postFeedbackResponse.getStatusCode());
       // System.out.println("Response body: " + postFeedbackResponse.getBody().asString());

    }

    @Then("successful response should be {int}")
    public void successfulResponseShouldBe(int statusCode) {
        assertEquals(statusCode, postFeedbackResponse.getStatusCode());

    }

    @Then("the unsuccessful response should be {int}")
    public void theUnsuccessfulResponseShouldBe(int statusCode) {
        assertEquals(statusCode, postFeedbackResponse.getStatusCode());
    }

    @Then("the server error response should be {int}")
    public void theServerErrorResponseShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, postFeedbackResponse.getStatusCode());
    }

    @And("the response message for feedback should be {string}")
    public void theResponseMessageForFeedbackShouldBe(String expectedMessage) {
        assertEquals(expectedMessage, postFeedbackResponse.jsonPath().getString("message"));
    }

    @And("the response error message for feedback should be {string}")
    public void theResponseErrorMessageForFeedbackShouldBe(String expectedErrorMessage) {
        assertEquals(expectedErrorMessage, postFeedbackResponse.jsonPath().getString("errorMessage"));
    }

    @And("the Invalid feedback details are:")
    public void theInvalidFeedbackDetailsAre(DataTable dataTable) {
        List<Map<String, String>> feedbackDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> feedbackDetails = feedbackDetailsList.get(0); // Assuming only one set of details

        requestBody = new HashMap<>();
        requestBody.put("sessionId", feedbackDetails.get("sessionId"));
        requestBody.put("workoutId", feedbackDetails.get("workoutId"));
        requestBody.put("clientId", feedbackDetails.get("clientId"));
        requestBody.put("coachId", feedbackDetails.get("coachId"));
        requestBody.put("rating", feedbackDetails.get("rating")); // Assuming rating is invalid
        requestBody.put("notes", feedbackDetails.get("notes"));
        requestBody.put("createDate", feedbackDetails.get("createDate")); // Assuming createDate is invalid
    }
}
