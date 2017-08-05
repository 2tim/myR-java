package pricing;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Redsky {

        private String type;
        private Product product;
        private String brandName;

        public Redsky() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Product getValue() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        @JsonProperty("product_description")
        private void unpackNameFromNestedObject(Map<String, String> product_description) {
            brandName = product_description.get("name");
        }

        @Override
        public String toString() {
            return "Quote{" +
                    "type='" + type + '\'' +
                    ", value=" + product +
                    '}';
        }

}
