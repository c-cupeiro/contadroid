package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

import java.util.List;

import static org.upv.ccupeiro.contadroid.common.utils.TransformItem.transformExpenseToCardExpense;

public class GetNotPaidExpenses {
    private final ContadroidRepository expenseRepository;

    public GetNotPaidExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<CardExpenseItem> execute(int year, int month){
        return transformExpenseToCardExpense(expenseRepository.getNotPaidExpensesInMonth(year,month));
    }
}
