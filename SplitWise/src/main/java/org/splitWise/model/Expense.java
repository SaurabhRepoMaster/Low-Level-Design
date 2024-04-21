package org.splitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Expense {

    private final BalanceMap userBalances;
    private final String title;
    private final String imageUrl;
    private final String description;
    private final String groupId;

    public BalanceMap getUserBalances() {
        return userBalances;
    }
}
