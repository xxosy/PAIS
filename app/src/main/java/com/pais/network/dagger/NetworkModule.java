package com.pais.network.dagger;

import com.pais.network.SensorDataAPI;
import com.pais.network.retrofit.RetrofitCreator;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by SSL-D on 2016-07-21.
 */
@Module
public class NetworkModule {
    @Provides
    public Retrofit provideRetrofit(){
        return RetrofitCreator.createRetrofit();
    }
    @Provides
    public SensorDataAPI provideSensorDataAPI(Retrofit retrofit){
        return new SensorDataAPI(retrofit);
    }
}
