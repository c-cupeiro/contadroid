package org.upv.ccupeiro.contadroid.detailexpense.view.presenter;

import org.upv.ccupeiro.contadroid.detailexpense.model.ExpenseGroupView;
import org.upv.ccupeiro.contadroid.detailexpense.model.SimpleExpenseGroup;

import java.util.List;

import javax.inject.Inject;

public class DetailExpensePresenter {

    private View view;
    private List<ExpenseGroupView> expenseGroupViewList;

    @Inject
    public DetailExpensePresenter() {}

    public void initialize(){
        expenseGroupViewList = SimpleExpenseGroup.getListExpenseGroup();
        view.showExpenseInfo(expenseGroupViewList);
    }


    public void setView(View view){
        this.view = view;
    }

    public void saveExpense(){
        view.saveExpense();
    }

    public boolean validateExpense(){
        return view.validateExpense();
    }

    public void changeGroupSelected(int groupSelectedPosition){
        view.changeGroupSelected(groupSelectedPosition);
    }
    public void hideAmountKeyboard(){
        view.hideAmountKeyboard();
    }

    public void showSnackBar(String text){
        view.showSnackBar(text);
    }

    public interface View {
        void showExpenseInfo(List<ExpenseGroupView> expenseGroupViewList);
        void saveExpense();
        boolean validateExpense();
        void changeGroupSelected(int id);
        void showSnackBar(String text);
        void hideAmountKeyboard();
    }
}
