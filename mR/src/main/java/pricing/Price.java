package pricing;

import org.springframework.data.annotation.Id;

public class Price {

    @Id
    private long id;
    private float value;
    private String currency_code;
    private Current_Price current_price;

    public Price(long id, Current_Price current_price){
        this.id = id;
        this.current_price = current_price;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Current_Price getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Current_Price current_price) {
        this.current_price = current_price;
    }

}