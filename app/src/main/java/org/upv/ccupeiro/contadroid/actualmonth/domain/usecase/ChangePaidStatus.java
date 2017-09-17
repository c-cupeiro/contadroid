package org.upv.ccupeiro.contadroid.actualmonth.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;

import javax.inject.Inject;

public class ChangePaidStatus {
    private final ContadroidRepository expenseRepository;

    @Inject
    public ChangePaidStatus(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void execute(long id, boolean paid, RepositoryCallback callback){
        expenseRepository.changePaidState(id,paid,callback);
    }
}
