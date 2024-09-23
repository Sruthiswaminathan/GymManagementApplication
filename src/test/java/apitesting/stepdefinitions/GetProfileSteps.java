package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.en.Then;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.given;

public class GetProfileSteps {
    private Response getProfileResponse;
    private String profileEndpoint;
    private String loginEndpoint;
    private Response loginResponse;
    String idToken;
    String invalidIdToken;
    private Properties config;
    public GetProfileSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//login
    @Given("the API endpoint is present at Config file")
    public void theAPIEndpointIsPresentAtConfigFile() {
        loginEndpoint = "/login";
    }
    @When("the user is authenticated correct credentials with above email and password")
    public void theUserIsAuthenticatedCorrectCredentialsWithAboveEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response contains the valid id token")
    public void theResponseContainsTheValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }



    //valid
    @Given("the API endpoint for profile is {string}")
    public void theApiEndpointIs(String endpoint) {
        profileEndpoint = endpoint;
    }
    @When("I send a GET request to the profile endpoint")
    public void iSendAGetRequestToTheProfileEndpoint() {
        getProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .when()
                .get(Config.BASE_URL+ profileEndpoint);
    }
    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertThat(getProfileResponse.getStatusCode(), equalTo(statusCode));
    }
    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        String actualMessage = getProfileResponse.jsonPath().getString("message");
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @Then("the response should include fullName, email, target, preferableActivity")
    public void the_response_should_include() {
        assertThat(getProfileResponse.jsonPath().getString("data.fullName"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.email"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.target"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.preferableActivity"), notNullValue());
    }


    //invalid
    @Given("the API endpoint for profile is {string} and idToken {string}")
    public void theAPIEndpointForProfileIsAndIdToken(String endpoint, String idToken) {
        profileEndpoint = endpoint;
        invalidIdToken=idToken;
    }
    @When("I send a GET request to the profile endpoint for invalid")
    public void iSendAGETRequestToTheProfileEndpointForInvalid() {
        getProfileResponse = given()
                .header("Authorization", "Bearer " + invalidIdToken)
                .contentType("application/json")
                .when()
                .get(Config.BASE_URL+ profileEndpoint);
        System.out.println("Response body:"+getProfileResponse.getBody().asString());
    }
    @Then("the response status code should be {int} for invalid")
    public void theResponseStatusCodeShouldBeForInvalid(int statusCode) {
        assertThat(getProfileResponse.getStatusCode(), equalTo(statusCode));
    }

}