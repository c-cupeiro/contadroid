package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

import java.util.List;

import javax.inject.Inject;

import static org.upv.ccupeiro.contadroid.common.utils.TransformItem.transformExpenseToCardExpense;

public class GetNotPaidExpenses {
    private final ContadroidRepository expenseRepository;

    @Inject
    public GetNotPaidExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<CardExpenseItem> execute(int year, int month){
        return transformExpenseToCardExpense(expenseRepository.getNotPaidExpensesInMonth(year,month));
    }
}
