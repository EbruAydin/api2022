package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {


     /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */

    @Test
    public void get01(){

        //1. Step: Set the Url

        //https://restful-booker.herokuapp.com/booking?firstname=Aaron&lastname=Chen
        spec.pathParam("first","booking").
                queryParams("firstname", "Aaron",
                        "lastname", "Chen");

        //2. Step: Set the expected data

        //3. Step: Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", hasItem(1649));
        //hoca asagidakini yapti
       // assertTrue(response.asString().contains("bookingid"));


        /*
        List<Integer> responseListesi = response.jsonPath().getList("bookingid");
        //System.out.println(responseListesi);
        for (Integer integer : responseListesi) {
            response = given().spec(spec).when().get("/{first}/" + integer);
            //assertResponse.prettyPrint();
            response.then().assertThat().body("firstname", equalTo("Aaron"),
                    "lastname", equalTo("Chen"));
            break;
        }
         */
    }



}