package com.pais.home.adapter;

import com.pais.domain.sensor.SensorItem;

import java.util.ArrayList;

/**
 * Created by SSL-D on 2016-07-22.
 */

public interface SensorSpinnerAdapterModel {
    void add(SensorItem item);
    int getSize();
    SensorItem getItem(int index);
    ArrayList<SensorItem> getItems();
    void clear();
}
