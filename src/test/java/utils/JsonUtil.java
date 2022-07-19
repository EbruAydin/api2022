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
            throw new RuntimeException(e);
        }

        return javaResult;
    }
    //2.Method=Java objesini json datasina cevirir (Serialisation)

}
