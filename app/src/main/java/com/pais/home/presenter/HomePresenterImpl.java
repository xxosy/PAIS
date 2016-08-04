package com.pais.home.presenter;


import com.pais.home.adapter.SensorSpinnerAdapterModel;
import com.pais.network.SensorDataAPI;
import com.pais.sensormonitor.view.SensorMonitorFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by SSL-D on 2016-07-20.
 */

public class HomePresenterImpl implements HomePresenter {
    private View view;
    private SensorDataAPI sensorDataAPI;
    private SensorSpinnerAdapterModel sensorSpinnerAdapterModel;
    private Subscription initSubscription;
    private PublishSubject subject;
    private int currentValue;
    private int currentSensor;

    @Inject
    public HomePresenterImpl(View view, SensorDataAPI sensorDataAPI, SensorSpinnerAdapterModel sensorSpinnerAdapterModel){
        this.view = view;
        this.sensorDataAPI = sensorDataAPI;
        this.sensorSpinnerAdapterModel = sensorSpinnerAdapterModel;
        subject = PublishSubject.create();
    }

    @Override
    public void initHome() {
        sensorDataAPI.getSensorList()
                .subscribeOn(Schedulers.io())
                .flatMap(result-> Observable.from(result))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    sensorSpinnerAdapterModel.add(result);
                    view.refreshSensorSpinner(sensorSpinnerAdapterModel.getItems());
                });
    }

    @Override
    public void spinnerItemChanged(String serial) {

    }

    @Override
    public void sensorAddButtonTouched() {

    }
}
