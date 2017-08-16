package org.upv.ccupeiro.contadroid.actualmonth.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.model.CardExpenseItem;
import org.upv.ccupeiro.contadroid.actualmonth.model.SimpleCardExpenseItemCollection;
import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.builder.CardExpenseItemBuilder;
import org.upv.ccupeiro.contadroid.actualmonth.view.renderer.CardExpenseRowRenderer;
import org.upv.ccupeiro.contadroid.common.utils.SnackBarUtils;
import org.upv.ccupeiro.contadroid.common.view.BasicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabExpensesPaidFragment extends Fragment {
    @Nullable
    @BindView(R.id.rv_card_paid_expenses)
    RecyclerView rvPaidExpenses;

    private RVRendererAdapter<CardExpenseItem> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_expenses_paid, container, false);
        initializaButterKnife(rootView);
        initAdapter();
        initRecyclerView();
        return rootView;
    }

    private void initializaButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    private void initAdapter() {
        final AdapteeCollection<CardExpenseItem> cardExpenseItemCollection =
                SimpleCardExpenseItemCollection.getPaidCollection();
        RendererBuilder<CardExpenseItem> rendererBuilder = new CardExpenseItemBuilder(new CardExpenseRowRenderer.Listener() {
            @Override
            public void onCheckboxClicked(CardExpenseItem expense) {
                new SnackBarUtils(rvPaidExpenses,"Checkbox clickado: "+expense.getName()).showShortSnackBar();
            }

            @Override
            public void OnLongPressRow(CardExpenseItem expense) {
                ((ActualMonthActivity)getActivity()).showActionDialog(expense);
            }
        });

        adapter = new RVRendererAdapter<>(rendererBuilder,cardExpenseItemCollection);
    }

    private void initRecyclerView(){
        rvPaidExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPaidExpenses.setAdapter(adapter);
    }
}
