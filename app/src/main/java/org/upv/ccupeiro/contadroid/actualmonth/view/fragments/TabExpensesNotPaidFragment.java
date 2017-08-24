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
import org.upv.ccupeiro.contadroid.common.model.SimpleCardExpenseItemCollection;
import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.builder.CardExpenseItemBuilder;
import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.TabExpenseNotPaidPresenter;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRowRenderer;
import org.upv.ccupeiro.contadroid.common.view.listener.CardExpenseListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabExpensesNotPaidFragment extends Fragment implements TabExpenseNotPaidPresenter.View{
    public static final CardExpenseCollection EMPTY_COLLECTION = new CardExpenseCollection(new ArrayList<CardExpenseItem>());
    @Nullable
    @BindView(R.id.rv_card_not_paid_expenses)
    RecyclerView rvNotPaidExpenses;
    @Nullable
    @BindView(R.id.tv_empty_case)
    TextView emptyCase;

    private RVRendererAdapter<CardExpenseItem> adapter;
    private TabExpenseNotPaidPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_expenses_not_paid, container, false);
        initializaButterKnife(rootView);
        initializeAdapter();
        initializePresenter();
        initializeRecyclerView();
        return rootView;

    }

    private void initializaButterKnife(View view){
        ButterKnife.bind(this,view);
    }

    private void initializePresenter(){
        presenter = new TabExpenseNotPaidPresenter(((ActualMonthActivity)getActivity()).getPresenter());
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
        rvNotPaidExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotPaidExpenses.setAdapter(adapter);
    }

    @Override
    public void showEmptyCase() {
        emptyCase.setVisibility(View.VISIBLE);
        rvNotPaidExpenses.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyCase() {
        emptyCase.setVisibility(View.GONE);
        rvNotPaidExpenses.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCardExpenses(List<CardExpenseItem> cardExpenseItemList) {
        adapter.clear();
        adapter.addAll(cardExpenseItemList);
        adapter.notifyDataSetChanged();
    }
}
