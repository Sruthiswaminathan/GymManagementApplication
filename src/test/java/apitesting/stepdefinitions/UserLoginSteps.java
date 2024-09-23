package apitesting.stepdefinitions;
import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class UserLoginSteps {
    private Response response1;
    private Response response2;
    private String loginEndpoint;
    private Properties config;

    public UserLoginSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Given("I am the user trying to login")
    public void iAmTheUserTryingToLogin() {
        loginEndpoint="/login";
    }
    @When("the user provides the login details with email and password")
    public void theUserProvidesTheLoginDetailsWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        response1 = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        assertThat(response1.getStatusCode(), equalTo(statusCode));
    }
    @Then("the response should contain tokens idToken, accessToken, refreshToken and {string}")
    public void theResponseShouldContainTokens(String message) {
        assertThat(response1.jsonPath().getString("data.idToken"), notNullValue());
        assertThat(response1.jsonPath().getString("data.accessToken"), notNullValue());
        assertThat(response1.jsonPath().getString("data.refreshToken"), notNullValue());
        String Message = response1.jsonPath().getString("message");
        Assert.assertEquals(message, Message);
    }
    @When("the user provides the login details with below details:")
    public void theUserProvidesTheLoginDetailsWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps(String.class, String.class).get(0);
        String email = data.get("email");
        String password = data.get("password");
        response2 = RestAssured.given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(Config.BASE_URL+ loginEndpoint);
    }
    @Then("I should receive a {int} status code for invalid")
    public void iShouldReceiveAStatusCodeForInvalid(int statusCode) {
        assertThat(response2.getStatusCode(), equalTo(statusCode));
    }
    @And("the response should contain the error message {string}")
    public void theResponseShouldContainTheErrorMessage(String message) {
        String actualErrorMessage = response2.jsonPath().getString("errorMessage");
        Assert.assertTrue(actualErrorMessage.startsWith(message), "Error message does not match");
    }
}
