package com.pais.house.dagger;

import com.pais.house.presenter.HousePresenter;
import com.pais.house.presenter.HousePresenterImpl;
import com.pais.network.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SSL-D on 2016-08-03.
 */
@Module(includes = NetworkModule.class)
public class HouseModule {
    HousePresenter.View view;

    public HouseModule(HousePresenter.View view){
        this.view = view;
    }
    @Provides
    public HousePresenter provideHousePresenter(HousePresenterImpl housePresenter){
        return housePresenter;
    }
    @Provides
    public HousePresenter.View provideView(){
        return view;
    }
}
