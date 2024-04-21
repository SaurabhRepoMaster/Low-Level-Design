package org.splitWise.model;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class BalanceMap {

    private final Map<String, Amount> balances;

    public Map<String, Amount> getBalances() {
        return balances;
    }
    public BalanceMap(Map<String, Amount> resultBalances) {
        this.balances = resultBalances;
    }


    public BalanceMap(){
        balances= new HashMap<>();
    }
}
