package org.upv.ccupeiro.contadroid.common.domain.model.comparator;


import org.upv.ccupeiro.contadroid.common.domain.model.Expense;

import java.util.Comparator;

public class ExpenseDateComparator  implements Comparator<Expense>{

    @Override
    public int compare(Expense expenseActual, Expense expenseToCompare) {
        if(expenseActual.getCreationDate().before(expenseToCompare.getCreationDate())){
            return -1;
        }else if(expenseActual.getCreationDate().after(expenseToCompare.getCreationDate())){
            return 1;
        }else{
            return 0;
        }
    }
}
