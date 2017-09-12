package org.upv.ccupeiro.contadroid;

import android.app.Application;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.datasource.RealmContadroidRepository;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ContadroidApplication extends Application{
    private static ContadroidRepository contadroidRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRealm();
        initializeRepository();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }

    private void initializeRepository() {
        contadroidRepository = new RealmContadroidRepository(Realm.getDefaultInstance());
    }

    public static ContadroidRepository getContadroidRepository() {
        return contadroidRepository;
    }
}
