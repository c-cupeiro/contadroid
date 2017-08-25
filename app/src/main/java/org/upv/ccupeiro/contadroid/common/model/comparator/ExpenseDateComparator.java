package org.upv.ccupeiro.contadroid.common.model.comparator;


import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.Comparator;

public class ExpenseDateComparator  implements Comparator<Expense>{

    @Override
    public int compare(Expense expenseActual, Expense expenseToCompare) {
        return expenseActual.getCreationDate().before(expenseToCompare.getCreationDate()) ? -1
                : expenseActual.getCreationDate().after(expenseToCompare.getCreationDate()) ? 1
                : 0;
    }
}
