package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;
import org.upv.ccupeiro.contadroid.common.model.Expense;

public class ChangePaidStatus {
    private final ContadroidRepository expenseRepository;

    public ChangePaidStatus(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void execute(long id, boolean paid, RepositoryCallback callback){
        expenseRepository.changePaidState(id,paid,callback);
    }
}
