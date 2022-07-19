package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;
import pojos.BookingsDatesPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */


    @Test
    public void postPojo01() {
        //1. set the url
        spec.pathParams("first", "booking");

        //Set the expected data
        BookingsDatesPojo bookingDates = new BookingsDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali", "Can", 999, true, bookingDates, "Breakfast with white tea, Dragon juice");

        //send POST request and GET the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");

        //do assertion
       BookingResponseBodyPojo actualPojo= response.as(BookingResponseBodyPojo.class);

       assertEquals(200,response.statusCode());
       assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
       assertEquals(bookingPojo.getLastname(),actualPojo.getBooking().getLastname());
       assertEquals(bookingPojo.getTotalprice(),actualPojo.getBooking().getTotalprice());
       assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
       assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());
       assertEquals(bookingPojo.getAdditionalneeds(), actualPojo.getBooking().getAdditionalneeds());

       //2.yol-checkin checout icin
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());

    }
}
