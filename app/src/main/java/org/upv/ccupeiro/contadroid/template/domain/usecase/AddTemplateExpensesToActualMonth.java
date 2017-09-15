package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

public class AddTemplateExpensesToActualMonth {
    private final ContadroidRepository expenseRepository;

    public AddTemplateExpensesToActualMonth(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean execute(int year, int month){
        return expenseRepository.addTemplateToMonth(year,month);
    }
}
