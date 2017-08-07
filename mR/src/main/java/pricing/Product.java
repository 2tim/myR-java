package pricing;

import org.springframework.data.annotation.Id;

/**
 * The Product class is defined as a serializable class for representing the return value of the HTTP GET request.
 * It contains the price from the local repo as well as the title or name from the remote resource. The JSON serialized
 * representation of this class would look like this:
 * {"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":23.42,"currency_code":"USD"}}
 */

public class Product {
    @Id
    private long id;
    private String name;
    private Current_Price current_price;

    public Product(long productId, String name, Current_Price price) {
        this.id = productId;
        this.name = name;
        this.current_price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
