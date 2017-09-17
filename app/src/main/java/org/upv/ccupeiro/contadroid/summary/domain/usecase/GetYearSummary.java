package org.upv.ccupeiro.contadroid.summary.domain.usecase;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.utils.TransformItem;
import org.upv.ccupeiro.contadroid.template.domain.model.SummaryItem;

import java.util.List;

import javax.inject.Inject;

public class GetYearSummary {
    private final ContadroidRepository expenseRepository;

    @Inject
    public GetYearSummary(ContadroidRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public List<SummaryItem> execute(int year){
        return TransformItem.transformExpenseToSummary(expenseRepository.getYearExpenses(year));
    }


}
