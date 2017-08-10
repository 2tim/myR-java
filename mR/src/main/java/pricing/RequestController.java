package pricing;


import com.fasterxml.jackson.databind.JsonNode;
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
        RestTemplate restTemplate = new RestTemplate();
        log.info(String.format(template, id));
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(template, id), String.class);
        try {
            JsonNode root = fieldMapper.readTree(response.getBody());
            String title = root.path("product").path("item").path("product_description").path("title").asText();
            return title;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "FAILURE";
    }

}
