package org.splitWise.services;

import org.splitWise.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
    public static final Amount NIL = new Amount(Currency.USD, 0);
    private final Map<String, Group> groups;
    private final ExpenseService expenseService;

    public GroupService(Map<String, Group> groups, ExpenseService expenseService) {
        this.groups = groups;
        this.expenseService = expenseService;
    }


    public PaymentGraph getGroupPaymentGraph(final String groupId, final String userId) throws IllegalAccessException {
        BalanceMap expense = getBalances(groupId,userId);
        return expenseService.getPaymentGraph(expense);
    }

    private BalanceMap sumExpenses(List<Expense> groupExpens) {
        Map<String, Amount> resultBalances = new HashMap<>();
        for(Expense expense: groupExpens)
        {
           for(var balance: expense.getUserBalances().getBalances().entrySet())
           {
               final var user = balance.getKey();
               final var amount = balance.getValue();
               resultBalances.put(user,resultBalances.getOrDefault(user,NIL).add(amount));
           }
        }
        return new BalanceMap(resultBalances);
    }

    public BalanceMap getBalances(final String  groupId, String userId) throws IllegalAccessException {
        if(!groups.get(groupId).getUsers().contains(userId))
            throw new IllegalAccessException();

        List<Expense> groupExpenses = expenseService.getGroupExpenses(groupId);
        BalanceMap resultExpense = sumExpenses(groupExpenses);
        return resultExpense;
    }



}
