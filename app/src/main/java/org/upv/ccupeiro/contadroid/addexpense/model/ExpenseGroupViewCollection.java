package org.upv.ccupeiro.contadroid.addexpense.model;

import com.pedrogomez.renderers.ListAdapteeCollection;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;

import java.util.List;

public class ExpenseGroupViewCollection extends ListAdapteeCollection<ExpenseGroupView> {
    public ExpenseGroupViewCollection(List<ExpenseGroupView> expenseGroupViewList) {
        super(expenseGroupViewList);
    }
}
