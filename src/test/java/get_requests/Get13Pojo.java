package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Akshita Nehru",
                "email": "nehru_akshita@jast.info",
                "gender": "female",
                "status": "active"
            }
        }
            }
     */

    @Test
    public void get01() {

        //set the url
        spec.pathParams("first", "users", "second", 2508);

        //set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508, "Akshita Nehru", "nehru_akshita@jast.info", "female", "active");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null, goRestDataPojo);

        //send the GET request. get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        GoRestResponseBodyPojo actualData= response.as(GoRestResponseBodyPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(goRestResponseBodyPojo.getMeta(), actualData.getMeta());
        assertEquals(goRestResponseBodyPojo.getData().getId(),actualData.getData().getId());
        assertEquals(goRestResponseBodyPojo.getData().getName(), actualData.getData().getName());
        assertEquals(goRestResponseBodyPojo.getData().getEmail(), actualData.getData().getEmail());
        assertEquals(goRestResponseBodyPojo.getData().getGender(),actualData.getData().getGender());
        assertEquals(goRestResponseBodyPojo.getData().getStatus(),actualData.getData().getStatus());


    }
}
