package pricing;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * The Pricing Controller acts as a "Controller" via the @RestController annotation. This conveniently tells the Spring
 * Boot framework to direct API requests to this class for handling. The DataStore instance, "repo" is @Autowired. This
 * allows for easy setup and use of the dataStore object. Methods are defined below for handling both a GET and PUT
 * request.
 */
@RestController
public class PricingController {

    @Autowired(required = false)
    private DataStore repo;
    private Price price;
    private static final Logger log = LoggerFactory.getLogger(PricingController.class);

    /**
     * The getpricing method is defined as a responder to GET requests that provide a numeric Product ID. The id is
     * requsted from the redsky site, the title is returned and then the price is reqested from the repo. The data is
     * combined into a Product object and returned and serialized into JSON in the response.
     * @param id
     * @return Product
     */
    @RequestMapping(value="/products/{id}",
                    method=GET,
                    produces = "application/json;")
    @ResponseBody
    // Product
    public ResponseEntity getpricing(@PathVariable(value="id") long id) {
        RequestController redsky = new RequestController();
        try {
            String title = redsky.getTitle(id);
            Price price = repo.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Product(id, title, price.getCurrent_price()));
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"No Product found\"}");
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"No Price found\"}");
        }
    }

    /**
     * The putpricing method is defined as a responder to PUT requests and processes a JSON object containing the
     * "value" and "curreny_code". If the currency_code or value fail to process then either the Current_Price
     * constructor or Price constructor will raise and exception and a error status will be returned.
     * @param priceInfo
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value="/products/{id}",
                    method=PUT,
                    produces = "application/json; charset=UTF-8",
                    consumes = "application/json;")
    @ResponseBody
    public ResponseEntity putpricing(@RequestBody String priceInfo, @PathVariable(value="id") long id) {
        ObjectMapper fieldMapper = new ObjectMapper();
        log.info(priceInfo);
        try {
            Current_Price cp = fieldMapper.readValue(priceInfo, Current_Price.class);
            Price price = new Price(id, cp);
            log.info("Adding price!!!!!");
            repo.save(price);
            return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"success\"}");
        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\":\"failed\"}");
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"failed\"}");
        }
    }

}
