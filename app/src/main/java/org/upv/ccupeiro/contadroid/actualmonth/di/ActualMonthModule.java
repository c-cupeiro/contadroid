package org.upv.ccupeiro.contadroid.actualmonth.di;

import org.upv.ccupeiro.contadroid.actualmonth.view.presenter.ActualMonthPresenter;
import org.upv.ccupeiro.contadroid.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActualMonthModule {
    private ActualMonthPresenter presenter;

    public ActualMonthModule(ActualMonthPresenter presenter) {
        this.presenter = presenter;
    }

    @ActivityScope
    @Provides
    public ActualMonthPresenter providesActivityPresenter(){
        return presenter;
    }
}
