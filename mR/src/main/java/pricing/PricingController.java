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

@RestController
public class PricingController {

    @Autowired(required = false)
    private DataStore repo;
    private Price price;
    private static final Logger log = LoggerFactory.getLogger(PricingController.class);

    @RequestMapping(value="/products/{id}",
                    method=GET,
                    produces = "application/json;")
    @ResponseBody
    public Product getpricing(@PathVariable(value="id") long id) {
        RequestController redsky = new RequestController();
        String title = redsky.getTitle(id);
        Price price = repo.findById(id);
        return new Product(id, title, price.getCurrent_price());
    }

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
