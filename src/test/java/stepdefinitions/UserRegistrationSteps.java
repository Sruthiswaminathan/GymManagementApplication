package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
public class UserRegistrationSteps {
    private Response response;
    @Given("I am a user")
    public void i_am_a_user() {
        System.out.println("I am a user");
    }

    @When("I send a POST request to the {string} endpoint with the following details:")
    public void i_send_a_post_request_to_the_endpoint_with_the_following_details(String endpoint, DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        response = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+endpoint);
    }

    @Then("I should receive a {int} status")
    public void i_should_receive_a_status(Integer status) {
        assertThat(response.statusCode(), is(status));
    }
    @Then("the response should contain a message {string}")
    public void the_response_should_contain_a_message(String message) {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        if (message.contains("<email>")) {
            String actualMessage = response.jsonPath().getString("errorMessage");
            assertTrue(actualMessage.contains("User with email"));
            assertTrue(actualMessage.contains("already exists"));
        } else {
            assertTrue(responseBody.contains(message));
        }
    }
}