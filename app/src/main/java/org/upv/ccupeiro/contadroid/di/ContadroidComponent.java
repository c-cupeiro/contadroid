package org.upv.ccupeiro.contadroid.di;

import android.app.Activity;
import android.content.Context;

import org.upv.ccupeiro.contadroid.actualmonth.view.activity.ActualMonthActivity;
import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.detailexpense.view.activity.DetailExpenseActivity;
import org.upv.ccupeiro.contadroid.summary.view.activity.SummaryActivity;
import org.upv.ccupeiro.contadroid.template.view.activity.TemplateActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ContadroidModule.class)
public interface ContadroidComponent {

    Context context();
    ContadroidRepository contadroidRepository();

    void inject(DetailExpenseActivity activity);
    void inject(SummaryActivity activity);
    void inject(TemplateActivity activity);

}
