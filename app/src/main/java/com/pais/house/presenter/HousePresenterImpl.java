package com.pais.house.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.util.Log;

import com.pais.database.DBManager;
import com.pais.domain.house.HouseItem;
import com.pais.house.adapter.HouseAdapter;
import com.pais.house.adapter.HouseAdapterDataModel;
import com.pais.network.SensorDataAPI;
import com.pais.sensormonitor.view.SensorMonitorFragment;
import com.pais.views.PageChange;

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
    PageChange pageChange;
    private static int MODIFY_HOUSE = 0;
    private static int ADD_HOUSE = 1;
    //state
    private int stateCU;
    private int modify_position;

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
        stateCU = ADD_HOUSE;
        view.setEditTextHouseName("");
        view.showAddWindow();
    }

    @Override
    public void addCancleClick() {
        view.hideAddWindow();
    }

    @Override
    public void addOkClick(String name) {
        if(stateCU == ADD_HOUSE) {
            mDBManager.insertHouse(name);
        }else if(stateCU == MODIFY_HOUSE){
            mDBManager.updateHouse(name,mHouseAdapterDataModel.getHouseItem(modify_position).getName());
        }
        List<HouseItem> houseItems = mDBManager.getHouseItems();
        mHouseAdapterDataModel.set(houseItems);
        view.hideAddWindow();
        view.refreshRecycler();
    }

    @Override
    public void onItemClick(int position) {
        pageChange.pageChange(new SensorMonitorFragment().newInstance("P5123 ",""));
    }
    @Override
    public void onItemDeleteClick(int position) {
        mDBManager.deleteHouse(mHouseAdapterDataModel.getHouseItem(position).getName());
        List<HouseItem> houseItems = mDBManager.getHouseItems();
        mHouseAdapterDataModel.set(houseItems);
        view.refreshRecycler();
    }
    @Override
    public void onItemModifyClick(int position) {
        stateCU = MODIFY_HOUSE;
        modify_position = position;
        view.setEditTextHouseName(mHouseAdapterDataModel.getHouseItem(position).getName());
        view.showAddWindow();
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

    @Override
    public void setPageChange(PageChange pageChange) {
        this.pageChange = pageChange;
    }
}
