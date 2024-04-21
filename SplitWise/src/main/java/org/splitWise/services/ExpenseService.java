package org.splitWise.services;

import org.splitWise.model.Amount;
import org.splitWise.model.BalanceMap;
import org.splitWise.model.Currency;
import org.splitWise.model.Expense;
import org.splitWise.model.PaymentGraph;

import java.util.*;

public class ExpenseService {

    private final Map<String,List<Expense>> groupExpenses;

    public ExpenseService()
    {
        this.groupExpenses = new HashMap<>();
    }

    public List<Expense> getGroupExpenses(String groupId) {
        return groupExpenses.get(groupId);
    }

    public PaymentGraph getPaymentGraph(BalanceMap groupBalances) {
        PriorityQueue<Map.Entry<String, Amount>> positiveAmounts =
                new PriorityQueue<>(Comparator.comparingDouble(balance-> -balance.getValue().getAmount()));
        PriorityQueue<Map.Entry<String, Amount>> negativeAmounts =
                new PriorityQueue<>(Comparator.comparingDouble(balance-> balance.getValue().getAmount()));

        for(var balance : groupBalances.getBalances().entrySet())
        {
            if(balance.getValue().getAmount()>0)
                positiveAmounts.add(balance);
            else
                negativeAmounts.add(balance);
        }

        final var graph = new HashMap<String,BalanceMap>();
        while (!positiveAmounts.isEmpty() && !negativeAmounts.isEmpty())
        {
            //eliminate largest from both
            final var largestPositive = positiveAmounts.poll();
            final var largestNegative = negativeAmounts.poll();
            final var positiveAmount = largestPositive.getValue().getAmount();
            final var negativeAmount  = -largestNegative.getValue().getAmount();

            graph.putIfAbsent(largestPositive.getKey(),new BalanceMap());
            graph.get(largestPositive.getKey()).getBalances()
                    .put(largestNegative.getKey(),new Amount(Currency.USD,Math.min(positiveAmount,negativeAmount)));
            double remaining = positiveAmount-negativeAmount;
            final var remainingAmount = new Amount(Currency.USD,remaining);
            if(remaining>0)
                positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositive.getKey(),remainingAmount));
            else
                negativeAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(),remainingAmount));
        }
        return new PaymentGraph(graph);

    }

    public void addExpense(Expense expense) {
        final var groupId = expense.getGroupId();
        if (groupId != null) {
            groupExpenses.putIfAbsent(groupId, new ArrayList<>());
            groupExpenses.get(groupId).add(expense);
        }
    }
}
