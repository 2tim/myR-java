package pricing;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(RequestController.class);
    private static final String template = "http://redsky.target.com/v2/pdp/tcin/%d?excludes=taxonomy,price,promotion" +
            ",bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    public RequestController() {

    }

    public String getTitle(long id) {

        ObjectMapper fieldMapper = new ObjectMapper();
        Map<String, Map> infoMap;
        RestTemplate restTemplate = new RestTemplate();
        log.info(String.format(template, id));
//        Redsky rs = restTemplate.getForObject(String.format(template, id), Redsky.class);
//        Product prod = restTemplate.getForObject(String.format(template, id), Product.class);
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(template, id), String.class);
        try {
            infoMap = fieldMapper.readValue(response.getBody(), Map.class);
            Map<String,Map> productMap = infoMap.get("product");
            Map<String,Map> itemMap = productMap.get("item");
            Map<String,String> prodDescrMap = itemMap.get(("product_description"));
            String title = prodDescrMap.get("title");
            log.info(title);
            return title;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info(prod.getItem().getDescription().getItem().toString());
//        log.info(rs.getProduct().toString());
//        return rs.getProduct().getDescription().getItem().toString();
        return "FAILURE";
    }

}
