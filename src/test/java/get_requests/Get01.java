package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;//given yerine buraya yildiz koyunca when, then, and import edilmis oldu

public class Get01 {

    /*
    Postman manuel API testi icin kullanilir.
    Rest API kullaniyoruz burada.
    API otomasyonu icin Rest-Assured Library'den dependencies import ederiz.
    Otomasyon kodlarinin yazimi icin su adimlari izliyoruz:

        a)understand requirement/gereksinimleri anlamak
        b)Test Case'i yazma
            i)Test Case yazimi icin Gherkin language kullaniliyoruz
                 Gherkin bazi keyword'lere sahip.Bunlar:
                 x)Given:onkosullar
                 y)When: Aksiyonlar
                 z) Then:donutler
                 t) And:Coklu islemler icin kullanilir. mesela ardarda kiyaslama yapacagiz, Then demek yerine And deriz

        c)Testing kodunun yazimi:
        //her API'da bu adimlari izleyecegiz
                i)set the URl/endpoint
                ii)set the expected data(POST-PUT-PATCH)
                iii) Type code to send a request
                iv)do assertion ==dogrulama yapmis olacagiz
     */

    /*
    Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01() {
        // i)set the URl/endpoint
        String url="https://restful-booker.herokuapp.com/booking/111";

        //  ii)set the expected data(POST-PUT-PATCH) -- bunu atlariz cunku data ile ilgilenmiyoruz, GET ile ilgileniyoruz

        // iii) Type code to send a request

        //burada yukaridaki task adimlarini kullaniyorzu
        Response response=given().when().get(url); //response atamasi yaparak data elde etmis yaptik

        // response.prettyPrint();  //bu sekilde sout gibi yazdirmis oluyoruz ama bu icindeki datalari yazdirir

        //response icerisinde oldugu icin butun datalar ondan devam edebiliriz
        // iv)do assertion ==dogrulama yapmis olacagiz
        response.then()
                .assertThat()//assertThat kendinden sonrakileri dogrulamak demek
                .statusCode(200) //200 oldugunu dogrula
                .contentType("application/json")//json formatinda olup olmadigini dogrula
                .statusLine("HTTP/1.1 200 OK");// bu oldgunu dogrular

        //'Status Code' masil yazdirilir?
        System.out.println("statusCode : "+response.statusCode());// bizim yukarida assert ettigimiz bilgileri su sekilde yazdiririz.

        //'Contain Type' masil yazdirilir?
        System.out.println("containType : "+response.contentType());

        //'Status Line' masil yazdirilir?
        System.out.println("statusLine : "+response.statusLine());

        //======================header nasil yazdirilir=============
        //header kismina dair methodlar. Postman icerisindeki header kismini yazdiriyor

        System.out.println(response.header("connection")); // icerisinde string olani seciyoruz
        System.out.println(response.header("host")); //null
        System.out.println(response.header("user-agent"));// null bunlari null vermesinin nedeni API ile alakali

        //TUM HEADERS YAZILMASI ICIN
        System.out.println("Headers : \n"+ response.headers());

        //"time" nasil yazdirilir?
        System.out.println("Time :"+response.getTime());

    }
}
