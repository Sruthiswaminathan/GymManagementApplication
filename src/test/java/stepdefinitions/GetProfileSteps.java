package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import java.util.Map;
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
//login
    @Given("the API endpoint is present at Config file")
    public void theAPIEndpointIsPresentAtConfigFile() {
        loginEndpoint = "/login";
    }
    @When("the user is authenticated correct credentials with above email and password")
    public void theUserIsAuthenticatedCorrectCredentialsWithAboveEmailAndPassword(DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        loginResponse = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+loginEndpoint);
        System.out.println("Response body:"+loginResponse.getBody().asString());
    }
    @And("the response contains the valid id token")
    public void theResponseContainsTheValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
        System.out.println(idToken);
    }


    //getprofile
    @Given("the API endpoint for profile is {string}")
    public void the_api_endpoint_is(String endpoint) {
        profileEndpoint = endpoint;
    }
    @When("I send a GET request to the profile endpoint")
    public void i_send_a_get_request_to_the_profile_endpoint() {

        getProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .when()
                .get(Config.BASE_URL+ profileEndpoint);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertThat(getProfileResponse.getStatusCode(), equalTo(statusCode));
    }
    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        String actualMessage = getProfileResponse.jsonPath().getString("message");
        if (actualMessage == null) {
            actualMessage = "";
        }
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @Then("the response should include fullName, email, target, preferableActivity")
    public void the_response_should_include() {
        System.out.println(getProfileResponse.getBody().asString());
        assertThat(getProfileResponse.jsonPath().getString("data.fullName"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.email"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.target"), notNullValue());
        assertThat(getProfileResponse.jsonPath().getString("data.preferableActivity"), notNullValue());
    }


}