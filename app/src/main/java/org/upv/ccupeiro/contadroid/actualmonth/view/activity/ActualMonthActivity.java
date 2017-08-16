package org.upv.ccupeiro.contadroid.actualmonth.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.addexpense.view.activity.AddExpenseActivity;
import org.upv.ccupeiro.contadroid.common.view.BasicActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.adapter.MainTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActualMonthActivity extends BasicActivity {

    public static final int ADD_EXPENSE_REQUEST_CODE = 101;
    @Nullable
    @BindView(R.id.tabs_view)
    private ViewPager tabsView;
    private MainTabAdapter mainTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFrameLayout(R.layout.activity_main);
        setTitle(R.string.actual_month_title);
        initializeTabLayout();
        showFloatingButton();
    }

    public static void open(Activity activity){
        Intent intent = new Intent(activity, ActualMonthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    @OnClick(R.id.fab)
    void clickOnFab(View view) {
        AddExpenseActivity.openWithResult(this, ADD_EXPENSE_REQUEST_CODE);
    }

    protected void initializeTabLayout() {
        List<String> tabsNames = new ArrayList<>();
        tabsNames.add(getString(R.string.actual_month_tab_expenses_paid));
        tabsNames.add(getString(R.string.actual_month_tab_expenses_not_paid));
        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(),tabsNames.size());
        tabsView.setAdapter(mainTabAdapter);
        super.initializeTabLayout(tabsNames, tabsView);
    }

}
