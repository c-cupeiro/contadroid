package org.upv.ccupeiro.contadroid.template.view.presenter;


import android.support.v7.app.AlertDialog;

import org.upv.ccupeiro.contadroid.common.data.RepositoryCallback;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.template.domain.usecase.AddTemplateExpensesToActualMonth;
import org.upv.ccupeiro.contadroid.template.domain.usecase.DeleteTemplateExpenses;
import org.upv.ccupeiro.contadroid.template.domain.usecase.GetTemplateExpenses;
import org.upv.ccupeiro.contadroid.template.domain.usecase.SaveTemplateExpenses;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class TemplatePresenter {

    private final GetTemplateExpenses getTemplateExpenses;
    private final SaveTemplateExpenses saveTemplateExpenses;
    private final DeleteTemplateExpenses deleteTemplateExpenses;
    private final AddTemplateExpensesToActualMonth addTemplateExpensesToActualMonth;
    private View view;

    @Inject
    public TemplatePresenter(GetTemplateExpenses getTemplateExpenses,
                             SaveTemplateExpenses saveTemplateExpenses,
                             DeleteTemplateExpenses deleteTemplateExpenses,
                             AddTemplateExpensesToActualMonth addTemplateExpensesToActualMonth) {
        this.getTemplateExpenses = getTemplateExpenses;
        this.saveTemplateExpenses = saveTemplateExpenses;
        this.deleteTemplateExpenses = deleteTemplateExpenses;
        this.addTemplateExpensesToActualMonth = addTemplateExpensesToActualMonth;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onFabClicked() {
        view.startFabAction();
    }

    public void showDialog(CardExpenseItem cardExpenseItem) {
        view.showActionDialog(cardExpenseItem);
    }

    public void initialize() {
        showInfo(getTemplateList());
    }

    public void saveTemplateExpense(Expense expense) {
        saveTemplateExpenses.execute(expense, new RepositoryCallback() {
            @Override
            public void onSuccess() {
                view.showSaveCorrect();
                showInfo(getTemplateList());
            }

            @Override
            public void onError() {
                view.showSaveError();
            }
        });
    }

    private List<CardExpenseItem> getTemplateList() {
        return getTemplateExpenses.execute();
    }

    public void deleteTemplateExpense(long id) {
        deleteTemplateExpenses.execute(id, new RepositoryCallback() {
            @Override
            public void onSuccess() {
                view.showDeleteCorrect();
                showInfo(getTemplateList());
            }

            @Override
            public void onError() {
                view.showDeleteError();
            }
        });
    }

    private void showInfo(List<CardExpenseItem> cardExpenseItemList) {
        if (cardExpenseItemList.size() == 0) {
            view.showEmptyCase();
        }else {
            view.hideEmptyCase();
            view.showExpenseTemplateList(cardExpenseItemList);
        }
    }

    public void showAlertDialog() {
        view.showAlertDialog();
    }

    public void addTemplateCurrentMonth() {
        Calendar actualDate = Calendar.getInstance();
        addTemplateExpensesToActualMonth.execute(
                actualDate.get(Calendar.YEAR),
                actualDate.get(Calendar.MONTH) + 1,
                new RepositoryCallback() {
                    @Override
                    public void onSuccess() {
                        view.showAddTemplateToMonthCorrect();
                    }

                    @Override
                    public void onError() {
                        view.showAddTemplateToMonthError();
                    }
                });
    }

    public interface View {
        void startFabAction();

        void showExpenseTemplateList(List<CardExpenseItem> expenseTemplateList);

        void showActionDialog(CardExpenseItem cardExpenseItem);

        void showSaveError();

        void showSaveCorrect();

        void showDeleteError();

        void showDeleteCorrect();

        void showEmptyCase();

        void hideEmptyCase();

        void showAlertDialog();

        void showAddTemplateToMonthError();

        void showAddTemplateToMonthCorrect();
    }
}
