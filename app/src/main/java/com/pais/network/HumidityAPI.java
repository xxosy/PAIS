package com.pais.network;

import com.pais.domain.humidity.HumidityItem;
import com.pais.domain.humidity.HumidityList;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.sensor.SensorList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by SSL-D on 2016-07-22.
 */

public interface HumidityAPI {
    @GET("/humidity/list")
    Observable<HumidityList> getHumidityList();
    @GET("/humidity/recent")
    Observable<HumidityItem> getHumidity();

    interface Service{
        Observable<HumidityItem> getHumidity();
        Observable<HumidityList> getHumidityList();
    }
}
