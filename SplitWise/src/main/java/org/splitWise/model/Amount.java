package org.splitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Amount {

    private final Currency currency;
    private final double amount;

    public Amount add(Amount amount) {
        return new Amount(currency, this.amount + amount.amount);
    }
}
