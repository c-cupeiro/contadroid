package org.upv.ccupeiro.contadroid.actualmonth.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.Expense;
import org.upv.ccupeiro.contadroid.common.model.ExpensesGroup;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpense;
import org.upv.ccupeiro.contadroid.actualmonth.view.adapter.MainCardAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabExpensesNotPaidFragment extends Fragment {
    @Nullable
    @BindView(R.id.rv_card_not_paid_expenses)
    RecyclerView rvNotPaidExpenses;

    MainCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_expenses_not_paid, container, false);
        initializaButterKnife(rootView);
        prepareAdapter();
        return rootView;

    }

    private void initializaButterKnife(View view){
        ButterKnife.bind(this,view);
    }

    private void prepareAdapter() {
        adapter = new MainCardAdapter(mockListCardsPaid());
        rvNotPaidExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotPaidExpenses.setAdapter(adapter);
    }

    private List<CardExpense> mockListCardsPaid(){
        List<CardExpense> cardList = new ArrayList<>();
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense("Nombre sin Pago",150.0f, ExpensesGroup.INCOME));
        expenseList.add(new Expense("Nombre sin Pago","Descripción sin pagar",10.0f, ExpensesGroup.INCOME));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_income,null),
                getResources().getString(R.string.group_income),
                expenseList
        ));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_home,null),
                getResources().getString(R.string.group_home),
                expenseList
        ));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_leisure,null),
                getResources().getString(R.string.group_leisure),
                expenseList
        ));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_other,null),
                getResources().getString(R.string.group_other),
                expenseList
        ));
        return cardList;
    }

}
