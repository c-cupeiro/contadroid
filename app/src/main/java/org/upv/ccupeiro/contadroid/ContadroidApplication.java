package org.upv.ccupeiro.contadroid;

import android.app.Application;

import org.upv.ccupeiro.contadroid.common.data.ContadroidRepository;
import org.upv.ccupeiro.contadroid.common.data.datasource.SimpleMockContadroidRepository;

public class ContadroidApplication extends Application{
    private static ContadroidRepository contadroidRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRepository();
    }

    private void initializeRepository() {
        contadroidRepository = SimpleMockContadroidRepository.getInstance();
    }

    public static ContadroidRepository getContadroidRepository() {
        return contadroidRepository;
    }
}
