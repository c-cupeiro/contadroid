package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

public class DeleteTemplateExpenses {
    private final ContadroidRepository expenseRepository;

    public DeleteTemplateExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(long id){
        return expenseRepository.deleteTemplateExpense(id);
    }
}
