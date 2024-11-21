package apitesting.stepdefinitions;
import apitesting.apiurl.Config;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetCoachReportSteps {
    private Properties config;
    private Response getCoachReportResponse;
    private String coachReportEndpoint;
    private String coachId;
    String token;
    private String loginEndpoint;
    private Response loginResponse;

    public GetCoachReportSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the API endpoint for coach report is {string}")
    public void theApiEndpointForCoachReportIs(String endpoint) {
        coachReportEndpoint = endpoint;
    }

    @Given("the coach ID is {string}")
    public void theCoachIdIs(String id) {
        coachId = id;
    }

    @When("I send a GET request to the coach report endpoint")
    public void iSendAGetRequestToTheCoachReportEndpoint() {
        getCoachReportResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(Config.BASE_URL + coachReportEndpoint + "?coachId=" + coachId);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int statusCode) {
        assertEquals(getCoachReportResponse.getStatusCode(), statusCode);
    }

    @And("the response message for retrieve coach report should be {string}")
    public void theResponseMessageForRetrieveCoachReportShouldBe(String expectedMessage) {
        assertEquals(getCoachReportResponse.jsonPath().getString("message"), expectedMessage);
    }

    @Given("the endpoint is present at the Config file for reports")
    public void theEndpointIsPresentAtTheConfigFileForReports() {
        loginEndpoint = "/login";
    }

    @And("the user is authenticated with correct credentials for coach report")
    public void theUserIsAuthenticatedWithCorrectCredentialsForCoachReport() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL + loginEndpoint);
        System.out.println("Response body:" + loginResponse.getBody().asString());

    }

    @And("response contains valid id token")
    public void responseContainsValidIdToken() {
        token = loginResponse.jsonPath().getString(("data.idToken"));
    }

    @Then("the response code Status should be {int}")
    public void theResponseCodeStatusShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, getCoachReportResponse.getStatusCode());
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expectedErrorMessage) {

        assertEquals(expectedErrorMessage, getCoachReportResponse.jsonPath().getString("errorMessage"));
        System.out.println("Response body:" + loginResponse.getBody().asString());
    }

    @When("I send a GET request to the coach report endpoint without authorization")
    public void iSendAGetRequestWithoutAuthorization() {
        getCoachReportResponse = given()
                .when()
                .get(Config.BASE_URL + coachReportEndpoint + "?coachId=" + coachId);


        // Log the response for debugging
        System.out.println("Response body without authorization: " + getCoachReportResponse.getBody().asString());

    }

    @Then("the responsestatus code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, getCoachReportResponse.getStatusCode());
    }


}
