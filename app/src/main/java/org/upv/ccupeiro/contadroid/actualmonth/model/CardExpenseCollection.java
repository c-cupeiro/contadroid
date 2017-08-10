package org.upv.ccupeiro.contadroid.actualmonth.model;

import com.pedrogomez.renderers.ListAdapteeCollection;

import java.util.List;

public class CardExpenseCollection extends ListAdapteeCollection<CardExpenseItem> {
    public CardExpenseCollection(List<CardExpenseItem> cardExpenseItems){
        super(cardExpenseItems);
    }
}
