import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get01(){

        // 1. Step: Set the url
        String url="https://restful-booker.herokuapp.com/booking/1005";

        //2. Step: Set the expected data (Post-Put-Patch)==> bunu yapmiyoruz
        //3. Step: Type code the send request
        Response response=given().when().get(url);

        //4. Step: Do Assertion
        response
                .then()
                .assertThat()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");


        //Response body'de bulunan speciffik bir veri nasil assert edilir

        assertTrue(response.asString().contains("Not Found"));

        //Response body'de bulunmayan speciffik bir veri nasil assert edilir
        assertFalse(response.asString().contains("TechProEd"));

        assertEquals("Cowboy", response.header("Server"));


    }

}
