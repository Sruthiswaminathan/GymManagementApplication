package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
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
    //login
    @Given("the API endpoint is present at Config")
    public void theAPIEndpointIsPresentAtConfig() {
        criteriaEndpoint = "/criteria";
        loginEndpoint = "/login";
    }
    @And("the user is authenticated correct credintials with email and password")
    public void theUserIsAuthenticatedCorrectCredintialsWithEmailAndPassword(DataTable dataTable) {
        Map<String,String> userDetails = dataTable.asMap(String.class, String.class);
        loginResponse = given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(Config.BASE_URL+loginEndpoint);

    }
    @And("the response stores the valid id token")
    public void theResponseStoresTheValidIdToken() {
        idToken = loginResponse.jsonPath().getString("data.idToken");
    }

    //getcriteria
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
        System.out.println("Response Body:"+getResponse.getBody().asString());
    }
    @Then("I should receive a statuscode as {int} for get request")
    public void i_should_receive_a_status_code(Integer statusCode) {
        assertThat(getResponse.getStatusCode(), equalTo(statusCode));
    }

}
