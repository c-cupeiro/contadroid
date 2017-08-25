package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

public class SaveTemplateExpenses {
    private final ContadroidRepository expenseRepository;

    public SaveTemplateExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(Expense expense){
        return expenseRepository.saveExpense(expense);
    }
}