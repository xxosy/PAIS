package com.pais.sensormonitor.presenter;

import com.pais.R;
import com.pais.home.adapter.SensorSpinnerAdapterModel;
import com.pais.network.SensorDataAPI;
import com.pais.sensormonitor.view.ValueTpye;

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
    private String serial;

    @Inject
    public SensorMonitorPresenterImpl(SensorMonitorPresenter.View view, SensorDataAPI sensorDataAPI){
        this.view = view;
        this.sensorDataAPI = sensorDataAPI;
    }
    private void requestPageData4Serial(){
        sensorDataAPI.getValue(serial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.refreshDate(result.getUpdate_date());
                    view.refreshUpdateTime(result.getUpdate_time());
                    view.refreshMainSensorData(result);
                    view.refreshChoosableSensorData(result);
                });
    }
    private void requestGraphData(int index){
        if(index== ValueTpye.TEMPERATURE){
            sensorDataAPI.getTemperatureList(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }else if(index==ValueTpye.HUMIDITY){
            sensorDataAPI.getHumidityList(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }else if(index==ValueTpye.CO2){
            sensorDataAPI.getCo2List(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }else if(index==ValueTpye.EC){
            sensorDataAPI.getEcList(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }else if(index==ValueTpye.PH){
            sensorDataAPI.getPhList(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }else if(index==ValueTpye.LIGHT){
            sensorDataAPI.getLightList(serial)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(list -> {
                        view.refreshChart(list);
                    });
        }
    }
    public void init(String serial){
        this.serial = serial;
        requestPageData4Serial();
        sensorDataAPI.getTemperatureList(serial)
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
    public void choosableSensorDataTouched(int index) {
        requestPageData4Serial();
        requestGraphData(index);
    }
}
