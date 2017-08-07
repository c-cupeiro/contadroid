package org.upv.ccupeiro.contadroid.main.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.upv.ccupeiro.contadroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.string.no;

public class TabExpensesPaidFragment extends Fragment {
    @Nullable
    @BindView(R.id.rv_paid_expenses)
    RecyclerView rvPaidExpenses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_tab_expenses_paid, container, false);
        initializaButterKnife(layout);

        return layout;
    }

    private void initializaButterKnife(View view){
        ButterKnife.bind(this,view);
    }


}
