package com.pais.network;

import com.google.android.gms.common.api.Api;
import com.pais.domain.humidity.HumidityItem;
import com.pais.domain.humidity.HumidityList;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.sensor.SensorList;

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

public class SensorDataAPI {
    private Retrofit retrofit;

    @Inject
    public SensorDataAPI(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Observable<HumidityItem> getHumidity(){
        return retrofit.create(Api.class)
                .getHumidity();
    }
    public Observable<HumidityList> getHumidityList(){
        return retrofit.create(Api.class)
                .getHumidityList();
    }
    public Observable<SensorItem> getSensor(String serial){
        return retrofit.create(Api.class)
                .getSensor(serial);
    }
    public Observable<SensorList> getSensorList(){
        return retrofit.create(Api.class)
                .getSensorList();
    }
    interface Api{
        @GET("/humidity/list")
        Observable<HumidityList> getHumidityList();
        @GET("/humidity/recent")
        Observable<HumidityItem> getHumidity();
        @GET("/sensor/{serial}")
        Observable<SensorItem> getSensor(@Path("serial") String serial);
        @GET("/sensor/list")
        Observable<SensorList> getSensorList();
        @PUT("/sensor/{serial}/{name}")
        Observable<SensorList> updateSensorName(@Path("serial")String serial,@Path("name") String name);
        @DELETE("/sensor/{serial}")
        Observable<SensorList> deleteSensor(@Path("serial")String serial);
    }
}
