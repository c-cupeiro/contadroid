package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.model.SimpleCardExpenseItemCollection;

import java.util.List;

public class TabExpensePaidPresenter {

    private View view;
    private List<CardExpenseItem> expensePaidList;

    public void setView(View view) {
        this.view = view;
    }

    public void initialize(){
        expensePaidList = SimpleCardExpenseItemCollection.getPaidExpenseList();
        if(expensePaidList.size()==0)
            view.showEmptyCase();
        else
            view.showCardExpenses(expensePaidList);
    }

    public void showSnackBar(String text){
        view.showSnackBar(text);
    }

    public interface View{
        void showEmptyCase();
        void hideEmptyCase();
        void showCardExpenses(List<CardExpenseItem> cardExpenseItemList);
        void showSnackBar(String text);
    }

}
