package org.upv.ccupeiro.contadroid.summary.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.utils.TransformItem;
import org.upv.ccupeiro.contadroid.template.model.SummaryItem;

import java.util.LinkedList;
import java.util.List;

public class GetYearSummary {
    private final ContadroidRepository expenseRepository;

    public GetYearSummary(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<SummaryItem> execute(int year){
        return TransformItem.transformExpenseToSummary(expenseRepository.getYearExpenses(year));
    }


}
