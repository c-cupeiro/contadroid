package org.upv.ccupeiro.contadroid.common.utils;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.common.model.comparator.ExpenseDateComparator;
import org.upv.ccupeiro.contadroid.common.model.comparator.ExpenseGroupComparator;
import org.upv.ccupeiro.contadroid.template.model.SummaryItem;
import org.upv.ccupeiro.contadroid.template.model.SummaryItemStatus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class TransformItem {

    public static final int INT_POST_DECEMBER = 12;

    public static List<CardExpenseItem> transformExpenseToCardExpense(List<Expense> expenseList){
        Collections.sort(expenseList, new ExpenseGroupComparator());
        return createCardExpenseItemList(expenseList);
    }

    public static List<SummaryItem> transformExpenseToSummary(List<Expense> expenseList){
        Collections.sort(expenseList, new ExpenseDateComparator());
        return createSummaryItemList(expenseList);
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

    private static List<SummaryItem> createSummaryItemList(List<Expense> expenseList){
        List<SummaryItem> summaryItemList = new LinkedList<>();
        int initMonth = 0;
        for(Expense expense : expenseList){
            int expenseMonth = getMonthFromDate(expense.getCreationDate());
            if(expenseMonth>initMonth) {
                insertMissingMonths(initMonth, expenseMonth, summaryItemList);
            }
            if(summaryItemList.size()==0 || (expenseMonth!=initMonth)){
                initMonth = expenseMonth;
                createNewMonth(expense,expenseMonth,summaryItemList);
            }else{
                addExpenseToMonth(expense,summaryItemList,initMonth);
            }
        }
        validateSummaryItemListAllMonths(initMonth+1,summaryItemList);
        return summaryItemList;
    }

    private static void createNewMonth(Expense expense,int expenseMonth,
                                       List<SummaryItem> summaryItemList) {
        float correctAmount = getCorrectAmount(expense);
        SummaryItemStatus correctStatus = getCorrectStatus(correctAmount);

        SummaryItem month = new SummaryItem.Builder()
                .withName(getMonthName(expenseMonth))
                .withAmount(correctAmount)
                .withSummaryStatus(correctStatus)
                .build();
        summaryItemList.add(month);
    }

    private static void addExpenseToMonth(Expense expense,
                                          List<SummaryItem> summaryItemList, int month_pos) {
        SummaryItem summaryItemUpdated = summaryItemList.get(month_pos);
        float correctAmount = getCorrectAmount(expense);
        summaryItemUpdated.addAmount(correctAmount);
        SummaryItemStatus correctStatus = getCorrectStatus(summaryItemUpdated.getAmount());
        summaryItemUpdated.setStatus(correctStatus);
        summaryItemList.set(month_pos,summaryItemUpdated);
    }

    private static void validateSummaryItemListAllMonths(int initMonth,
                                                         List<SummaryItem> summaryItemList) {
        insertMissingMonths(initMonth, INT_POST_DECEMBER,summaryItemList);
    }

    private static float getCorrectAmount(Expense expense){
        if(isPositiveExpense(expense.getGroup()))
            return expense.getAmount();
        return (-expense.getAmount());
    }

    private static SummaryItemStatus getCorrectStatus(float amount){
        return amount > 0 ? SummaryItemStatus.POSITIVE
                : amount < 0 ? SummaryItemStatus.NEGATIVE
                : SummaryItemStatus.NEUTRAL;
    }

    private static boolean isPositiveExpense(ExpensesGroup group){
        return group == ExpensesGroup.INCOME;
    }

    private static void insertMissingMonths(int initMonth, int expenseMonth,
                                            List<SummaryItem> summaryItemList) {
        while(initMonth<expenseMonth){
            summaryItemList.add(getEmptyMonth(initMonth));
            initMonth++;
        }
    }

    private static SummaryItem getEmptyMonth(int initMonth) {
        return new SummaryItem.Builder()
                        .withName(getMonthName(initMonth))
                        .build();
    }

    private static int getMonthFromDate(Date creationDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creationDate);
        return calendar.get(Calendar.MONTH);
    }

    private static String getMonthName(int monthInt){
        SimpleDateFormat dateFormat = new SimpleDateFormat( "LLLL", new Locale("es","ES"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,monthInt);
        return dateFormat.format(calendar.getTime());
    }
}
