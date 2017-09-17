package org.upv.ccupeiro.contadroid.di;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ContadroidApplication extends Application{
    ContadroidComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjection();
    }


    private void initializeDependencyInjection() {
        component = DaggerContadroidComponent.builder()
                .contadroidModule(new ContadroidModule(this))
                .build();

    }

    public ContadroidComponent getComponent() {
        return component;
    }
}
