package pricing;

/**
 * The Current_Price class represents the current_price in the JSON objects that are returned from the GET and received
 * in the PUT message. It uses some specialized classes for money to help protect against bad data being inserted. It
 * serializes to JSON as:
 * {"current_price":{"value":23.42,"currency_code":"USD"}}
 */

import java.math.BigDecimal;
import java.util.*;

public class Current_Price {
    //Using a BigDecimal here to handle a dollar amount. Don't allow wacky prices
    private BigDecimal value;
    // Protects against bad currencies.
    private Currency currency_code;

    public Current_Price() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        if (value.scale() == 2) {
            this.value = value;
        }
        else{
            throw new NumberFormatException();
        }
    }

    public Currency getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(Currency currency_code) {
        this.currency_code = currency_code;
    }
}
