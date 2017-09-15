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
        initializeRepository();
    }

    private void initializeRepository() {
        contadroidRepository = new RealmContadroidRepository(this);
    }

    public static ContadroidRepository getContadroidRepository() {
        return contadroidRepository;
    }
}
