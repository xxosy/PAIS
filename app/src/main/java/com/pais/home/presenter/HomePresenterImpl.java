package com.pais.home.presenter;


import android.util.Log;

import com.pais.home.adapter.SensorSpinnerAdapterModel;
import com.pais.network.SensorDataAPI;

import java.util.concurrent.TimeUnit;

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
                    sensorDataAPI.getTemperatureList(String.valueOf(sensorSpinnerAdapterModel.getItem(0).getId()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(list -> {
                                view.refreshChart(list);
                            });
                });
        sensorDataAPI.getValue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.refreshDate(result.getUpdate_date());
                    view.refreshUpdateTime(result.getUpdate_time());
                    view.refreshMainSensorData(result);
                    view.refreshSubSensorData(result);
                    view.refreshChoosableSensorData(result.getTemperature(),
                            result.getHumidity(),
                            result.getCo2(),
                            result.getLight(),
                            result.getPh(),
                            result.getEc());
                });

    }
    private void requestPageData4Serial(String serial){
        sensorDataAPI.getValue(serial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.refreshDate(result.getUpdate_date());
                    view.refreshUpdateTime(result.getUpdate_time());
                    view.refreshMainSensorData(result);
                    view.refreshSubSensorData(result);
                    view.refreshChoosableSensorData(result.getTemperature(),
                            result.getHumidity(),
                            result.getCo2(),
                            result.getLight(),
                            result.getPh(),
                            result.getEc());
                });
    }
    @Override
    public void spinnerItemChanged(String serial) {
        requestPageData4Serial(serial);
    }

    @Override
    public void sensorAddButtonTouched() {

    }

    @Override
    public void settingTimeTouched() {

    }

    @Override
    public void choosableSensorDataTouched() {

    }
}
