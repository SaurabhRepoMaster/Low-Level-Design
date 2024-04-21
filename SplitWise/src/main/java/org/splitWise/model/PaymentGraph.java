package org.splitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
public class PaymentGraph {

    private final Map<String,BalanceMap> graph;

}
