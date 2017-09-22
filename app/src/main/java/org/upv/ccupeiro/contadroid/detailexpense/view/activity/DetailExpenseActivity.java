package org.upv.ccupeiro.contadroid.detailexpense.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.detailexpense.model.ExpenseGroupView;
import org.upv.ccupeiro.contadroid.detailexpense.model.ExpenseGroupViewCollection;
import org.upv.ccupeiro.contadroid.detailexpense.model.SimpleExpenseGroup;
import org.upv.ccupeiro.contadroid.detailexpense.view.adapter.DetailGroupAdapter;
import org.upv.ccupeiro.contadroid.detailexpense.view.presenter.DetailExpensePresenter;
import org.upv.ccupeiro.contadroid.detailexpense.view.renderer.ExpenseGroupViewRenderer;
import org.upv.ccupeiro.contadroid.common.domain.model.Expense;
import org.upv.ccupeiro.contadroid.common.domain.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.common.utils.SnackBarUtils;
import org.upv.ccupeiro.contadroid.di.ContadroidApplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailExpenseActivity extends AppCompatActivity implements DetailExpensePresenter.View {

    public static final int DETAIL_EXPENSE_REQUEST_CODE = 101;
    public static final int GRID_COLUMN = 2;
    public static final String ADD_EXPENSE_SEND_EXPENSE = "AddExpenseSendExpense";
    public static final String DETAIL_EXPENSE_RETURN_EXPENSE = "addExpenseReturnExpense";
    public static final String STRING_SEPARATOR = ",";
    public static final String STRING_BLANK = "";
    public static final String STRING_SPACE = " ";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_expense_title)
    EditText et_name;
    @BindView(R.id.et_expense_description)
    EditText et_description;
    @BindView(R.id.et_expense_amount)
    EditText et_amount;
    @BindView(R.id.rv_category_expenses)
    RecyclerView rv_category;
    private DetailGroupAdapter adapter;

    @Inject
    DetailExpensePresenter presenter;

    private boolean isEdition=false;
    private boolean isExpenseEditionPaid = false;
    private long expenseEditionId = -1;
    private Date creationDateEdition = new Date(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        presenter = new DetailExpensePresenter();
        initializeDependencyInjection();
        initializeButterKnife();
        initializeToolbar();
        initializeAdapter();
        initializePresenter();
        initializeRecyclerView();
        setKeyboardHideListener();
    }


    public static void openWithResult(Activity activity, int idResult){
        Intent intent = new Intent(activity, DetailExpenseActivity.class);
        activity.startActivityForResult(intent,idResult);
    }
    public static void openSendDataWithResult(Activity activity,Expense expense, int idResult){
        Intent intent = new Intent(activity, DetailExpenseActivity.class);
        intent.putExtra(ADD_EXPENSE_SEND_EXPENSE,expense);
        activity.startActivityForResult(intent,idResult);
    }

    private void initializeDependencyInjection() {
        ((ContadroidApplication) getApplication()).getComponent().inject(this);
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.detail_expense_title_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initializeAdapter() {
        final AdapteeCollection<ExpenseGroupView> expenseGroupViewCollection =
                new ExpenseGroupViewCollection(new ArrayList<ExpenseGroupView>());
        RendererBuilder<ExpenseGroupView> rendererBuilder = new RendererBuilder<ExpenseGroupView>().withPrototype(
            new ExpenseGroupViewRenderer(new ExpenseGroupViewRenderer.Listener() {
                @Override
                public void onRowClicked(ExpenseGroupView group, View viewClicked) {

                    int groupSelected = rv_category.getChildLayoutPosition(viewClicked);
                    presenter.changeGroupSelected(groupSelected);
                }
            })
        ).bind(ExpenseGroupView.class, ExpenseGroupViewRenderer.class);
        adapter = new DetailGroupAdapter(rendererBuilder,expenseGroupViewCollection);
    }

    private void initializePresenter(){
        presenter.setView(this);
        presenter.initialize();
    }

    private void setKeyboardHideListener(){
        et_amount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    presenter.hideAmountKeyboard();
                }
                return false;
            }
        });
    }

    @Override
    public void hideAmountKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_amount.getWindowToken(), 0);
    }

    @Override
    public void showExpenseInfo(List<ExpenseGroupView> expenseGroupViewList) {
        adapter.clear();
        adapter.addAll(expenseGroupViewList);
        checkEdition();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void saveExpense(){
        if(presenter.validateExpense()){
            String name = et_name.getText().toString();
            String description = et_description.getText().toString();
            float amount = Float.parseFloat(et_amount.getText().toString());
            ExpensesGroup group = adapter.getSelectedGroup();
            Expense.Builder expense = new Expense.Builder()
                    .withName(name)
                    .withDescription(description)
                    .withAmount(amount)
                    .withGroup(group);
            if(isEdition){
                expense.withId(expenseEditionId)
                .withDate(creationDateEdition);
                if(isExpenseEditionPaid)
                    expense.isPaid();
            }else{
                expense.withDate(Calendar.getInstance().getTime());
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra(DETAIL_EXPENSE_RETURN_EXPENSE,expense.build());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
    @Override
    public boolean validateExpense(){
        String error = "";
        if(et_name.getText().toString().isEmpty()) {
            error += getString(R.string.detail_expense_name_error);
        }
        if(et_amount.getText().toString().isEmpty()){
            error+=addSeparator(error)+getString(R.string.detail_expense_amount_error);
        }
        if(adapter.getSelectedGroup()==ExpensesGroup.EMPTY){
            error+=addSeparator(error)+getString(R.string.detail_expense_group_error);
        }

        if(!error.isEmpty()){
            error = new StringBuilder()
                    .append(getString(R.string.detail_expense_init_error))
                    .append(STRING_SPACE)
                    .append(error).toString();
            presenter.showSnackBar(error);
        }
        return error.isEmpty();
    }

    @Override
    public void changeGroupSelected(int id) {
        adapter.clear();
        adapter.setCollection(new ExpenseGroupViewCollection(
                SimpleExpenseGroup.getListExpenseGroup()));
        adapter.getItem(id).setSelected(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSnackBar(String text){
        SnackBarUtils.showLongSnackBar(toolbar,text);
    }

    private void checkEdition(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Expense editionExpense = (Expense) extras.getSerializable(ADD_EXPENSE_SEND_EXPENSE);
            isEdition = true;
            isExpenseEditionPaid = editionExpense.isPaid();
            expenseEditionId = editionExpense.getId();
            creationDateEdition = editionExpense.getCreationDate();
            renderData(editionExpense);
        }
    }

    private void renderData(Expense editionExpense) {
        et_name.setText(editionExpense.getName());
        if(!editionExpense.getDescription().isEmpty())
            et_description.setText(editionExpense.getDescription());
        et_amount.setText(Float.toString(editionExpense.getAmount()));
        adapter.markGroupInAdapter(editionExpense.getGroup());
    }



    private void initializeRecyclerView() {
        rv_category.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN));
        rv_category.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_expense_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                presenter.saveExpense();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private String addSeparator(String error){
        if(!error.isEmpty())
            return STRING_SEPARATOR;
        return STRING_BLANK;
    }
}
