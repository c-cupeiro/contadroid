package org.upv.ccupeiro.contadroid.template.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseCollection;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.datasource.SimpleContadroidRepository;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.utils.SnackBarUtils;
import org.upv.ccupeiro.contadroid.common.view.activity.BasicActivity;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;
import org.upv.ccupeiro.contadroid.detailexpense.view.activity.DetailExpenseActivity;
import org.upv.ccupeiro.contadroid.template.domain.usecase.DeleteTemplateExpenses;
import org.upv.ccupeiro.contadroid.template.domain.usecase.GetTemplateExpenses;
import org.upv.ccupeiro.contadroid.template.domain.usecase.SaveTemplateExpenses;
import org.upv.ccupeiro.contadroid.template.view.builder.CardTemplateExpenseItemBuilder;
import org.upv.ccupeiro.contadroid.template.view.presenter.TemplatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity.DELETE_OPTION;
import static org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity.EDIT_OPTION;
import static org.upv.ccupeiro.contadroid.detailexpense.view.activity.DetailExpenseActivity.DETAIL_EXPENSE_REQUEST_CODE;


public class TemplateActivity extends BasicActivity implements TemplatePresenter.View{
    public static final CardExpenseCollection EMPTY_COLLECTION = new CardExpenseCollection(new ArrayList<CardExpenseItem>());
    @Nullable
    @BindView(R.id.rv_template)
    RecyclerView rvTemplate;
    @Nullable
    @BindView(R.id.tv_empty_case)
    TextView emptyCase;

    private RVRendererAdapter<CardExpenseItem> adapter;
    private TemplatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFrameLayout(R.layout.activity_template);
        initToolbar();
        initializeAdapter();
        initializeRecyclerView();
        showFloatingButton();
        initializePresenter();
    }

    public static void open(Activity activity){
        Intent intent = new Intent(activity, TemplateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    private void initToolbar() {
        setTitle(R.string.template_title);
    }

    private void initializePresenter(){
        ContadroidRepository repository = SimpleContadroidRepository.getInstance();
        presenter = new TemplatePresenter(new GetTemplateExpenses(repository),
                new SaveTemplateExpenses(repository),
                new DeleteTemplateExpenses(repository));
        presenter.setView(this);
        presenter.initialize();
    }


    private void initializeAdapter() {
        final AdapteeCollection<CardExpenseItem> cardExpenseItemCollection =
                EMPTY_COLLECTION;
        RendererBuilder<CardExpenseItem> rendererBuilder = new CardTemplateExpenseItemBuilder(new CardExpenseListener() {
            @Override
            public void onCheckboxClicked(CardExpenseItem expense) {
            }

            @Override
            public void OnLongPressRow(CardExpenseItem expense) {
                presenter.showDialog(expense);
            }
        });
        adapter = new RVRendererAdapter<>(rendererBuilder,cardExpenseItemCollection);
    }

    private void initializeRecyclerView(){
        rvTemplate.setLayoutManager(new LinearLayoutManager(this));
        rvTemplate.setAdapter(adapter);
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
    public void showExpenseTemplateList(List<CardExpenseItem> expenseTemplateList) {
        adapter.clear();
        adapter.addAll(expenseTemplateList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showActionDialog(final CardExpenseItem cardExpenseItem) {
        final Activity activity = this;
        AlertDialog.Builder menu = new AlertDialog.Builder(activity);
        CharSequence[] options = {getString(R.string.actual_month_dialog_edit),getString(R.string.actual_month_dialog_delete)};
        menu.setItems(options, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int option) {
                switch (option){
                    case EDIT_OPTION:
                        DetailExpenseActivity.openSendDataWithResult(activity,expenseFromCardExpense(cardExpenseItem),DETAIL_EXPENSE_REQUEST_CODE);
                        break;
                    case DELETE_OPTION:
                        presenter.deleteTemplateExpense(cardExpenseItem.getExpenseId());
                        break;
                }
            }
        });
        menu.create().show();
    }

    @Override
    public void showSaveError() {
        SnackBarUtils.showShortSnackBar(rvTemplate,getString(R.string.save_error));
    }

    @Override
    public void showDeleteError() {
        SnackBarUtils.showShortSnackBar(rvTemplate,getString(R.string.delete_error));
    }

    @Override
    public void showSaveCorrect() {
        SnackBarUtils.showShortSnackBar(rvTemplate,getString(R.string.save_correct));
    }

    @Override
    public void showDeleteCorrect() {
        SnackBarUtils.showShortSnackBar(rvTemplate,getString(R.string.delete_correct));
    }

    @Override
    public void showEmptyCase() {
        emptyCase.setVisibility(View.VISIBLE);
        rvTemplate.setVisibility(View.GONE);
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
        presenter.saveTemplateExpense(expense);
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
}
