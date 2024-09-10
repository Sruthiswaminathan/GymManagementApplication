package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TokenRefreshSteps {
    private Response response;
    private String refreshEndpoint;
    String refreshToken;
    private String loginEndpoint;
    Response loginResponse;
//login
    @Given("the API endpoint is present")
    public void theAPIEndpointIsPresent() {
        refreshEndpoint="/refresh-tokens";
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with below email and password")
    public void theUserIsAuthenticatedWithBelowEmailAndPassword(DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        loginResponse = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("the response stores a valid id token and access token")
    public void theResponseStoresAValidIdTokenAndAccessToken() {
        refreshToken = loginResponse.jsonPath().getString("data.refreshToken");
    }

    //refreshtoken
    @Given("I have successfully logged in")
    public void iHaveSuccessfullyLoggedInAndRetrievedTheTokensFromThePreviousScenario() {
    }
    @When("I use the refresh token to request new tokens")
    public void iSendAPostRequestToTheRefreshEndpoint() {
        response = given()
                .contentType("application/json")
                .body("{\"refreshToken\": \"" + refreshToken + "\"}")
                .post(Config.BASE_URL + refreshEndpoint);
    }
    @Then("I should receive a {int} status code")
    public void theStatusCodeShouldBe(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
    @And("the response should contain \"idToken\" and \"accessToken\" that are not empty")
    public void iShouldReceiveANewAccessTokenAndIdToken() {
        String idToken = response.jsonPath().getString("data.idToken");
        String accessToken = response.jsonPath().getString("data.accessToken");
        assertThat(idToken, notNullValue());
        assertThat(accessToken, notNullValue());
    }
}
