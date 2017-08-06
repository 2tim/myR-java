package pricing;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class PricingController {

    @Autowired
    private DataStore repo;
    private Price price;
    private static final Logger log = LoggerFactory.getLogger(PricingController.class);
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

    //TODO: Maybe make the requests happen on threads to make it faster.
    @RequestMapping(value="/pricing/{id}", method=GET)
    public Product getpricing(@PathVariable(value="id") long id) {
        RequestController redsky = new RequestController();
        String title = redsky.getTitle(id);
        Price price = repo.findById(id);
        return new Product(id, title, price.getCurrent_price());
    }

    @RequestMapping(value="/pricing/{id}", method=PUT)
    public String putpricing(@RequestBody String priceInfo, @PathVariable(value="id") long id) {

        ObjectMapper fieldMapper = new ObjectMapper();
        Map<String, Map> infoMap;
        log.info(priceInfo);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(priceInfo, String.class);
        try {

            Price price = fieldMapper.readValue(priceInfo, Price.class);
//            infoMap = fieldMapper.readValue(priceInfo, Map.class);
////
//            long productId = infoMap.get("id");
//            Map<String,Map> itemMap = infoMap.get("current_price");
//            Map<String,String> value = itemMap.get("value");
//            Map<String,String> currency_code = itemMap.get("currency_code");
//            Map<String,String> prodDescrMap = itemMap.get(("product_description"));
////            String title = prodDescrMap.get("title");
////            log.info(title);
////            return title;
//            price = new Price();
//            price.setProductId(productId.get("id"));
//            price.setValue(Float.parseFloat(value.get("value")));
//            price.setCurrency_code(currency_code.get("currency_code"));
//            price.setCurrency_code();
            log.info("Adding price!!!!!");
            repo.save(price);
            return "{\"status\":\"success\"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
//        RequestController redsky = new RequestController();
//        String title = redsky.getTitle(id);
//        Price price = repo.findByProductId(id);
//        log.info("Adding price for:");
//        log.info(String.format("%d : %f : %s", id, priceInfo.getValue(), priceInfo.getCurrency_code()));
//        repo.save(priceInfo);
        return "{\"status\":\"failed\"}";
    }

}
