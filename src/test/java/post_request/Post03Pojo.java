package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01() {
        //1. Step: set the url
        spec.pathParam("first", "todos");
        //2.Step: set the expected data
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3. Send Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");

        //4.Do Assertion
        JsonPlaceHolderPojo actualBody= response.as(JsonPlaceHolderPojo.class);
        /*
        bize reponse olarak donen Response formatinda, ama bizim actual data olusturma asamasinda
        actualBody create etmek icin response'in dayandigi ilk adim
        requestBody idi. RequestBody formati da JsonPlaceHolderPojo oldugu icin
        actualBody icerisinde de ona atama yapiyoruz.
        HashMap'e cevirdigimiz zaman nasil ki Map'e atiyoruz, ayni sekilde yapiyoruz burada da
        datalari benzetiyoruz.
         */

           // assertEquals(requestBody.getUserId(), actualBody.getUserId());
           // assertEquals(requestBody.getTitle(), actualBody.getTitle());
           // assertEquals(requestBody.getCompleted(), actualBody.getCompleted());

        assertEquals(requestBody.toString(), actualBody.toString());
    }

}
