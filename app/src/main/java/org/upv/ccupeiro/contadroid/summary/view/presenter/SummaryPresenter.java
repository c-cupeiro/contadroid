package org.upv.ccupeiro.contadroid.summary.view.presenter;


import org.upv.ccupeiro.contadroid.summary.domain.usecase.GetYearSummary;
import org.upv.ccupeiro.contadroid.template.model.SummaryItem;

import java.util.Calendar;
import java.util.List;

public class SummaryPresenter {

    private GetYearSummary getYearSummary;
    private View view;

    public SummaryPresenter(GetYearSummary getYearSummary) {
        this.getYearSummary = getYearSummary;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void initialize(){
        Calendar actualDate = Calendar.getInstance();
        showInfo(getYearSummary.execute(actualDate.get(Calendar.YEAR)));
    }

    private void showInfo(List<SummaryItem> summaryItemList){

        if(summaryItemList.size()==0)
            view.showEmptyCase();
        else
            view.showSummaryList(summaryItemList);
    }


    public interface View{
        void showSummaryList(List<SummaryItem> expenseTemplateList);
        void showEmptyCase();
    }

}
