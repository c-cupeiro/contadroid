package org.upv.ccupeiro.contadroid.actualmonth.model;

import org.upv.ccupeiro.contadroid.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleCardExpenseItemCollection {
    public CardExpenseCollection getCollection(){
        List<CardExpenseItem> cardExpenseItemsList = new ArrayList<>();
        cardExpenseItemsList.add(generateGroupHeader(R.drawable.icon_income,"Ingresos",2000));
        cardExpenseItemsList.add(generateExpenseRow("Sueldo","ingreso del trabajo",2000,true));
        return new CardExpenseCollection(cardExpenseItemsList);
    }


    private CardExpenseItem generateGroupHeader(int icon,String name,float amount){
        CardExpenseItem groupHeader = new CardExpenseItem();
        groupHeader.setGroupHeader(true);
        groupHeader.setIcon(icon);
        groupHeader.setName(name);
        groupHeader.setAmount(amount);
        return groupHeader;
    }
    private CardExpenseItem generateExpenseRow(String name,String description,
                                               float amount, boolean paid){
        CardExpenseItem expenseRow = new CardExpenseItem();
        expenseRow.setExpenseRow(true);
        expenseRow.setName(name);
        expenseRow.setDescription(description);
        expenseRow.setAmount(amount);
        expenseRow.setPaid(paid);
        return expenseRow;
    }

}
