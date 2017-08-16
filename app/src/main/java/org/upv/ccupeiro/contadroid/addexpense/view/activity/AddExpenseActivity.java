package org.upv.ccupeiro.contadroid.addexpense.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.addexpense.model.ExpenseGroupView;
import org.upv.ccupeiro.contadroid.addexpense.model.ExpenseGroupViewCollection;
import org.upv.ccupeiro.contadroid.addexpense.model.SimpleExpenseGroup;
import org.upv.ccupeiro.contadroid.addexpense.view.renderer.ExpenseGroupViewRenderer;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.common.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.name;

public class AddExpenseActivity extends AppCompatActivity {

    public static final int GRID_COLUMN = 2;
    public static final String ADD_EXPENSE_SEND_EXPENSE = "AddExpenseSendExpense";
    public static final String ADD_EXPENSE_RETURN_EXPENSE = "addExpenseReturnExpense";
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.et_expense_title)
    protected EditText et_title;
    @BindView(R.id.et_expense_description)
    protected EditText et_description;
    @BindView(R.id.et_expense_amount)
    protected EditText et_amount;
    @BindView(R.id.rv_category_expenses)
    protected RecyclerView rv_category;
    private RVRendererAdapter<ExpenseGroupView> adapter;

    private boolean isEdition=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initializeButterKnife();
        initializeToolbar();
        initializeAdapter();
        checkEdition();
        initializeRecyclerView();
    }


    public static void openWithResult(Activity activity, int idResult){
        Intent intent = new Intent(activity, AddExpenseActivity.class);
        activity.startActivityForResult(intent,idResult);
    }
    public static void openSendDataWithResult(Activity activity,Expense expense, int idResult){
        Intent intent = new Intent(activity, AddExpenseActivity.class);
        intent.putExtra(ADD_EXPENSE_SEND_EXPENSE,expense);
        activity.startActivityForResult(intent,idResult);
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.add_expense_title_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initializeAdapter() {
        final AdapteeCollection<ExpenseGroupView> expenseGroupViewCollection =
                new ExpenseGroupViewCollection(SimpleExpenseGroup.getListExpenseGroup());
        RendererBuilder<ExpenseGroupView> rendererBuilder = new RendererBuilder<ExpenseGroupView>().withPrototype(
            new ExpenseGroupViewRenderer(new ExpenseGroupViewRenderer.Listener() {
                @Override
                public void onRowClicked(ExpenseGroupView group, View viewClicked) {

                    int position = rv_category.getChildLayoutPosition(viewClicked);
                    changeGroupSelected(position);
                }
            })
        ).bind(ExpenseGroupView.class, ExpenseGroupViewRenderer.class);
        adapter = new RVRendererAdapter(rendererBuilder,expenseGroupViewCollection);
    }

    private void checkEdition(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Expense editionExpense = (Expense) extras.getSerializable(ADD_EXPENSE_SEND_EXPENSE);
            isEdition = true;
            renderData(editionExpense);
        }
    }

    private void renderData(Expense editionExpense) {
        et_title.setText(editionExpense.getName());
        if(!editionExpense.getDescription().isEmpty())
            et_description.setText(editionExpense.getDescription());
        et_amount.setText(StringUtils.formatAmount(editionExpense.getAmount()));
        markGroupInAdapter(editionExpense.getGroup());
    }

    private void markGroupInAdapter(ExpensesGroup group) {
        for(int positionGroup = 0; positionGroup<adapter.getItemCount();positionGroup++){
            ExpenseGroupView item = adapter.getItem(positionGroup);
            if(item.getGroupType() == group) {
                item.setSelected(true);
                break;
            }
        }
    }

    private void changeGroupSelected(int id) {
        adapter.clear();
        adapter.setCollection(new ExpenseGroupViewCollection(
                SimpleExpenseGroup.getListExpenseGroup()));
        adapter.getItem(id).setSelected(true);
        adapter.notifyDataSetChanged();
    }

    private void initializeRecyclerView() {
        rv_category.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN));
        rv_category.setAdapter(adapter);
    }

    private void showSnackBar(String text){
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
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
                saveExpense();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveExpense(){
        if(validateExpense()){
            String title = et_title.getText().toString();
            String description = et_description.getText().toString();
            float amount = Float.parseFloat(et_amount.getText().toString());
            ExpensesGroup group = getSelectedGroup();
            Expense expense = new Expense(title,description,amount,group);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(ADD_EXPENSE_RETURN_EXPENSE,expense);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
    private boolean validateExpense(){
        String error = "";
        if(et_title.getText().toString().isEmpty())
            error+=getString(R.string.add_expense_title_error);
        if(et_amount.getText().toString().isEmpty()){
            error+=addSeparator(error)+getString(R.string.add_expense_amount_error);
        }
        if(getSelectedGroup()==ExpensesGroup.EMPTY){
            error+=addSeparator(error)+getString(R.string.add_expense_group_error);
        }

        if(!error.isEmpty()){
            error = getString(R.string.add_expense_init_error)+error;
            showSnackBar(error);
        }
        return error.isEmpty();
    }

    private String addSeparator(String error){
        if(!error.isEmpty())
            return getString(R.string.add_expense_error_separator);
        return "";
    }

    private ExpensesGroup getSelectedGroup(){
        for(int positionGroup = 0; positionGroup<adapter.getItemCount();positionGroup++){
            ExpenseGroupView item = adapter.getItem(positionGroup);
            if(item.isSelected())
                return item.getGroupType();
        }
        return ExpensesGroup.EMPTY;
    }
}
