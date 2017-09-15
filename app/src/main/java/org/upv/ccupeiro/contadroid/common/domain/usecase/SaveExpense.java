package org.upv.ccupeiro.contadroid.common.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import javax.inject.Inject;

public class SaveExpense {
    private final ContadroidRepository expenseRepository;

    @Inject
    public SaveExpense(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(Expense expense){
        return expenseRepository.saveExpense(expense);
    }
}
