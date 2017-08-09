package org.upv.ccupeiro.contadroid.main.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.common.view.BasicActivity;
import org.upv.ccupeiro.contadroid.main.view.adapter.MainTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BasicActivity {

    @Nullable
    @BindView(R.id.tabs_view)
    ViewPager tabs_view;
    private MainTabAdapter mainTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFrameLayout(R.layout.activity_main);
        setTitle(R.string.main_title);
        initializeTabLayout();
        initializeFloatingButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnFab(view);
            }
        });
    }

    private void clickOnFab(View view) {
        Snackbar.make(view, "Clickado FAB", Snackbar.LENGTH_LONG).show();
    }

    protected void initializeTabLayout() {
        List<String> tabsNames = new ArrayList<>();
        tabsNames.add(getString(R.string.main_tab_expenses_paid));
        tabsNames.add(getString(R.string.main_tab_expenses_not_paid));
        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(),tabsNames.size());
        tabs_view.setAdapter(mainTabAdapter);
        super.initializeTabLayout(tabsNames, tabs_view);
    }

}
