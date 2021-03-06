package org.upv.ccupeiro.contadroid.common.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.common.utils.SnackBarUtils;
import org.upv.ccupeiro.contadroid.di.ContadroidApplication;
import org.upv.ccupeiro.contadroid.di.ContadroidComponent;
import org.upv.ccupeiro.contadroid.summary.view.activity.SummaryActivity;
import org.upv.ccupeiro.contadroid.template.view.activity.TemplateActivity;

import java.util.List;

public abstract class BasicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @Nullable
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Nullable
    @BindView(R.id.content_frame)
    FrameLayout content_Layout;
    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        initializeButterKnife();
        initializeToolbar();
        initializeNavigationDrawer();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitAlert();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_actual_month) {
            ActualMonthActivity.open(this);
        } else if (id == R.id.nav_chart) {
            SummaryActivity.open(this);
        } else if (id == R.id.nav_template) {
            TemplateActivity.open(this);
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected ContadroidComponent getAppComponent(){
        return ((ContadroidApplication) getApplication()).getComponent();
    }

    public void showSnackBar(String text){
        SnackBarUtils.showShortSnackBar(mDrawer,text);
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initializeNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void initializeFrameLayout(int layoutToInflate) {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutToInflate, null, false);
        content_Layout.addView(contentView, 0);
        initializeButterKnife();
    }

    protected void showFloatingButton() {
        fab.setVisibility(View.VISIBLE);
    }

    protected void hideFloatingButton() {
        fab.setVisibility(View.GONE);
    }

    protected void showExitAlert(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.basic_activity_alert_exit_message)
                .setPositiveButton(R.string.alert_yes_option,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        }).setNegativeButton(R.string.alert_no_option,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    protected void initializeTabLayout(List<String> tabsNames, final ViewPager tabView) {
        tabLayout.setVisibility(View.VISIBLE);
        for(String tab: tabsNames){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabView.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
