package apitesting.stepdefinitions;

import apitesting.apiurl.Config;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class UserRegistrationSteps {
    private Response response;
    @Given("I am a user")
    public void iAmAUser() {
        System.out.println("I am a user");
    }
    @When("I send a POST request to the {string} endpoint with the following details:")
    public void iSendAPostRequestToTheEndpointWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        response = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+endpoint);
    }

    @Then("I should receive a {int} status")
    public void iShouldReceiveAStatus(Integer status) {
        assertThat(response.statusCode(), is(status));
    }
    @Then("the response should contain a message {string}")
    public void theResponseShouldContainAMessage(String message) {
        if(response.statusCode()==201){
            String actualMessage = response.jsonPath().getString("message");
            assertEquals(actualMessage, message);
        }
        else {
            String actualMessage = response.jsonPath().getString("errorMessage");
            assertTrue(actualMessage.startsWith(message));
        }
    }
}