package stepdefinitions;

import io.cucumber.datatable.DataTable;
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
import static org.hamcrest.Matchers.equalTo;
public class UserLogoutSteps {
    private Response response;
    private Response loginResponse;
    private String logoutEndpoint = "/logout";
    private String loginEndpoint;
    String idToken;
    String accessToken;
    private Properties config;

    public UserLogoutSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //login
    @Given("the Url is present in Config file")
    public void theUrlIsPresentInConfigFile() {
        logoutEndpoint="/logout";
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with email and password")
    public void theUserIsAuthenticatedWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response contains a valid id token and access token")
    public void theResponseContainsAValidIdTokenAndAccessToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
        accessToken = loginResponse.jsonPath().getString("data.accessToken");
    }

    //logout
    @When("I send a POST request to the logout endpoint")
    public void iSendAPostRequestToTheLogoutEndpoint() {
        response = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .body("{\"accessToken\": \"" + accessToken + "\"}")
                .post(Config.BASE_URL+ logoutEndpoint);
    }
    @Then("I should receive a {int} status code for logout successful")
    public void iShouldReceiveAStatusCodeStatusCodeForLogoutSuccessful(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
    @And("the response should contain the message {string}")
    public void theResponseShouldContainTheMessage(String message) {
        String Message = response.jsonPath().getString("message");
        Assert.assertEquals(message, Message);
    }

}
