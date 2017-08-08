package org.upv.ccupeiro.contadroid.main.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.Expense;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 08/08/2017.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    protected List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_expense_row, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.expense_name.setText(expense.getName());
        holder.expense_amount.setText(expense.getAmountEuroString());
        holder.expense_checkbox.setChecked(expense.isPaid());
        holder.expense_description.setText(expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_expense_name)
        TextView expense_name;
        @BindView(R.id.tv_expense_amount)
        TextView expense_amount;
        @BindView(R.id.tv_expense_description)
        TextView expense_description;
        @BindView(R.id.checkbox_expense)
        CheckBox expense_checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
