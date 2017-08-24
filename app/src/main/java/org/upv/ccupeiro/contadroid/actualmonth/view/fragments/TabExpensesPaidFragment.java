package org.upv.ccupeiro.contadroid.actualmonth.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseCollection;
import org.upv.ccupeiro.contadroid.common.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.builder.CardExpenseItemBuilder;
import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.TabExpensePaidPresenter;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRowRenderer;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.upv.ccupeiro.contadroid.common.model.CardExpenseCollection.EMPTY_COLLECTION;

public class TabExpensesPaidFragment extends Fragment implements TabExpensePaidPresenter.View{
    @Nullable
    @BindView(R.id.rv_card_paid_expenses)
    RecyclerView rvPaidExpenses;
    @Nullable
    @BindView(R.id.tv_empty_case)
    TextView emptyCase;

    private RVRendererAdapter<CardExpenseItem> adapter;
    private TabExpensePaidPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_expenses_paid, container, false);
        initializaButterKnife(rootView);
        initializeAdapter();
        initializePresenter();
        initializeRecyclerView();
        return rootView;
    }

    private void initializaButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    private void initializePresenter(){
        presenter = new TabExpensePaidPresenter(((ActualMonthActivity)getActivity()).getPresenter());
        presenter.setView(this);
        presenter.initialize();
    }

    private void initializeAdapter() {
        final AdapteeCollection<CardExpenseItem> cardExpenseItemCollection =
                EMPTY_COLLECTION;
        RendererBuilder<CardExpenseItem> rendererBuilder = new CardExpenseItemBuilder(new CardExpenseListener() {
            @Override
            public void onCheckboxClicked(CardExpenseItem expense) {
                presenter.changePaidStatusExpense(expense.getExpenseId(),!expense.isPaid());
            }

            @Override
            public void OnLongPressRow(CardExpenseItem expense) {
                presenter.showDialogExpense(expense);
            }
        });

        adapter = new RVRendererAdapter<>(rendererBuilder,cardExpenseItemCollection);
    }

    private void initializeRecyclerView(){
        rvPaidExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPaidExpenses.setAdapter(adapter);
    }

    @Override
    public void showEmptyCase() {
        emptyCase.setVisibility(View.VISIBLE);
        rvPaidExpenses.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyCase() {
        emptyCase.setVisibility(View.GONE);
        rvPaidExpenses.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCardExpenses(List<CardExpenseItem> cardExpenseItemList) {
        adapter.clear();
        adapter.addAll(cardExpenseItemList);
        adapter.notifyDataSetChanged();
    }
}
