package org.upv.ccupeiro.contadroid.common.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import javax.inject.Inject;

public class DeleteExpense {
    private final ContadroidRepository expenseRepository;

    @Inject
    public DeleteExpense(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(long id){
        return expenseRepository.deleteExpense(id);
    }
}
