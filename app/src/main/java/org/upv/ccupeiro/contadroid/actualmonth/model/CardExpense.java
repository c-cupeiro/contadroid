package org.upv.ccupeiro.contadroid.actualmonth.model;

import android.graphics.drawable.Drawable;

import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CardExpense {
    private Drawable cardLogo;
    private String cardText;
    private float cardAmount;
    private List<Expense> expenseList;

    public CardExpense(Drawable cardLogo, String cardText) {
        this.cardLogo = cardLogo;
        this.cardText = cardText;
        this.cardAmount = 0;
        expenseList = new ArrayList<>();
    }

    public CardExpense(Drawable cardLogo, String cardText,List<Expense> expenseList) {
        this.cardLogo = cardLogo;
        this.cardText = cardText;
        this.cardAmount = 0;
        this.expenseList = expenseList;
    }

    public Drawable getCardLogo() {
        return cardLogo;
    }

    public String getCardText() {
        return cardText;
    }

    public float getCardAmount() {
        return cardAmount;
    }

    public String getCardAmountEuroString(){
        return StringUtils.formatAmount(cardAmount);
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense){
        cardAmount +=expense.getAmount();
        expenseList.add(expense);
    }
}
