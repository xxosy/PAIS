package com.pais.home.presenter;

/**
 * Created by SSL-D on 2016-07-20.
 */

public interface HomePresenter {
    void spinnerItemChanged();
    void sensorAddButtonTouched();
    void settingTimeTouched();
    void choosableSensorDataTouched();
    interface View{
        void refreshSensorSpinner();
        void refreshDate(String date);
        void refreshUpdateTime(String time);
        void refreshSelectedSensorData();
        void refreshSubSensorData();
        void refreshSensorDataAtSettingTime();
        void refreshChoosableSensorData();
        void refreshSelectedSensorGraph();
        void refresh();
    }
}
