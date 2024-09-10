package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateProfileSteps {

    private Response updateProfileResponse;
    private Response loginResponse;
    private String loginEndpoint;
    private String profileEndpoint;
    private Response getProfileResponse;
    String idToken;
    String name;
    //login
    @Given("the url is fetched from Config file")
    public void theUrlIsFetchedFromConfigFile() {
        loginEndpoint = "/login";
        System.out.println(loginEndpoint + " "+ profileEndpoint);
    }
    @And("the user is authenticated with email and password details")
    public void theUserIsAuthenticatedWithEmailAndPasswordDetails(DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        loginResponse = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+loginEndpoint);
    }
    @And("checking the response contains a correct valid id token")
    public void checkingTheResponseContainsACorrectValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }

    //updateprofile
    @Given("the user has a valid token and is authenticated {string}")
    public void theUserHasAValidTokenAndIsAuthenticated(String endpoint) {
        profileEndpoint=endpoint;
    }
    @When("the user sends a PUT request to the profile endpoint with the updated {string}, {string}, {string}")
    public void the_user_sends_a_PUT_request_to_the_profile_endpoint_with_the_updated(String fullName, String target, String preferableActivity) {
        // Sending the PUT request to the API with the updated profile information
        updateProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .body("{\"fullName\":\"" + fullName + "\", \"target\":\"" + target + "\", \"preferableActivity\":\"" + preferableActivity + "\"}")
                .put(Config.BASE_URL+profileEndpoint);
        name=fullName;
        System.out.println("Response Body:"+updateProfileResponse.getBody().asString());
    }
    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, updateProfileResponse.getStatusCode());
    }
    @And("the success message should be {string}")
    public void the_success_message_should_be(String message) {
        System.out.println("Response Body: " + updateProfileResponse.getBody().asString());
        String Message = updateProfileResponse.jsonPath().getString("message");
        Assert.assertTrue(Message.equals(message));
    }

    @And("check the name is updated or not")
    public void checkTheNameIsUpdatedOrNot() {
        getProfileResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .when()
                .get(Config.BASE_URL+ profileEndpoint);
        System.out.println("Response body:"+getProfileResponse.getBody().asString());
    }
}

