package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetCoachesByIdSteps {
    private Response getCoachByIdResponse;
    String token;
    String idToken;
    private String coachDetailsEndpoint;
    private String loginEndpoint;
    private Response loginResponse;
    private Properties config;
    public GetCoachesByIdSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //login
    @Given("the endpoint is present at the Config file")
    public void theUrlIsPresentInConfigFile() {
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with below credentials for Coaches")
    public void theUserIsAuthenticatedWithBelowCredentialsForCoaches() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response contains a valid token")
    public void theResponseContainsAValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }


    //valid
    @Given("the API endpoint for coach details is {string}")
    public void theApiEndpointForCoachDetailsIs(String endpoint) {
        coachDetailsEndpoint = endpoint;
    }
    @When("I send a GET request to the coach details endpoint with ID {string}")
    public void iSendAGetRequestToTheCoachDetailsEndpointWithID(String coachId) {
        getCoachByIdResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .get(Config.BASE_URL + coachDetailsEndpoint.replace("{id}", coachId));
        System.out.println("Response body:"+getCoachByIdResponse.getBody().asString());
    }
    @Then("the response should be statuscode {int} for successful")
    public void theResponseShouldBeStatuscodeForSuccessful(int statusCode) {
        assertThat(getCoachByIdResponse.getStatusCode(), equalTo(statusCode));
    }
    @And("the response message for coaches Id should be {string}")
    public void theResponseMessageForCoachesIdShouldBe(String expectedMessage) {
        getCoachByIdResponse.then().body("message", equalTo(expectedMessage));
    }
    @Then("the response should include {string}, {string}, {string}, {string}, {string}, and {string}")
    public void theResponseShouldIncludeAnd(String string, String string2, String string3, String string4, String string5, String string6) {
        getCoachByIdResponse.then().body("data.id", notNullValue())
                .body("data.name", notNullValue())
                .body("data.summary", notNullValue())
                .body("data.specialization", notNullValue())
                .body("data.rating", notNullValue())
                .body("data.reviews", notNullValue());
    }


    //invalid
    @Then("the response should be status code {int} for invalid id")
    public void theResponseShouldBeStatusCodeForInvalid(int statusCode) {
        assertEquals(getCoachByIdResponse.getStatusCode(), statusCode);
    }
    @And("the response error message should be {string}")
    public void theResponseErrorMessageShouldBe(String expectedErrorMessage) {
        getCoachByIdResponse.then().body("errorMessage", equalTo(expectedErrorMessage));
    }
}
