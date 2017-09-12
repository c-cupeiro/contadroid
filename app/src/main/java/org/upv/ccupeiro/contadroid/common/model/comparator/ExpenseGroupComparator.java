package org.upv.ccupeiro.contadroid.common.model.comparator;


import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.Comparator;

public class ExpenseGroupComparator implements Comparator<Expense> {

    @Override
    public int compare(Expense expenseActual, Expense expenseToCompare) {
        int groupComp = expenseActual.getGroup().compareTo(expenseToCompare.getGroup());
        if (groupComp != 0)
            return groupComp;
        else {
            return compareById(expenseActual, expenseToCompare);
        }
    }

    private int compareById(Expense expenseActual, Expense expenseToCompare) {
        if (expenseActual.getId() < expenseToCompare.getId()) {
            return -1;
        } else if (expenseActual.getId() > expenseToCompare.getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}
