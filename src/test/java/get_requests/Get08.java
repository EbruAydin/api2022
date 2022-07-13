package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    //De-serialisation:Json formatindan java objesine cevirme
    //Serialisation: Java objesini json formatina cevirme
    //De-serialisation ve serialisation iki turlu yapilir:
    //Gson: google tarafindan uretilmistir
    //Object mapper: Daha populerdir
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get01() {

        spec.pathParams("first", "todos", "second", 2);
        //2.set the expected data

        //De-serialisation method
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        //3. Step: Send the request, get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        // burada response HashMap class'ina cevrilmis olundu ve Mapp'a atandi ki expectedData ile kiyas yapalim
        Map<String, Object> actualData = response.as(HashMap.class);

        //4. step: Do assertion
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("statusCode"), response.getStatusCode()); // response icersinde oldugu icin
                                                                    // response uzerinden dogrudan cagirabiliriz
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

    @Test
    public void get02(){
        spec.pathParams("first", "todos", "second", 2);

        //2.set the expected data
        JsonPlaceHolderTestData expectedData=new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap=expectedData.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui", false);
        expectedDataMap.put("StatusCode", 200);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");
        //3. Step: Send the request, get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String, Object> actualData = response.as(HashMap.class);

        //4. step: Do assertion
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode()); // response icersinde oldugu icin
        // response uzerinden dogrudan cagirabiliriz
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));

    }
}
