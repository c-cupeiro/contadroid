package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import javax.inject.Inject;

public class ChangePaidStatus {
    private final ContadroidRepository expenseRepository;

    @Inject
    public ChangePaidStatus(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(long id, boolean paid){
        return expenseRepository.changePaidState(id,paid);
    }
}
