package org.upv.ccupeiro.contadroid.actualmonth.view.presenter;

import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.ChangePaidStatus;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetNotPaidExpenses;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetPaidExpenses;
import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;
import org.upv.ccupeiro.contadroid.common.domain.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.domain.usecase.DeleteExpense;
import org.upv.ccupeiro.contadroid.common.domain.usecase.SaveExpense;
import org.upv.ccupeiro.contadroid.common.domain.model.Expense;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ActualMonthPresenter {

    private final SaveExpense saveExpense;
    private final DeleteExpense deleteExpense;
    private final ChangePaidStatus changePaidStatus;
    private final GetPaidExpenses getPaidExpenses;
    private final GetNotPaidExpenses getNotPaidExpenses;
    private View view;

    @Inject
    public ActualMonthPresenter(SaveExpense saveExpense, DeleteExpense deleteExpense,
                                ChangePaidStatus changePaidStatus, GetPaidExpenses getPaidExpenses,
                                GetNotPaidExpenses getNotPaidExpenses) {
        this.saveExpense = saveExpense;
        this.deleteExpense = deleteExpense;
        this.changePaidStatus = changePaidStatus;
        this.getPaidExpenses = getPaidExpenses;
        this.getNotPaidExpenses = getNotPaidExpenses;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onFabClicked(){
        view.startFabAction();
    }

    public void showDialog(CardExpenseItem cardExpenseItem){
        view.showActionDialog(cardExpenseItem);
    }

    public void saveExpense(Expense expense){
        saveExpense.execute(expense, new RepositoryCallback() {
            @Override
            public void onSuccess() {
                view.redrawTabs();
                view.showSaveCorrect();
            }

            @Override
            public void onError() {
                view.showSaveError();
            }
        });
    }
    public void deleteExpense(long id){
        deleteExpense.execute(id, new RepositoryCallback() {
            @Override
            public void onSuccess() {
                view.redrawTabs();
                view.showDeleteCorrect();
            }

            @Override
            public void onError() {
                view.showDeleteError();
            }
        });
    }

    public void changePaidStatus(long id, boolean paid){
        changePaidStatus.execute(id, paid, new RepositoryCallback() {
            @Override
            public void onSuccess() {
                view.redrawTabs();
            }

            @Override
            public void onError() {
                view.showChangeStatusError();
            }
        });
    }

    public List<CardExpenseItem> getPaidExpense(){
        Calendar actualDate = Calendar.getInstance();
        return getPaidExpenses.execute(
                actualDate.get(Calendar.YEAR),
                actualDate.get(Calendar.MONTH)+1);
    }
    public List<CardExpenseItem> getNotPaidExpense(){
        Calendar actualDate = Calendar.getInstance();
        return getNotPaidExpenses.execute(
                actualDate.get(Calendar.YEAR),
                actualDate.get(Calendar.MONTH)+1);
    }

    public interface View{
        void startFabAction();
        void redrawTabs();
        void showActionDialog(CardExpenseItem cardExpenseItem);
        void showSaveError();
        void showSaveCorrect();
        void showDeleteError();
        void showDeleteCorrect();
        void showChangeStatusError();
    }
}
