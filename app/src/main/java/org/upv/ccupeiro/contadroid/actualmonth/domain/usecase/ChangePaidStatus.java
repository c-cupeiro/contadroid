package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

public class ChangePaidStatus {
    private final ContadroidRepository expenseRepository;

    public ChangePaidStatus(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(long id, boolean paid){
        return expenseRepository.changePaidState(id,paid);
    }
}
