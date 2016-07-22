package com.pais.home.presenter;


import com.pais.network.SensorDataAPI;

import javax.inject.Inject;

/**
 * Created by SSL-D on 2016-07-20.
 */

public class HomePresenterImpl implements HomePresenter {
    private View view;
    private SensorDataAPI sensorDataAPI;
    @Inject
    public HomePresenterImpl(View view, SensorDataAPI sensorDataAPI){
        this.view = view;
        this.sensorDataAPI = sensorDataAPI;
    }

    @Override
    public void spinnerItemChanged() {

    }

    @Override
    public void sensorAddButtonTouched() {

    }

    @Override
    public void settingTimeTouched() {

    }

    @Override
    public void choosableSensorDataTouched() {

    }
}
