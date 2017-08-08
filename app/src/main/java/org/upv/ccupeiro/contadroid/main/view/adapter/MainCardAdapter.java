package org.upv.ccupeiro.contadroid.main.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.main.model.CardExpense;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 07/08/2017.
 */

public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.ViewHolder> {
    protected List<CardExpense> cardsList;
    protected RecyclerView.LayoutManager innerRvLayoutManager;


    public MainCardAdapter(List<CardExpense> cardsList) {
        this.cardsList = cardsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_row, parent, false);

        return new ViewHolder(rootView);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        innerRvLayoutManager = layoutManager;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardExpense cardInfo = cardsList.get(position);
        holder.card_icon.setImageDrawable(cardInfo.getCardLogo());
        holder.card_name.setText(cardInfo.getCardText());
        holder.card_amount.setText(cardInfo.getCardAmountEuroString());
        prepareAdapter(holder.rv_paid_expenses, cardInfo.getExpenseList());
    }

    private void prepareAdapter(RecyclerView rv_paid_expenses, List<Expense> expenseList) {
        ExpenseAdapter adapter = new ExpenseAdapter(expenseList);
        rv_paid_expenses.setLayoutManager(new LinearLayoutManager(null));
        rv_paid_expenses.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_card_title_icon)
        ImageView card_icon;
        @BindView(R.id.tv_card_title_name)
        TextView card_name;
        @BindView(R.id.tv_card_title_amount)
        TextView card_amount;
        @BindView(R.id.rv_paid_expenses)
        RecyclerView rv_paid_expenses;
        ExpenseAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            initialiazeButterknife(itemView);
        }

        private void initialiazeButterknife(View itemView) {
            ButterKnife.bind(this,itemView);
        }



    }
}