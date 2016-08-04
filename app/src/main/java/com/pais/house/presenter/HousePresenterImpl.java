package com.pais.house.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.util.Log;

import com.pais.database.DBManager;
import com.pais.domain.house.HouseItem;
import com.pais.house.adapter.HouseAdapter;
import com.pais.house.adapter.HouseAdapterDataModel;
import com.pais.network.SensorDataAPI;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by SSL-D on 2016-08-03.
 */

public class HousePresenterImpl implements HousePresenter {
    HousePresenter.View view;
    SensorDataAPI sensorDataAPI;
    private DBManager mDBManager;
    HouseAdapterDataModel mHouseAdapterDataModel;
    @Inject
    public HousePresenterImpl(HousePresenter.View view, SensorDataAPI sensorDataAPI){
        this.view = view;
        this.sensorDataAPI = sensorDataAPI;

    }

    @Override
    public void initDataBase(Context context) {
        mDBManager = new DBManager(context,"Pais.db",null,1);
    }



    @Override
    public void btnAddHouseClicked() {
        view.showAddWindow();
    }

    @Override
    public void addCancleClick() {
        view.hideAddWindow();
    }

    @Override
    public void addOkClick(String name) {
        mDBManager.insert(name);
        List<HouseItem> houseItems = mDBManager.getHouseItems();
        mHouseAdapterDataModel.set(houseItems);
        view.hideAddWindow();
        view.refreshRecycler();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void initRecycler() {
        List<HouseItem> houseItems = mDBManager.getHouseItems();
        mHouseAdapterDataModel.set(houseItems);
        view.refreshRecycler();
    }

    @Override
    public void setHouseAdapterDataModel(HouseAdapter adapter) {
        this.mHouseAdapterDataModel = adapter;
    }
}
