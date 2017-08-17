package org.upv.ccupeiro.contadroid.detailexpense.model;

import com.pedrogomez.renderers.ListAdapteeCollection;

import java.util.List;

public class ExpenseGroupViewCollection extends ListAdapteeCollection<ExpenseGroupView> {
    public ExpenseGroupViewCollection(List<ExpenseGroupView> expenseGroupViewList) {
        super(expenseGroupViewList);
    }
}
