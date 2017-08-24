package org.upv.ccupeiro.contadroid.template.domain.usecase;

import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;

import java.util.List;

import static org.upv.ccupeiro.contadroid.common.utils.TransformItem.transformExpenseToCardExpense;

public class GetTemplateExpenses {
    private final ContadroidRepository expenseRepository;

    public GetTemplateExpenses(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<CardExpenseItem> execute(){
        return transformExpenseToCardExpense(expenseRepository.getTemplate());
    }
}
