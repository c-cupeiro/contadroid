package org.upv.ccupeiro.contadroid.common.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

public class DeleteExpense {
    private final ContadroidRepository expenseRepository;

    public DeleteExpense(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(int id){
        return expenseRepository.deleteExpense(id);
    }
}
