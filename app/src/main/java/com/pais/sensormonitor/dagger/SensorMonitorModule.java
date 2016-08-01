package com.pais.sensormonitor.dagger;

import com.pais.home.dagger.HomeModule;
import com.pais.network.dagger.NetworkModule;
import com.pais.sensormonitor.presenter.SensorMonitorPresenter;
import com.pais.sensormonitor.presenter.SensorMonitorPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SSL-D on 2016-08-01.
 */
@Module(includes = NetworkModule.class)
public class SensorMonitorModule {
    private SensorMonitorPresenter.View view;

    public SensorMonitorModule(SensorMonitorPresenter.View view){
        this.view = view;
    }

    @Provides
    SensorMonitorPresenter provideSensorMonitorPresenter(SensorMonitorPresenterImpl sensorMonitorPresenterImpl){
        return sensorMonitorPresenterImpl;
    }
    @Provides
    SensorMonitorPresenter.View provideView(){
        return view;
    }

}
