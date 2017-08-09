package org.upv.ccupeiro.contadroid.main.view.fragments;

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
import org.upv.ccupeiro.contadroid.main.model.CardExpense;
import org.upv.ccupeiro.contadroid.main.view.adapter.MainCardAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabExpensesPaidFragment extends Fragment {
    @Nullable
    @BindView(R.id.rv_card_paid_expenses)
    RecyclerView rvPaidExpenses;

    MainCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_expenses_paid, container, false);
        initializaButterKnife(rootView);
        prepareAdapter();
        return rootView;
    }

    private void initializaButterKnife(View view){
        ButterKnife.bind(this,view);
    }

    private void prepareAdapter() {
        adapter = new MainCardAdapter(mockListCardsPaid());
        rvPaidExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPaidExpenses.setAdapter(adapter);
    }



    private List<CardExpense> mockListCardsPaid(){
        List<CardExpense> cardList = new ArrayList<>();
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense("Nombre",20.0f, ExpensesGroup.INCOME));
        expenseList.add(new Expense("Nombre","Descripción 1",20.0f, ExpensesGroup.INCOME));
        expenseList.get(0).setPaid(true);
        expenseList.get(1).setPaid(true);
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
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_transport,null),
                getResources().getString(R.string.group_transport),
                expenseList
        ));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_food,null),
                getResources().getString(R.string.group_food),
                expenseList
        ));
        cardList.add(new CardExpense(
                ResourcesCompat.getDrawable(getResources(),R.drawable.icon_shoppings,null),
                getResources().getString(R.string.group_shopping),
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
