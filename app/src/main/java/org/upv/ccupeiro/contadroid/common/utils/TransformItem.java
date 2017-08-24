package org.upv.ccupeiro.contadroid.common.utils;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransformItem {
    public static List<CardExpenseItem> transformExpenseToCardExpense(List<Expense> expenseList){
        Collections.sort(expenseList);
        return createCardExpenseItemList(expenseList);
    }

    private static List<CardExpenseItem> createCardExpenseItemList(List<Expense> expenseList){
        List<CardExpenseItem> cardExpenseItemList = new LinkedList<>();
        CardExpenseItem headerItem = CardExpenseItem.CARD_EXPENSE_ITEM_EMPTY;
        for(Expense expense : expenseList){
            if(headerItem.getGroup()!=expense.getGroup()){
                headerItem = generateGroupHeader(expense.getGroup());
                cardExpenseItemList.add(headerItem);
            }
            cardExpenseItemList.add(generateExpenseRow(expense));
            headerItem.setAmount(headerItem.getAmount()+expense.getAmount());
        }
        return cardExpenseItemList;
    }

    private static String getGroupName(ExpensesGroup group ){
        switch (group){
            case INCOME:
                return "Ingresos";
            case HOME:
                return "Casa";
            case TRANSPORT:
                return "Transporte";
            case FOOD:
                return "Comida";
            case SHOPPING:
                return "Compras";
            case LEISURE:
                return "Ocio";
            case OTHER:
                return "Otros";
            case EMPTY:
                return "Vacío";
        }
        return "";
    }

    private static int getIcon(ExpensesGroup group ){
        switch (group){
            case INCOME:
                return R.drawable.icon_income;
            case HOME:
                return R.drawable.icon_home;
            case TRANSPORT:
                return R.drawable.icon_transport;
            case FOOD:
                return R.drawable.icon_food;
            case SHOPPING:
                return R.drawable.icon_shoppings;
            case LEISURE:
                return R.drawable.icon_leisure;
            case OTHER:
                return R.drawable.icon_other;
            case EMPTY:
                return R.drawable.icon_other;
        }
        return R.drawable.icon_other;
    }

    private static CardExpenseItem generateGroupHeader(ExpensesGroup group){
        return new CardExpenseItem.Builder()
                .isGroupHeader()
                .withGroup(group)
                .withIcon(getIcon(group))
                .withName(getGroupName(group))
                .withAmount(0)
                .build();
    }
    private static CardExpenseItem generateExpenseRow(Expense expense){
        CardExpenseItem.Builder expenseRow = new CardExpenseItem.Builder()
                .isExpenseRow()
                .withExpenseId(expense.getId())
                .withGroup(expense.getGroup())
                .withName(expense.getName())
                .withDescription(expense.getDescription())
                .withAmount(expense.getAmount());
        if(expense.isPaid())
            expenseRow.isPaid();
        return expenseRow.build();
    }
}
