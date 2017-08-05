package pricing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final String template = "http://redsky.target.com/v2/pdp/tcin/%s?excludes=taxonomy,price,promotion" +
            ",bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    public RequestController() {

    }

    public String getDetails(String id) {

        RestTemplate restTemplate = new RestTemplate();
        Redsky rs = restTemplate.getForObject(String.format(template, id), Redsky.class);
        log.info(rs.toString());
        return rs.toString();
    }

}
