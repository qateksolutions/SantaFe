package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithQueryParameter extends BaseAPIClass {
    @Test
    public void getUserWithQueryParameter() {
        LOGGER.info("--------API Test: Get All Users with Query Parameter----------");

        // Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to send to the server.
        // The server is specified in the BaseURI that we have specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the HTTP Method type.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.queryParam("page", "2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        // Assert that the correct response status code is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        // Get the jsonPath object from the response
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        // Validate that a specific user email does exist in the response body.
        String emailId = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(emailId);
        Assert.assertTrue(emailExist, emailId + " does not exist");

        LOGGER.info("--------End Test: Get All Users with Query Parameter----------");
    }
}
