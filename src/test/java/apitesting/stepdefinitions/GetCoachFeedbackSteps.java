package apitesting.stepdefinitions;
import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetCoachFeedbackSteps {
    private Response getFeedbackResponse;
    private String feedbackEndpoint;
    private String token;
    private String sessionId;
    private String createDate;
    private String workoutId;

    private String loginEndpoint;
    private Response loginResponse;


    private Properties config;

    public GetCoachFeedbackSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("the API endpoint is present at the Config file for get coaches feedback")
    public void theAPIEndpointIsPresentAtTheConfigFileForGetCoachesFeedback() {
        loginEndpoint = "/login";
    }

    @And("the user is authenticated with correct credentials for coach feedback retrieval")
    public void theUserIsAuthenticatedWithCorrectCredentialsForCoachFeedbackRetrieval() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL + loginEndpoint);
    }

    @And("the response for login must contains valid id token")
    public void theResponseForLoginMustContainsValidIdToken() {
        token = loginResponse.jsonPath().getString("data.idToken");
    }

    @Given("the API endpoint for getting coach feedback is {string}")
    public void theAPIEndpointForGettingCoachFeedbackIs(String endpoint) {
        feedbackEndpoint = endpoint;
    }

    @And("the query parameters are sessionId={string}, createDate={string}, workoutId={string}")
    public void theQueryParametersAreSessionIdCreateDateWorkoutId(String sessionId, String createDate, String workoutId) {
        this.sessionId = sessionId;
        this.createDate = createDate;
        this.workoutId = workoutId;
    }

    @When("I send a GET request to the coach feedback endpoint")
    public void iSendAGETRequestToTheCoachFeedbackEndpoint() {
        getFeedbackResponse = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("sessionId", sessionId)
                .queryParam("createDate", createDate)
                .queryParam("workoutId", workoutId)
                .when()
                .get(Config.BASE_URL + feedbackEndpoint);

    }

    @Then("the successful response statuscode should be {int}")
    public void theSuccessfulResponseStatuscodeShouldBe(int statusCode) {
        assertEquals(statusCode, getFeedbackResponse.getStatusCode());
    }

    @And("the response success message should be {string}")
    public void theResponseSuccessMessageShouldBe(String expectedMessage) {
        assertEquals(expectedMessage, getFeedbackResponse.jsonPath().getString("message"));
    }
}