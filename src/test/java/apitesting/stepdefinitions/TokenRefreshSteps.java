package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TokenRefreshSteps {
    private Response response;
    private String refreshEndpoint;
    String refreshToken;
    private String loginEndpoint;
    Response loginResponse;
    private Properties config;

    public TokenRefreshSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//login
    @Given("the API endpoint is present")
    public void theAPIEndpointIsPresent() {
        refreshEndpoint="/refresh-tokens";
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with below email and password")
    public void theUserIsAuthenticatedWithBelowEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response stores a valid id token and access token")
    public void theResponseStoresAValidIdTokenAndAccessToken() {
        refreshToken = loginResponse.jsonPath().getString("data.refreshToken");
    }


    @Given("I have successfully logged in")
    public void iHaveSuccessfullyLoggedInAndRetrievedTheTokensFromThePreviousScenario() {
        System.out.println("After login collected the idToken");
    }
    @When("I use the refresh token to request new tokens")
    public void iSendAPostRequestToTheRefreshEndpoint() {
        response = given()
                .contentType("application/json")
                .body("{\"refreshToken\": \"" + refreshToken + "\"}")
                .post(Config.BASE_URL+ refreshEndpoint);
    }
    @Then("I should receive a message as {string}")
    public void iShouldReceiveAMessageAs(String message) {
        assertThat(response.jsonPath().getString("message"), equalTo(message));
    }
    @And("the response should contain \"idToken\" and \"accessToken\" that are not empty")
    public void iShouldReceiveANewAccessTokenAndIdToken() {
        String idToken = response.jsonPath().getString("data.idToken");
        String accessToken = response.jsonPath().getString("data.accessToken");
        assertThat(idToken, notNullValue());
        assertThat(accessToken, notNullValue());
    }
}
