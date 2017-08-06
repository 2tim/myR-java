package pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class Product {
    @Id
    private final long productId;
//    private final String content;
    private String title;
//    private Current_price current_price;
    private Map<String, String> c_price;

//    @Autowired
//    private DataStore repo;

    public Product(long productId, String title, Map<String, String> price) {
        this.productId = productId;
        this.title = title;
        this.c_price = price;
    }

    public long getProductId() {
        return productId;
    }

//    public String getContent() {
//        return content;
//    }

    public Map<String, String> getC_price() {
        return c_price;
    }

    public void setC_price(Map<String, String> c_price) {
        this.c_price = c_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getProduct() {
//
//        this.price = repo.findByProductId(productId);
//        this.title = new RequestController().getTitle(productId);
//        return title;
//
//    }
}
