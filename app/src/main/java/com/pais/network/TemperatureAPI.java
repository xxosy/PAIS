package com.pais.network;

import com.pais.domain.humidity.HumidityItem;
import com.pais.domain.humidity.HumidityList;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by SSL-D on 2016-07-22.
 */

public interface TemperatureAPI {
    @GET("/temperature/list/{sensor_id}")
    Observable<TemperatureList> getTemperatureList(@Path("sensor_id")String sensor_id);
    @GET("/temperature/recent")
    Observable<TemperatureItem> getTemperature();

    interface Service{
        Observable<TemperatureItem> getTemperature();
        Observable<TemperatureList> getTemperatureList(String sensor_id);
    }
}
