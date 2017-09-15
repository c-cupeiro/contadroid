package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;

import java.util.List;

public class TabExpenseNotPaidPresenter {

    private View view;
    private List<CardExpenseItem> expenseNotPaidList;
    private ActualMonthPresenter activityPresenter;

    public TabExpenseNotPaidPresenter(ActualMonthPresenter activityPresenter) {
        this.activityPresenter = activityPresenter;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void initialize(){
        expenseNotPaidList = activityPresenter.getNotPaidExpense();
        if(expenseNotPaidList.size()==0) {
            view.showEmptyCase();
        }else{
            view.showCardExpenses(expenseNotPaidList);
        }
    }

    public void showDialogExpense(CardExpenseItem expense){
        activityPresenter.showDialog(expense);
    }

    public void changePaidStatusExpense(long id,boolean paid){
        activityPresenter.changePaidStatus(id,paid);
    }


    public interface View{
        void showEmptyCase();
        void hideEmptyCase();
        void showCardExpenses(List<CardExpenseItem> cardExpenseItemList);
    }

}
