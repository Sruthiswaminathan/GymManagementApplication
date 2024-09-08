package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UserLoginSteps {
    private Response response1;
    private Response response2;
    private String loginEndpoint;

    @Given("I am the user trying to login")
    public void iAmTheUserTryingToLogin() {
        loginEndpoint="/login";
    }
    @When("the user provides the login details with email {string} and password {string}")
    public void theUserProvidesTheLoginDetailsWithEmailAndPassword(String email, String password) {
        response1 = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
        System.out.println("Response Body: " + response1.getBody().asString());


    }
    @Then("I should receive a {int} statuscode")
    public void iShouldReceiveAStatuscode(int statusCode) {
        assertThat(response1.getStatusCode(), equalTo(statusCode));
    }
    @Then("the response should contain tokens idToken, accessToken, refreshToken")
    public void theResponseShouldContainTokens() {
        String actualIdToken = response1.jsonPath().getString("data.idToken");
        String actualAccessToken = response1.jsonPath().getString("data.accessToken");
        String actualRefreshToken = response1.jsonPath().getString("data.refreshToken");

        assertThat(response1.jsonPath().getString("data.idToken"), notNullValue());
        System.out.println("IdToken :"+response1.jsonPath().getString("data.idToken"));
        assertThat(response1.jsonPath().getString("data.accessToken"), notNullValue());
        System.out.println("accessToken :"+response1.jsonPath().getString("data.accessToken"));
        assertThat(response1.jsonPath().getString("data.refreshToken"), notNullValue());
        System.out.println("RefreshToken :"+response1.jsonPath().getString("data.refreshToken"));

        assertThat(actualIdToken, is(notNullValue()));  // Make sure it's not null
        assertThat(actualAccessToken, is(notNullValue()));
        assertThat(actualRefreshToken, is(notNullValue()));
    }
    @When("the user provides the login details with below details:")
    public void the_user_provides_the_login_details_with_below_details(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps(String.class, String.class).get(0);
        String email = data.get("email");
        String password = data.get("password");
        response2 = RestAssured.given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(Config.BASE_URL+ loginEndpoint);
    }
    @Then("I should receive a {int} statuscode for invalid")
    public void iShouldReceiveAStatuscodeForInvalid(int statusCode) {
        assertThat(response2.getStatusCode(), equalTo(statusCode));
    }
    @And("the response should contain the error message {string}")
    public void the_response_should_contain_the_error_message(String message) {
        System.out.println("Response Body: " + response2.getBody().asString());
        String actualErrorMessage = response2.jsonPath().getString("errorMessage");
        String staticPartOfExpectedMessage = message;
        Assert.assertTrue(actualErrorMessage.startsWith(staticPartOfExpectedMessage), "Error message does not match");
    }
}
