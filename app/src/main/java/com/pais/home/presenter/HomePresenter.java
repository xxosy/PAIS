package com.pais.home.presenter;

import com.pais.domain.sensor.SensorItem;

import java.util.ArrayList;

/**
 * Created by SSL-D on 2016-07-20.
 */

public interface HomePresenter {
    void initHome();
    void spinnerItemChanged();
    void sensorAddButtonTouched();
    void settingTimeTouched();
    void choosableSensorDataTouched();
    interface View{
        void refreshSensorSpinner(ArrayList<SensorItem> items);
        void refreshChart(float[] graph);
        void refreshDate(String date);
        void refreshUpdateTime(String time);
        void refreshMainSensorData();
        void refreshSubSensorData();
        void refreshSensorDataAtSettingTime();
        void refreshChoosableSensorData();
        void refreshSelectedSensorGraph();
        void refresh();
    }
}
