package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePatchTest extends BaseAPIClass {
    @Test
    public void updateUserSingleField() {
        LOGGER.info("--------API Test: Update User's single field----------");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server.
        // The server is specified in the BaseURI that we have specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Data creation for the request body
        Faker faker = new Faker();
        String userRole = faker.job().title();
        LOGGER.debug("Updated User Job: " + userRole);

        // Creating the request body
        JSONObject reqBody = new JSONObject();
        reqBody.put("job", userRole);

        // Adding the appropriate header and the request body with the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqBody.toJSONString());

        //Make a request to the server by specifying the HTTP Method type.
        // This will return the Response from the server. Store the response in a variable.
        String id = "2";
        Response response = httpRequest.request(Method.PATCH, id);
        LOGGER.debug(response.getBody().asPrettyString());

        // Assert that the correct response status code is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        // Get the jsonPath object from the response
        JsonPath jsonPath = response.jsonPath();
        String actualJob = jsonPath.getString("job");

        // Validate that use is created successfully
        Assert.assertEquals(actualJob, userRole);

        LOGGER.info("--------End Test: Update User's single field----------");
    }
}
