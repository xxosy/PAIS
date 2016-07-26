package com.pais.home.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.pais.domain.sensor.SensorItem;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by SSL-D on 2016-07-22.
 */

public class SensorSpinnerAdapter implements SensorSpinnerAdapterModel{
    ArrayList<SensorItem> items;

    public SensorSpinnerAdapter(){
        items = new ArrayList<SensorItem>();
    }
    @Override
    public void add(SensorItem item) {
        items.add(item);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public SensorItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public ArrayList<SensorItem> getItems() {
        return items;
    }

    @Override
    public void clear() {
        items.clear();
    }
}
