package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingsDatesPojo {
    //1. Tum keyler icin private variable'lar olusturuyoruz

    private String checkin;
    private String checkout;

    //2. Tum paramerelerle paramatleri ve parametrasiz consturctor olustuyoruyorz


    public BookingsDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingsDatesPojo() {
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingsDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
