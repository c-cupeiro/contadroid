package org.upv.ccupeiro.contadroid.common.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;
import org.upv.ccupeiro.contadroid.common.domain.model.Expense;

import javax.inject.Inject;

public class SaveExpense {
    private final ContadroidRepository expenseRepository;

    @Inject
    public SaveExpense(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void execute(Expense expense, RepositoryCallback callback){
        expenseRepository.saveExpense(expense, callback);
    }
}
