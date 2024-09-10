package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
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
    //login
    @Given("the API endpoint is present at {string}")
    public void theAPIEndpointIsPresentAt(String arg0) {
        logoutEndpoint="/logout";
        loginEndpoint = "/login";
        System.out.println(loginEndpoint + " "+ logoutEndpoint);
    }
    @And("the user is authenticated with email and password")
    public void theUserIsAuthenticatedWithEmailAndPassword(DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        loginResponse = given()
                .contentType("application/json")
                .body(userDetails)
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
        System.out.println("Response Body: " + response.getBody().asString());
        String Message = response.jsonPath().getString("message");
        Assert.assertTrue(Message.equals(message));
    }

}
