package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TokenRefreshSteps {
    private Response response;
    private String refreshEndpoint = "/refresh-tokens";
    String refreshToken;


    @Given("I have successfully logged in and {string}")
    public void iHaveSuccessfullyLoggedInAndRetrievedTheTokens(String token) {
        refreshToken = token;
    }

    @When("I use the refresh token to request new tokens")
    public void iSendAPostRequestToTheRefreshEndpoint() {
        response = given()
                .contentType("application/json")
                .body("{\"refreshToken\": \"" + refreshToken + "\"}")
                .post(Config.BASE_URL + refreshEndpoint);
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @When("I use the refresh token {string} to request new tokens")
    public void iSendAPostRequestToTheRefreshEndpointWithInvalidRefershToken(String refresh) {
        response = given()
                .contentType("application/json")
                .body("{\"refreshToken\": \"" +refresh+ "\"}")
                .post(Config.BASE_URL + refreshEndpoint);
        System.out.println("Response Body: " + response.getBody().asString());
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
        System.out.println("Stored idToken: " + idToken);
        System.out.println("Stored accessToken: " + accessToken);
    }
    @Given("I have successfully logged in and retrieved the tokens from the previous scenario")
    public void iHaveSuccessfullyLoggedInAndRetrievedTheTokensFromThePreviousScenario() {
    }
    @And("the response should contain the error message as {string}")
    public void theResponseShouldContainTheErrorMessageAs(String errorMessage) {
        String actualErrorMessage = response.jsonPath().getString("errorMessage");
        System.out.println("Actual error message:"+actualErrorMessage);
        String staticPartOfExpectedMessage = errorMessage;
        Assert.assertTrue(actualErrorMessage.startsWith(staticPartOfExpectedMessage), "Error message does not match");
    }


}
