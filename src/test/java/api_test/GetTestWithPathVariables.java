package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTestWithPathVariables extends BaseAPIClass {
    @Test
    public void getSingleUser() {
        LOGGER.info(testCaseName);

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server.
        // The server is specified in the BaseURI that we have specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the HTTP Method type.
        // This will return the Response from the server. Store the response in a variable.
        String id = "2";
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        // Assert that the correct response status code is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        // Get the jsonPath object from the response
        JsonPath jsonPath = response.jsonPath();
        String actualEmailId = jsonPath.get("data.email");

        // Validate that a specific user email does exist in the response body.
        String expectedEmailId = "janet.weaver@reqres.in";
        Assert.assertEquals(actualEmailId, expectedEmailId);

        LOGGER.info(testCaseName);
    }

    @Test
    public void attemptToGetUserWithInvalidId() {
        LOGGER.info("--------API Test: Attempt to retrieve user with invalid id----------");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server.
        // The server is specified in the BaseURI that we have specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the HTTP Method type.
        // This will return the Response from the server. Store the response in a variable.
        String id = "23";
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        // Assert that the correct response status code is returned.
        Assert.assertEquals(response.getStatusCode(), 404);

        // Get the jsonPath object from the response
        JsonPath jsonPath = response.jsonPath();
        String actualResponse = jsonPath.get().toString();

        // Validate that response body is empty
        Assert.assertEquals(actualResponse, "{}");

        LOGGER.info("--------End Test: Attempt to retrieve user with invalid id----------");
    }
}
