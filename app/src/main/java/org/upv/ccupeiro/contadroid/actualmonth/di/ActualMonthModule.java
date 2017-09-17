package org.upv.ccupeiro.contadroid.actualmonth.di;

import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.ChangePaidStatus;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetNotPaidExpenses;
import org.upv.ccupeiro.contadroid.actualmonth.domain.usecase.GetPaidExpenses;
import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.ActualMonthPresenter;
import org.upv.ccupeiro.contadroid.common.domain.usecase.DeleteExpense;
import org.upv.ccupeiro.contadroid.common.domain.usecase.SaveExpense;
import org.upv.ccupeiro.contadroid.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActualMonthModule {

    @ActivityScope
    @Provides
    public ActualMonthPresenter providesActivityPresenter(SaveExpense saveExpense,
                                                          DeleteExpense deleteExpense,
                                                          ChangePaidStatus changePaidStatus,
                                                          GetPaidExpenses getPaidExpenses,
                                                          GetNotPaidExpenses getNotPaidExpenses){
        return new ActualMonthPresenter(saveExpense,deleteExpense,changePaidStatus,
                getPaidExpenses,getNotPaidExpenses);
    }

}
