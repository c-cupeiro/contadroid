package org.upv.ccupeiro.contadroid.actualmonth.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.ChangePaidStatus;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetNotPaidExpenses;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetPaidExpenses;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.ActualMonthPresenter;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.datasource.SimpleContadroidRepository;
import org.upv.ccupeiro.contadroid.common.domain.usecase.DeleteExpense;
import org.upv.ccupeiro.contadroid.common.domain.usecase.SaveExpense;
import org.upv.ccupeiro.contadroid.detailexpense.view.activity.DetailExpenseActivity;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.utils.SnackBarUtils;
import org.upv.ccupeiro.contadroid.common.view.activity.BasicActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.adapter.MainTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActualMonthActivity extends BasicActivity implements ActualMonthPresenter.View{
    public static final int EDIT_OPTION = 0;
    public static final int DELETE_OPTION = 1;
    public static final int DETAIL_EXPENSE_REQUEST_CODE = 101;
    @Nullable
    @BindView(R.id.tabs_view)
    ViewPager tabsView;
    private MainTabAdapter mainTabAdapter;
    private ActualMonthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFrameLayout(R.layout.activity_main);
        initializePresenter();
        initToolbar();
        initializeTabLayout();
        showFloatingButton();
    }

    public static void open(Activity activity){
        Intent intent = new Intent(activity, ActualMonthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    private void initializePresenter(){
        ContadroidRepository repository = SimpleContadroidRepository.getInstance();
        presenter = new ActualMonthPresenter(new SaveExpense(repository),
                new DeleteExpense(repository),
                new ChangePaidStatus(repository),
                new GetPaidExpenses(repository),
                new GetNotPaidExpenses(repository));
        presenter.setView(this);
    }

    private void initToolbar() {
        setTitle(R.string.actual_month_title);
    }

    private void initializeTabLayout() {
        List<String> tabsNames = new ArrayList<>();
        tabsNames.add(getString(R.string.actual_month_tab_expenses_paid));
        tabsNames.add(getString(R.string.actual_month_tab_expenses_not_paid));
        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(),tabsNames.size());
        tabsView.setAdapter(mainTabAdapter);
        super.initializeTabLayout(tabsNames, tabsView);
    }

    @OnClick(R.id.fab)
    void clickOnFab(View view) {
        presenter.onFabClicked();
    }

    @Override
    public void startFabAction() {
        DetailExpenseActivity.openWithResult(this, DETAIL_EXPENSE_REQUEST_CODE);
    }

    @Override
    public void showActionDialog(final CardExpenseItem cardExpense) {
        final Activity activity = this;
        AlertDialog.Builder menu = new AlertDialog.Builder(activity);
        CharSequence[] options = {getString(R.string.actual_month_dialog_edit),getString(R.string.actual_month_dialog_delete)};
        menu.setItems(options, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int option) {
                switch (option){
                    case EDIT_OPTION:
                        DetailExpenseActivity.openSendDataWithResult(activity,expenseFromCardExpense(cardExpense),DETAIL_EXPENSE_REQUEST_CODE);
                        break;
                    case DELETE_OPTION:
                        presenter.deleteExpense(cardExpense.getExpenseId());
                        break;
                }
            }
        });
        menu.create().show();
    }

    private Expense expenseFromCardExpense(CardExpenseItem cardExpense) {
        Expense.Builder expense = new Expense.Builder()
                .withId(cardExpense.getExpenseId())
                .withName(cardExpense.getName())
                .withDescription(cardExpense.getDescription())
                .withAmount(cardExpense.getAmount())
                .withGroup(cardExpense.getGroup());
        if(cardExpense.isPaid())
            expense.isPaid();
        return expense.build();
    }

    @Override
    public void redrawTabs() {
        mainTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSaveError() {
        SnackBarUtils.showShortSnackBar(tabsView,getString(R.string.save_error));
    }

    @Override
    public void showDeleteError() {
        SnackBarUtils.showShortSnackBar(tabsView,getString(R.string.delete_error));
    }

    @Override
    public void showChangeStatusError() {
        SnackBarUtils.showShortSnackBar(tabsView,getString(R.string.changePaid_error));
    }

    @Override
    public void showSaveCorrect() {
        SnackBarUtils.showShortSnackBar(tabsView,getString(R.string.save_correct));
    }

    @Override
    public void showDeleteCorrect() {
        SnackBarUtils.showShortSnackBar(tabsView,getString(R.string.delete_correct));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == DETAIL_EXPENSE_REQUEST_CODE){
                saveExpense(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveExpense(Intent data) {
        Expense expense = (Expense) data.getExtras()
                .getSerializable(DetailExpenseActivity.DETAIL_EXPENSE_RETURN_EXPENSE);
        presenter.saveExpense(expense);
    }

    public ActualMonthPresenter getPresenter() {
        return presenter;
    }
}
