package com.pais.home.presenter;

import com.pais.domain.Value;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.temperature.TemperatureList;

import java.util.ArrayList;

/**
 * Created by SSL-D on 2016-07-20.
 */

public interface HomePresenter {
    void initHome();
    void spinnerItemChanged(String serial);
    void sensorAddButtonTouched();
    interface View{
        void refreshSensorSpinner(ArrayList<SensorItem> items);
    }
}
