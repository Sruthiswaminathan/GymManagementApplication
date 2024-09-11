package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class GetCriteriaSteps {
    private String email;
    private Response getResponse;
    private Response loginResponse;
    private String loginEndpoint;
    private String criteriaEndpoint;
    private String idToken;
    private Properties config;
    public GetCriteriaSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //login
    @Given("the API endpoint is present at Config")
    public void theAPIEndpointIsPresentAtConfig() {
        criteriaEndpoint = "/criteria";
        loginEndpoint = "/login";
    }
    @And("the user is authenticated correct credentials with email and password")
    public void theUserIsAuthenticatedCorrectCredentialsWithEmailAndPassword() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);

    }
    @And("the response stores the valid id token")
    public void theResponseStoresTheValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }

    @Given("I have a valid email {string} for criteria")
    public void iHaveAValidEmailForCriteria(String email) {
        this.email = email;
    }
    @When("I send a get request")
    public void i_send_a_request(){
        getResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .get(Config.BASE_URL+ criteriaEndpoint + "?email=" + email);
    }
    @Then("I should receive a status code as {int} for get request")
    public void iShouldReceiveAStatusCode(Integer statusCode) {
        assertThat(getResponse.getStatusCode(), equalTo(statusCode));
    }

}
