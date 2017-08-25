package org.upv.ccupeiro.contadroid.common.model.comparator;


import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.Comparator;

public class ExpenseGroupComparator implements Comparator<Expense> {

        @Override
        public int compare(Expense expenseActual, Expense expenseToCompare) {
            int groupComp = expenseActual.getGroup().compareTo(expenseToCompare.getGroup());
            if(groupComp!=0)
                return groupComp;
            else{
                return expenseActual.getId() < expenseToCompare.getId() ? -1
                        : expenseActual.getId() > expenseToCompare.getId() ? 1
                        : 0;
            }
        }
}
