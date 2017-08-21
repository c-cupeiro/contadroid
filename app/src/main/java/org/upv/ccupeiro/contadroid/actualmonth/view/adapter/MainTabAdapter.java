package org.upv.ccupeiro.contadroid.actualmonth.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.upv.ccupeiro.contadroid.actualmonth.view.fragments.TabExpensesNotPaidFragment;
import org.upv.ccupeiro.contadroid.actualmonth.view.fragments.TabExpensesPaidFragment;

public class MainTabAdapter extends FragmentStatePagerAdapter {

    public static final int TAB_PAID_EXPENSES = 0;
    public static final int TAB_NOT_PAID_EXPENSES = 1;
    private int numTabs;

    public MainTabAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_PAID_EXPENSES:
                return new TabExpensesPaidFragment();
            case TAB_NOT_PAID_EXPENSES:
                return new TabExpensesNotPaidFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
