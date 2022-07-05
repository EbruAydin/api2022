import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.Serializers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get03 extends BaseUrls {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */


    @Test
    public void get01() {

        //1. Step: Set the url
        /*
        String url="https://jsonplaceholder.typicode.com/todos/23";
        simdi bu sekilde yazmak yerine asgidakileri pathParams ile ekleyerek
        dinamik hale getirmis oluyoruz
         */
        spec.pathParams("first", "todos", "second", "23");

        //2,Step: set the expected data

        //3.Send the request and get the request
        Response response = given().spec(spec).when().get("/{first}/{second}");

        // 4. Step: Do Assertion
        //1. yol
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

        //2. yol
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        , "completed", equalTo(false), "userId", equalTo(2));
    }
}
