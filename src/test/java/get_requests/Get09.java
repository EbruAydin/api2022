package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void get01() {
        //1.Step: get url
        spec.pathParams("first", "booking", "second", 91);

        //2.Step: Set the expected data
        Map<String, String> bookingsdatesMap = new HashMap<>();//inner map
        bookingsdatesMap.put("checkin", "2018-01-01");
        bookingsdatesMap.put("checkout", "2019-01-01");

        Map<String, Object> expectedDataMap=new HashMap<>();//outer map
        expectedDataMap.put("firstname", "James");
        expectedDataMap.put("lastname", "Brown");
        expectedDataMap.put("totalprice", 111);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingsdatesMap);
        expectedDataMap.put("additionalneeds", "Breakfast");

        //3.Step: send the request get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        Map<String, Object> actualDataMap=response.as(HashMap.class);

        //4.Step:Do assertion
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        assertEquals(bookingsdatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingsdatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
        //burada map devrede oldugu iicin checkin ve checkout almamiz lazim
        //actualDataMap icerisinde object var, onu map'e ceviriyoruz (Map) ile

        assertEquals(expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));
    }
}
