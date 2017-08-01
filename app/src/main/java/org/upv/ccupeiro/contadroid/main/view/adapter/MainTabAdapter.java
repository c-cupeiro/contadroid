package org.upv.ccupeiro.contadroid.main.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.upv.ccupeiro.contadroid.main.view.fragments.TabExpensesNotPaidFragment;
import org.upv.ccupeiro.contadroid.main.view.fragments.TabExpensesPaidFragment;

/**
 * Created by Carlos on 01/08/2017.
 */

public class MainTabAdapter extends FragmentStatePagerAdapter {

    private int num_tabs;

    public MainTabAdapter(FragmentManager fm, int num_tabs) {
        super(fm);
        this.num_tabs = num_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TabExpensesPaidFragment();
            case 1:
                return new TabExpensesNotPaidFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return num_tabs;
    }
}
