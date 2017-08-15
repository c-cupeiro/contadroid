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

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.name;

public class AddExpenseActivity extends AppCompatActivity {

    public static final int GRID_COLUMN = 2;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_expense_title)
    EditText et_title;
    @BindView(R.id.et_expense_description)
    EditText et_description;
    @BindView(R.id.et_expense_amount)
    EditText et_amount;
    @BindView(R.id.rv_category_expenses)
    RecyclerView rv_category;
    private RVRendererAdapter<ExpenseGroupView> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initializeButterKnife();
        initializeToolbar();
        initializeAdapter();
        initializeRecyclerView();
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
                showSnackBar("Click on Save");
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
            //Expense a devolver al adapter de la otra clase
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
