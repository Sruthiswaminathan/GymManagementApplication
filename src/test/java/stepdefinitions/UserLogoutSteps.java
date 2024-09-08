package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserLogoutSteps {
    private Response response;
    private String logoutEndpoint = "/logout";
    String idToken;
    String accessToken;

    @Given("I have idToken as {string} and accessToken as {string}")
    public void iHaveIdTokenAsAndAccessTokenAs(String token1, String token2) {
        idToken=token1;
        accessToken=token2;
    }

    @When("I send a POST request to the logout endpoint")
    public void iSendAPostRequestToTheLogoutEndpoint() {
        response = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .body("{\"accessToken\": \"" + accessToken + "\"}")
                .post(Config.BASE_URL+ logoutEndpoint);
    }

    @Then("I should receive a {int} status code for logout")
    public void iShouldReceiveAStatusCodeForLogout(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("the response should contain the message {string}")
    public void theResponseShouldContainTheMessage(String message) {
        System.out.println("Response Body: " + response.getBody().asString());
        if(response.statusCode()==400) {
            String actualErrorMessage = response.jsonPath().getString("errorMessage");
            String staticPartOfExpectedMessage = message;
            Assert.assertTrue(actualErrorMessage.startsWith(staticPartOfExpectedMessage), "Error message does not match");
        }
        else if(response.statusCode()==403)
        {
            String actualErrorMessage = response.jsonPath().getString("Message");
            String staticPartOfExpectedMessage = message;
            Assert.assertTrue(actualErrorMessage.equals(staticPartOfExpectedMessage), "Error message does not match");
        }
        else if(response.statusCode()==401){
            String actualErrorMessage = response.jsonPath().getString("message");
            String staticPartOfExpectedMessage = message;
            Assert.assertTrue(actualErrorMessage.equals(staticPartOfExpectedMessage), "Error message does not match");
        }
        else{

        }
    }
}
