package pricing;

import org.springframework.data.annotation.Id;

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
