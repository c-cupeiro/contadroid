package org.upv.ccupeiro.contadroid.di;

import android.app.Application;
import android.content.Context;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.datasource.RealmContadroidRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ContadroidModule {

    private Context application;

    public ContadroidModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesAppContext(){
        return application;
    }

    @Provides
    @Singleton
    public Realm providesRealm(){
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public ContadroidRepository providesContadroidRepository(Realm realm){
        return new RealmContadroidRepository(realm);
    }
}
