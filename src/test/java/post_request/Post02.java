package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                            }
     */

    @Test
    public void post01() {

        //1.Step:Set the url
        spec.pathParam("first", "booking");

        //2.Step: set the expected data

        HerOkuAppTestData herokuapp = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap = herokuapp.bookingdatesSetUp("2021-09-21", "2021-09-21");

        //burada expectedDataMap olustururken bookingdatesMap'i icersine atarak inner Map'i de icermesini
        //sagliyoruz.
        Map<String, Object> expectedDataMap = herokuapp.expectedDataSetUp("John", "Doe",
                11111, true,
                bookingdatesMap);


        //3.Step: send the post request, get the response
        /*
        bu asamada contentType yapilmaz ise Internal Server Error verecektir
         */
        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedDataMap).when().post("/{first}");


        //4.Step:DO Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
       /*
       asagida actual icin iki adim izlememiz lazim, once booking'e gidecegiz
       daha sonra firstname'e gidecegiz.
        */
        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));


        //checkin ve checkout inner map oldugu icin farkli islem yapiyoruz. yukarida zaten
        // ona ait map olusturduk, bu durumda onun uzerinden hareket edebiliriz

        assertEquals(bookingdatesMap.get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}
