package utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {


    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    //1.Method=Json datasini java objesine ceviri. (De-serialisation)
    public static <T> T convertJsonToJavaObject(String json, Class<T> cls) { //generic method
        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.getStackTrace();//butun mesajlari bize verecek
        }

        return javaResult;
    }
    /*
    T olmasi demek type demek ve generic bir yapi sunuyor bize. Yani ham bir method elimizde
    biz onun turunu daha sonrasinda turune dair atama yapacagiz.
     */

    //2.Method=Java objesini json datasina cevirir (Serialisation)

    public static String convertJavaObjectToJson(Object obj){
        String jsonResult=null;

        try {
            jsonResult=mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.getStackTrace();
        }

        return jsonResult;
    }
}
