package pricing;

import org.springframework.data.annotation.Id;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Price {

    @Id
    private long id;
    private float value;
    private String currency_code;
    private Map<String, String> current_price;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

//    public Map<Float, String> getCurrentPrice() {
//
//        Map<Float, String> currentPrice = new HashMap<>();
//        currentPrice.put(value, currency_code);
//        return currentPrice;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //TODO: Add a custom serializer for the price value to match the example.
    public Map<String, String> getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Map<String, String> current_price) {
//        Map.Entry e = new AbstractMap.SimpleEntry<String, AbstractMap.SimpleEntry>(1, "test");
        this.current_price = current_price;
    }
}