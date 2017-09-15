package org.upv.ccupeiro.contadroid.di;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ContadroidApplication extends Application{
    ContadroidComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRealm();
        initializeDependencyInjection();
    }



    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
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
