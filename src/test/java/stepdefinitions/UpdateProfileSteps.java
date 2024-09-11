package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UpdateProfileSteps {

    private Response updateProfileResponse;
    private Response loginResponse;
    private String loginEndpoint;
    private String profileEndpoint;
    String idToken;
    String name;
    private Properties config;
    public UpdateProfileSteps() {
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Given("the url is fetched from Config file")
    public void theUrlIsFetchedFromConfigFile() {
        loginEndpoint = "/login";
    }
    @And("the user is authenticated with email and password details")
    public void theUserIsAuthenticatedWithEmailAndPasswordDetails() {
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        loginResponse = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("checking the response contains a correct valid id token")
    public void checkingTheResponseContainsACorrectValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }
    @Given("the user has a valid token and is authenticated {string}")
    public void theUserHasAValidTokenAndIsAuthenticated(String endpoint) {
        profileEndpoint=endpoint;
    }
    @When("the user sends a PUT request to the profile endpoint with the updated {string}, {string}, {string}")
    public void theUserSendsAPUTRequestToTheProfileEndpointWithTheUpdated(String fullName, String target, String preferableActivity) {
        // Sending the PUT request to the API with the updated profile information
        updateProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .body("{\"fullName\":\"" + fullName + "\", \"target\":\"" + target + "\", \"preferableActivity\":\"" + preferableActivity + "\"}")
                .put(Config.BASE_URL+profileEndpoint);
        name=fullName;
    }
    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, updateProfileResponse.getStatusCode());
    }
    @And("the success message should be {string}")
    public void theSuccessMessageShouldBe(String message) {
        String Message = updateProfileResponse.jsonPath().getString("message");
        Assert.assertEquals(message, Message);
    }

    @And("check the name is updated or not")
    public void checkTheNameIsUpdatedOrNot() {
        Response getProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .when()
                .get(Config.BASE_URL + profileEndpoint);
        assertEquals(getProfileResponse.jsonPath().getString("data.fullName"),name);
    }
}

