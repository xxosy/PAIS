package com.pais.home.dagger;

import com.pais.home.adapter.SensorSpinnerAdapter;
import com.pais.home.adapter.SensorSpinnerAdapterModel;
import com.pais.home.presenter.HomePresenter;
import com.pais.home.presenter.HomePresenterImpl;
import com.pais.network.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SSL-D on 2016-07-20.
 */
@Module(includes = NetworkModule.class)
public class HomeModule {
    private HomePresenter.View view;
    private SensorSpinnerAdapter adapter;

    public HomeModule(HomePresenter.View view, SensorSpinnerAdapter adapter){
        this.view = view;
        this.adapter = adapter;
    }

    @Provides
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter){
        return homePresenter;
    }
    @Provides
    HomePresenter.View provideView(){
        return view;
    }
    @Provides
    SensorSpinnerAdapterModel provideSensorSpinnerAdapterModel(){return adapter;}
}
