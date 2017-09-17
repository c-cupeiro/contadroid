package org.upv.ccupeiro.contadroid.common.domain.model;

import com.pedrogomez.renderers.ListAdapteeCollection;

import java.util.ArrayList;
import java.util.List;

public class CardExpenseCollection extends ListAdapteeCollection<CardExpenseItem> {
    public CardExpenseCollection(List<CardExpenseItem> cardExpenseItems){
        super(cardExpenseItems);
    }
    public static final CardExpenseCollection EMPTY_COLLECTION = new CardExpenseCollection(new ArrayList<CardExpenseItem>());
}
