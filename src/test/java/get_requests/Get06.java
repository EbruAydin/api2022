package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "GGS",
            "lastname": "FINCH",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            }

        }
     */

    @Test
    public void get01(){

        //1.Step: Set the Url
        spec.pathParams("first", "booking", "second", 101);
        //2. Set the expected data
        //3.Send the request and get the respond
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("GGS"),
                        "lastname", equalTo("FINCH"),
                        "totalprice", equalTo(111), "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"));

        // nested olanlarda . koyuyoruz mesela bookingdates nested ve checkin'e ulasmak istiyorsak
        // bookingdates.checkin diyoruz.

        // 2. Yontem---> JsonPath

        JsonPath json=response.jsonPath();
        assertEquals("GGS",json.getString("firstname"));
        assertEquals("FINCH",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertEquals(true, json.getBoolean("depositpaid"));
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));

        // 3. Yontem ---> soft assertion
        // Soft Assertion icin 3 adim izlenir;

        SoftAssert softAssert=new SoftAssert();

        //2) obje arciligi ile assert yapilir
        softAssert.assertEquals(json.getString("firstname"), "GGS", "firstname uyusmadi");
        softAssert.assertEquals(json.getString("lastname"), "FINCH", "lastname uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"), 111, "total price uyusmadi");
        softAssert.assertEquals(json.getBoolean("depositpaid"), true, "depositpaid uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2018-01-01", "checkin bilgisi uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2019-01-01", "checkout bilgisi uyusmadi");


        // assertAll() methodu kullanilir, aksi takdirde kod her zaman pass olur.
        softAssert.assertAll();




    }
}
