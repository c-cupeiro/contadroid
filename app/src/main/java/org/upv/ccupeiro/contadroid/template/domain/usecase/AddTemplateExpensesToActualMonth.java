package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;

import javax.inject.Inject;

public class AddTemplateExpensesToActualMonth {
    private final ContadroidRepository expenseRepository;

    @Inject
    public AddTemplateExpensesToActualMonth(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void execute(int year, int month, RepositoryCallback callback){
        expenseRepository.addTemplateToMonth(year,month,callback);
    }
}
