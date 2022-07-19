package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestResponseBodyPojo {

    private Object meta;//bilmedigimiz icin turunu Object olarak degerlendiriyouz
    private GoRestDataPojo data;

    public GoRestResponseBodyPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestResponseBodyPojo() {
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestResponseBodyPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
