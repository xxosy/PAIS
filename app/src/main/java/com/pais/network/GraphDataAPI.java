package com.pais.network;

import com.pais.domain.co2.Co2List;
import com.pais.domain.ec.EcList;
import com.pais.domain.graph.GraphList;
import com.pais.domain.humidity.HumidityList;
import com.pais.domain.light.LightList;
import com.pais.domain.ph.PhList;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by SSL-D on 2016-08-02.
 */

public interface GraphDataAPI {
    @GET("/temperature/list/{serial}")
    Observable<GraphList> getTemperatureList(@Path("serial")String serial);
    @GET("/humidity/list/{serial}")
    Observable<GraphList> getHumidityList(@Path("serial")String serial);
    @GET("/temperature2/list/{serial}")
    Observable<GraphList> getTemperature2List(@Path("serial")String serial);
    @GET("/ec/list/{serial}")
    Observable<GraphList> getEcList(@Path("serial")String serial);
    @GET("/ph/list/{serial}")
    Observable<GraphList> getPhList(@Path("serial")String serial);
    @GET("/light/list/{serial}")
    Observable<GraphList> getLightList(@Path("serial")String serial);
    @GET("/co2/list/{serial}")
    Observable<GraphList> getCo2List(@Path("serial")String serial);
    interface Service{
        Observable<GraphList> getTemperatureList(String serial);
        Observable<GraphList> getHumidityList(String serial);
        Observable<GraphList> getTemperature2List(String serial);
        Observable<GraphList> getEcList(String serial);
        Observable<GraphList> getPhList(String serial);
        Observable<GraphList> getLightList(String serial);
        Observable<GraphList> getCo2List(String serial);
    }
}
