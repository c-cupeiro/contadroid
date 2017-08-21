package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;

public class ActualMonthPresenter {

    View view;

    public void setView(View view) {
        this.view = view;
    }

    public void onFabClicked(){
        view.startFabAction();
    }

    public void showDialog(CardExpenseItem cardExpenseItem){
        view.showActionDialog(cardExpenseItem);
    }

    public interface View{
        void startFabAction();
        void showActionDialog(CardExpenseItem cardExpenseItem);
    }
}
