package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;

public class DeleteTemplateExpenses {
    private final ContadroidRepository expenseRepository;

    public DeleteTemplateExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void execute(long id, RepositoryCallback callback){
        expenseRepository.deleteTemplateExpense(id,callback);
    }
}
