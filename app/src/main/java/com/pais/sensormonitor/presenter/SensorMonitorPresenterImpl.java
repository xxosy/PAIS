package com.pais.sensormonitor.presenter;

import com.pais.home.adapter.SensorSpinnerAdapterModel;
import com.pais.network.SensorDataAPI;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SSL-D on 2016-08-01.
 */

public class SensorMonitorPresenterImpl implements SensorMonitorPresenter {
    private SensorMonitorPresenter.View view;
    private SensorDataAPI sensorDataAPI;
    @Inject
    SensorSpinnerAdapterModel spinner;

    @Inject
    public SensorMonitorPresenterImpl(SensorMonitorPresenter.View view, SensorDataAPI sensorDataAPI){
        this.view = view;
        this.sensorDataAPI = sensorDataAPI;
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
    public void init(){
        sensorDataAPI.getTemperatureList(String.valueOf(spinner.getItem(0).getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {

                    view.refreshChart(list);

                });
    }
    @Override
    public void settingTimeTouched() {

    }

    @Override
    public void choosableSensorDataTouched() {

    }
}
