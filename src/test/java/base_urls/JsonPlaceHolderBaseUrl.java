package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    /*
    uri -> identification anlamina geliyor burasi. ve diyoruz ki nokta atisi yap
    yani uri ile dogrudan hedefe ulasmis oluyoruz ki API'nin mevzusu da nokta atisi
    yaparak data testi yapmaktir.
     */
    protected RequestSpecification spec;

    @Before
    public void setUp(){

        spec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
    }

}
