package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get01ObjectMapper() {
        //set the url
        spec.pathParams("first", "todos", "second", "198");

        //set the expected url
        String expectedData = "{\n" +
                "   \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                "  \"title\": \"quis eius est sint explicabo\",\n" +
                "  \"completed\": true\n" +
                " }";
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //send the Get request, and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));

    }

    @Test
    public void get02ObjectMapper() {//pojo ile kullanmak en yaygin olanifi
        //set the url
        spec.pathParams("first", "todos", "second", "198");

        //set the expected url

        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        String expectedDataString = expectedData.expectedDataInString(10, "quis eius est sint explicabo", true);
        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedDataString, JsonPlaceHolderPojo.class);

        //send the Get request, and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());
    }
}
