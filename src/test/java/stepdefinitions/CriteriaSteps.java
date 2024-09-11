package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class CriteriaSteps {
    private String email;
    private String role;
    private Response getResponse;
    private Response putResponse;
    private Response deleteResponse;
    private Response loginResponse;
    private Response postResponse;
    private String loginEndpoint;
    private String criteriaEndpoint;
    private String idToken;
    private Properties config;
    public CriteriaSteps() {
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

    //get
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
        System.out.println("Response body:"+getResponse.getBody().asString());
    }
    @Then("I should receive a status code as {int} for get request")
    public void iShouldReceiveAStatusCode(Integer statusCode) {
        assertThat(getResponse.getStatusCode(), equalTo(statusCode));
    }
    @And("the response body should contain message as {string} for get")
    public void theResponseBodyShouldContainMessageAsForGET(String expectedMessage) {
        if(getResponse.getStatusCode()==200) {
            getResponse.then().body("message", equalTo(expectedMessage));
        }
        else{
            getResponse.then().body("errorMessage", equalTo(expectedMessage));
        }
    }




    //put
    @Given("the user has a valid email ,role and new role to be updated")
    public void theUserHasAValidEmailRoleAndNewRoleToBeUpdated() {
        System.out.println("Given the email,role,new role to be updated");
    }
    @When("the user sends a PUT request to endpoint with BearerToken and the request body")
    public void theUserSendsPutRequestWithBearerTokenAndTheRequestBody(Map<String, String> requestBody) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", requestBody.get("email"));
        body.put("role", requestBody.get("role"));
        putResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .contentType("application/json")
                .body(body)
                .when()
                .put(Config.BASE_URL+"/criteria");
        System.out.println("Response body:"+putResponse.getBody().asString());
    }
    @Then("the response should return a status code {int}")
    public void theResponseShouldReturnAStatusCode(int statusCode) {
        putResponse.then().statusCode(statusCode);
    }
    @And("the response body should contain message as {string} for put")
    public void theResponseBodyShouldContainMessageAsForPUT(String expectedMessage) {
        if(putResponse.getStatusCode()==200) {
            putResponse.then().body("message", equalTo(expectedMessage));
        }
        else{
            putResponse.then().body("errorMessage", equalTo(expectedMessage));
        }
    }


    //post
    @Given("the user has a valid email {string} and role {string} to add")
    public void theUserHasAValidEmailAndRoleToAdd(String email, String role) {
        this.email = email;
        this.role = role;
    }
    @When("the user sends a POST request to add criteria with BearerToken")
    public void theUserSendsAPostRequestToAddCriteria() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("role", role);

        postResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Config.BASE_URL + "/criteria")
                .thenReturn();
        System.out.println("Response body: " + postResponse.getBody().asString());
    }
    @Then("the response should return a status code {int} for POST")
    public void theResponseShouldReturnAStatusCodeForPost(int statusCode) {
        postResponse.then().statusCode(statusCode);
    }
    @And("the response body should contain message {string} for POST")
    public void theResponseBodyShouldContainMessageForPost(String expectedMessage) {
        if(postResponse.getStatusCode()==200) {
            postResponse.then().body("message", equalTo(expectedMessage));
        }
        else{
            postResponse.then().body("errorMessage", equalTo(expectedMessage));
        }
    }


    //delete
    @Given("the user has a valid email {string} to remove and there is {string} admin exists")
    public void theUserHasAValidEmailToRemove(String email1,String email2) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email2);
        body.put("role", "ADMIN");
        this.email = email1;
        postResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Config.BASE_URL + "/criteria")
                .thenReturn();
        System.out.println("Response body: " + postResponse.getBody().asString());

    }
    @When("the user sends a DELETE request to remove criteria with email {string}")
    public void theUserSendsADeleteRequestToRemoveCriteria(String email1) {
        deleteResponse = given()
                .header("Authorization", "Bearer " + idToken)
                .header("Content-Type", "application/json")
                .when()
                .delete(Config.BASE_URL + "/criteria?email=" + email1)
                .thenReturn();
        System.out.println("Response body: " + postResponse.getBody().asString());
    }
    @Then("the response should return a status code {int} for DELETE")
    public void theResponseShouldReturnAStatusCodeForDelete(int statusCode) {
        deleteResponse.then().statusCode(statusCode);
    }
    @And("the response body should contain message {string} for DELETE")
    public void theResponseBodyShouldContainMessageForDelete(String expectedMessage) {
        deleteResponse.then().body("message", equalTo(expectedMessage));
    }


}
