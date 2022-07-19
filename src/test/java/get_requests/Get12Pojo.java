package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;
import pojos.BookingsDatesPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get12Pojo extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/18
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 112,
            "depositpaid": true,
            "bookingdates": {
                "checkin": ""2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
        }
     */

    @Test
    public void getPojo01(){
        //set the url
        spec.pathParams("first", "booking", "second", 147);

        //set the expected data
        BookingsDatesPojo bookingDatesPojo=new BookingsDatesPojo("2013-02-23","2014-10-23");
        BookingPojo bookingPojo=new BookingPojo("Sally", "Brown",111, true,bookingDatesPojo, "Breakfast");

        //send the request, get the response

       Response response= given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        BookingPojo actualData= response.as(BookingPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(bookingPojo.getFirstname(), actualData.getFirstname());
        assertEquals(bookingPojo.getLastname(),actualData.getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualData.getTotalprice());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(bookingPojo.getAdditionalneeds(), actualData.getAdditionalneeds());
    }
}
