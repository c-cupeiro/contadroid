package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.model.SimpleCardExpenseItemCollection;

import java.util.List;

public class TabExpenseNotPaidPresenter {

    private View view;
    private List<CardExpenseItem> expenseNotPaidList;

    public void setView(View view) {
        this.view = view;
    }

    public void initialize(){
        expenseNotPaidList = SimpleCardExpenseItemCollection.getNotPaidExpenseList();
        if(expenseNotPaidList.size()==0)
            view.showEmptyCase();
        else
            view.showCardExpenses(expenseNotPaidList);
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
