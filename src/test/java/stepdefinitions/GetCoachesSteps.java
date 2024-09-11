package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.Matchers.equalTo;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetCoachesSteps {
    private Response getCoachesResponse;
    String token;
    private String coachesEndpoint;
    private String loginEndpoint;
    private Response loginResponse;
    private Properties config;
    public GetCoachesSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Given("the API endpoint is present at the Config file")
    public void theApiEndpointIsPresentAtTheConfigFile() {
        loginEndpoint = "/login";
    }
    @Given("the user is authenticated with correct credentials")
    public void theUserIsAuthenticatedWithCorrectCredentials() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL + loginEndpoint);
        System.out.println("Response body:" + loginResponse.getBody().asString());
    }

    @Given("the response contains a valid id token")
    public void theResponseContainsAValidIdToken() {
        token = loginResponse.jsonPath().getString(("data.idToken"));
    }

    @Given("the API endpoint for coaches is {string}")
    public void theApiEndpointForCoachesIs(String endpoint) {
        coachesEndpoint = endpoint;
    }

    @When("I send a GET request to the coaches endpoint")
    public void iSendAGetRequestToTheCoachesEndpoint() {
        getCoachesResponse = given()
                .header("Authorization", "Bearer " + token)
                .get(Config.BASE_URL + coachesEndpoint);
    }

    @Then("the response should be {int}")
    public void theResponseShouldBe(int statusCode) {
        assertEquals(getCoachesResponse.getStatusCode(),statusCode);
    }

    @And("the response message for retrieve coaches should be {string}")
    public void theResponseMessageForRetrieveCoachesShouldBe(String expectedMessage) {
        assertEquals(getCoachesResponse.jsonPath().getString("message"),expectedMessage);
    }
}


 