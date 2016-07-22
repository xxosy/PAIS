package com.pais.network;

import com.pais.domain.sensor.SensorItem;
import com.pais.domain.sensor.SensorList;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by SSL-D on 2016-07-22.
 */

public interface SensorAPI {
    @GET("/sensor/{serial}")
    Observable<SensorItem> getSensor(@Path("serial") String serial);
    @GET("/sensor/list")
    Observable<SensorList> getSensorList();
    @PUT("/sensor/{serial}/{name}")
    Observable<SensorList> updateSensorName(@Path("serial")String serial,@Path("name") String name);
    @DELETE("/sensor/{serial}")
    Observable<SensorList> deleteSensor(@Path("serial")String serial);

    interface Service{
        Observable<SensorList> getSensorList();
        Observable<SensorItem> getSensor(String serial);
        Observable<SensorList> updateSensorName(String serial, String name);
        Observable<SensorList> deleteSensor(String serial);
    }
}
