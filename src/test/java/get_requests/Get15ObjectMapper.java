package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {

    /*
    Given
	            https://restful-booker.herokuapp.com/booking/2
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get01() {
        //set the url
        spec.pathParams("first", "booking", "second", 22);

        //Set the expected data
        HerOkuAppTestData expectedData = new HerOkuAppTestData();
        String expectedDataString = expectedData.expectedDataInString("Oliver", "Smith", 100, true, "2022-07-18",
                "2022-07-19", "Breakfast");

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedDataString, BookingPojo.class);

        //send the Get request, get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);

        //do assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());

        //Soft Assertion
        //1.adim SoftAssert objesi
        SoftAssert softAssert=new SoftAssert();
        //2.adim: Assertion Yap
        softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname(),"firstname uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname(), "lastname uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice(), "total price uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid(),"depositpaid uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin() ,"checkin uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout(),"checkout uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds(), "additional needs uyusmadi");
        //3. assertAll() ekle
        softAssert.assertAll();
    }
}
