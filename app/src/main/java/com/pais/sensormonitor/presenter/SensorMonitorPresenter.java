package com.pais.sensormonitor.presenter;

import com.pais.domain.Value;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.temperature.TemperatureList;

import java.util.ArrayList;

/**
 * Created by SSL-D on 2016-08-01.
 */

public interface SensorMonitorPresenter {
    void init();
    void settingTimeTouched();
    void choosableSensorDataTouched();

    interface View{
        void refreshChart(TemperatureList items);
        void refreshDate(String date);
        void refreshUpdateTime(String time);
        void refreshMainSensorData(Value mainData);
        void refreshSubSensorData(Value subData);
        void refreshSensorDataAtSettingTime();
        void refreshChoosableSensorData(String temp, String humid, String co2, String light,String ph, String ec);
        void refreshSelectedSensorGraph();
        void refresh();
    }
}
