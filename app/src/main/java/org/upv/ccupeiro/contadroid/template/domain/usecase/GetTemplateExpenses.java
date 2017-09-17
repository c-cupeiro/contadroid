package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

import java.util.List;

import javax.inject.Inject;

import static org.upv.ccupeiro.contadroid.common.utils.TransformItem.transformExpenseToCardExpense;

public class GetTemplateExpenses {
    private final ContadroidRepository expenseRepository;

    @Inject
    public GetTemplateExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<CardExpenseItem> execute(){
        return transformExpenseToCardExpense(expenseRepository.getTemplate());
    }
}
