package com.pais.network;

import com.pais.domain.Value;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by SSL-D on 2016-07-29.
 */

public interface ValueAPI {
    @GET("/value/list")
    Observable<TemperatureList> getValueList();
    @GET("/value/recent/{serial}")
    Observable<Value> getValue(@Path("serial")String serial);
    @GET("/value/recent")
    Observable<Value> getValue();

    interface Service{
        Observable<Value> getValue(String serial);
        Observable<Value> getValue();
        Observable<TemperatureList> getValueList();
    }
}
