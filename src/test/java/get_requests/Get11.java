package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Aalok Acharya DDS", "Agastya Somayaji", "Acharyasuta Chattopadhyay DC" are among the users
        And
            The female users are less than or equals to male users
     */

    @Test
    public void get01() {
        //1. Set the url
        spec.pathParam("first", "users");

        //2.set the expected data==>yapisi karisik oldugu icin, map icerisinde birden fazla map oldugu icin map yapmiyoruz
        //3. send the request, get the response
        Response response = given().spec(spec).when().get("/{first}");

        //response.prettyPrint();

        //4. Step: Do Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Pranay Shukla", "Chandraprabha Guneta PhD", "Ms. Kumuda Reddy"));
        /*
        icice bir suru json oldugu icin body methodu ile once disaridaki map yani meta, sonra pagination, sonrasinda
        da limit'i cagiriyoruz. bunu da . koyarak yapiyoruz.

        data kismi list [] bu sekilden anlayabilirsin, bu durumda data[1].id dersen 1. index'teki
        veriyi elde etmis olursun. hasSize ile de listin icerisinde kac tane user oldugunu bulmus oluruz
         */

        //Kadin ve Erkek sayisini karsilastirma
        //1.Yol: Tum cinsiyeti cekip kadin sayisi ile karsilastima

        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");

        int numOfFemales = 0;
        for (String w : genders) {
            if (w.equalsIgnoreCase("female")) {
                numOfFemales++;
            }
        }

       assertTrue(numOfFemales < genders.size() - numOfFemales);


        //2.yol: Tum kadin ve erkekleri ayri ayri Grovay ile cekelim
       List<String> femaleList= json.getList("data.findAll{it.gender=='female'}.gender");
       List<String> maleList= json.getList("data.findAll{it.gender=='male'}.gender");
       assertTrue(femaleList.size()<maleList.size());
    }

}
