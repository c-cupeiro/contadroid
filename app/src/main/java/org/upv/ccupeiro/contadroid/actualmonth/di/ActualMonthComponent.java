package org.upv.ccupeiro.contadroid.actualmonth.di;

import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.actualmonth.view.fragments.TabExpensesNotPaidFragment;
import org.upv.ccupeiro.contadroid.actualmonth.view.fragments.TabExpensesPaidFragment;
import org.upv.ccupeiro.contadroid.di.ActivityScope;
import org.upv.ccupeiro.contadroid.di.ContadroidComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ContadroidComponent.class, modules = ActualMonthModule.class)
public interface ActualMonthComponent {
    void inject(ActualMonthActivity activity);
    void inject(TabExpensesPaidFragment fragment);
    void inject(TabExpensesNotPaidFragment fragment);
}
