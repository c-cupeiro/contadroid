package org.upv.ccupeiro.contadroid.detailexpense.view.presenter;

public class DetailExpensePresenter {

    private View view;

    public void initialize(){
        view.showExpenseInfo();
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

    public void showSnackBar(String text){
        view.showSnackBar(text);
    }

    public interface View {
        void showExpenseInfo();
        void saveExpense();
        boolean validateExpense();
        void changeGroupSelected(int id);
        void showSnackBar(String text);
    }
}
