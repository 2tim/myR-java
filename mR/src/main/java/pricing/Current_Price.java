package pricing;

import java.math.BigDecimal;
import java.util.*;

public class Current_Price {
    //Using a BigDecimal here to handle a dollar amount.
    private BigDecimal value;
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
