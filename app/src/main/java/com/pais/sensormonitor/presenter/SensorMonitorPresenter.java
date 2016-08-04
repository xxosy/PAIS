package com.pais.sensormonitor.presenter;

import com.pais.domain.Value;
import com.pais.domain.graph.GraphList;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.temperature.TemperatureList;

import java.util.ArrayList;

/**
 * Created by SSL-D on 2016-08-01.
 */

public interface SensorMonitorPresenter {
    void init(String serial);
    void settingTimeTouched();
    void choosableSensorDataTouched(int index);

    interface View{
        void refreshChart(GraphList items);
        void refreshDate(String date);
        void refreshUpdateTime(String time);
        void refreshMainSensorData(Value mainData);
        void refreshSensorDataAtSettingTime();
        void refreshChoosableSensorData(Value data);
        void refreshSelectedSensorGraph();
        void refresh();
    }
}
