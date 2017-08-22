package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.model.SimpleCardExpenseItemCollection;

import java.util.List;

public class TabExpensePaidPresenter {

    private View view;
    private List<CardExpenseItem> expensePaidList;
    private ActualMonthPresenter activityPresenter;

    public TabExpensePaidPresenter(ActualMonthPresenter activityPresenter) {
        this.activityPresenter = activityPresenter;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void initialize(){
        expensePaidList = activityPresenter.getPaidExpense();
        if(expensePaidList.size()==0)
            view.showEmptyCase();
        else
            view.showCardExpenses(expensePaidList);
    }

    public void showDialogExpense(CardExpenseItem expense){
        activityPresenter.showDialog(expense);
    }

    public void changePaidStatusExpense(int id,boolean paid){
        activityPresenter.changePaidStatus(id,paid);
    }


    public interface View{
        void showEmptyCase();
        void hideEmptyCase();
        void showCardExpenses(List<CardExpenseItem> cardExpenseItemList);
    }

}