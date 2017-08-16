package org.upv.ccupeiro.contadroid.common.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.upv.ccupeiro.contadroid.R;
import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;

import java.util.List;

public abstract class BasicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;
    @Nullable
    @BindView(R.id.nav_view)
    protected NavigationView navigationView;
    @Nullable
    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawer;
    @Nullable
    @BindView(R.id.content_frame)
    protected FrameLayout content_Layout;
    @Nullable
    @BindView(R.id.fab)
    protected FloatingActionButton fab;

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
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_actual_month) {
            ActualMonthActivity.open(this);
            showSnakcbar("Has pulsado el mes Actual");
        } else if (id == R.id.nav_chart) {
            showSnakcbar("Has pulsado la grafica");
        } else if (id == R.id.nav_template) {
            showSnakcbar("Has pulsado la plantilla");
        } else if (id == R.id.nav_settings) {
            showSnakcbar("Has pulsado las opciones");
        } else if (id == R.id.nav_login) {
            showSnakcbar("Has pulsado login");
        } else if (id == R.id.nav_logout) {
            showSnakcbar("Has pulsado logout");
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showSnakcbar(String text){
        Snackbar.make(mDrawer, text, Snackbar.LENGTH_LONG).show();
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
