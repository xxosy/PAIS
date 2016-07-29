package com.pais.network;

import com.google.android.gms.common.api.Api;
import com.pais.domain.Value;
import com.pais.domain.humidity.HumidityItem;
import com.pais.domain.humidity.HumidityList;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.sensor.SensorList;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by SSL-D on 2016-07-21.
 */

public class SensorDataAPI implements SensorAPI.Service
                        ,HumidityAPI.Service
                        ,TemperatureAPI.Service
                        ,ValueAPI.Service{
    private Retrofit retrofit;

    @Inject
    public SensorDataAPI(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    @Override
    public Observable<HumidityItem> getHumidity(){
        return retrofit.create(HumidityAPI.class)
                .getHumidity();
    }
    @Override
    public Observable<HumidityList> getHumidityList(){
        return retrofit.create(HumidityAPI.class)
                .getHumidityList();
    }
    @Override
    public Observable<SensorItem> getSensor(String serial){
        return retrofit.create(SensorAPI.class)
                .getSensor(serial);
    }

    @Override
    public Observable<SensorList> updateSensorName(String serial, String name) {
        return retrofit.create(SensorAPI.class)
                .updateSensorName(serial,name);
    }

    @Override
    public Observable<SensorList> deleteSensor(String serial) {
        return retrofit.create(SensorAPI.class)
                .deleteSensor(serial);
    }

    @Override
    public Observable<SensorItem[]> getSensorList(){
        return retrofit.create(SensorAPI.class)
                .getSensorList();
    }

    @Override
    public Observable<TemperatureItem> getTemperature() {
        return retrofit.create(TemperatureAPI.class)
                .getTemperature();
    }

    @Override
    public Observable<TemperatureList> getTemperatureList(String sensor_id) {
        return retrofit.create(TemperatureAPI.class)
                .getTemperatureList(sensor_id);
    }

    @Override
    public Observable<Value> getValue(String serial) {
        return retrofit.create(ValueAPI.class)
                .getValue(serial);
    }

    @Override
    public Observable<Value> getValue() {
        return retrofit.create(ValueAPI.class)
                .getValue();
    }

    @Override
    public Observable<TemperatureList> getValueList() {
        return retrofit.create(ValueAPI.class)
                .getValueList();
    }
}
