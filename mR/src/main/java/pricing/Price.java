package pricing;

import org.springframework.data.annotation.Id;

/**
 * The Price class is defined as a serializable class for representing the return value of the HTTP GET request.
 * It contains the id and current_price object for representing as part of the Product and for storage in the
 * local repo. The json equivilent would look like: {"id":13860428,"current_price":{"value":23.42,"currency_code":"USD"}}
 */

public class Price {

    @Id
    private long id;
    private Current_Price current_price;

    public Price(long id, Current_Price current_price){
        this.id = id;
        this.current_price = current_price;
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