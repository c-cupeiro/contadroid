package org.upv.ccupeiro.contadroid.main.model;

import android.graphics.drawable.Drawable;

import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 07/08/2017.
 */

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
        return String.format("%.2f €",cardAmount);
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }
    public void addExpense(Expense expense){
        cardAmount +=expense.getAmount();
        expenseList.add(expense);
    }
}
