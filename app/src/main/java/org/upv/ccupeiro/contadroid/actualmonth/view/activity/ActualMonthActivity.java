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
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.ActualMonthPresenter;
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
    public static final int ADD_EXPENSE_REQUEST_CODE = 101;
    public static final int EDIT_EXPENSE_REQUEST_CODE = 102;
    @Nullable
    @BindView(R.id.tabs_view)
    ViewPager tabsView;
    private MainTabAdapter mainTabAdapter;
    private ActualMonthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ActualMonthPresenter();
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
        DetailExpenseActivity.openWithResult(this, ADD_EXPENSE_REQUEST_CODE);
    }

    public void showDialog(CardExpenseItem cardExpenseItem){
        presenter.showDialog(cardExpenseItem);
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
                        DetailExpenseActivity.openSendDataWithResult(activity,expenseFromCardExpense(cardExpense),EDIT_EXPENSE_REQUEST_CODE);
                        break;
                    case DELETE_OPTION:
                        SnackBarUtils.showShortSnackBar(tabsView,"Borrar elemento");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ADD_EXPENSE_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    SnackBarUtils.showShortSnackBar(tabsView,"Gasto Guardado");
                }
                break;
            case EDIT_EXPENSE_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    SnackBarUtils.showShortSnackBar(tabsView,"Gasto Editado");
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
