package com.pais.house.presenter;

import android.content.Context;

import com.pais.house.adapter.HouseAdapter;

/**
 * Created by SSL-D on 2016-08-03.
 */

public interface HousePresenter {
    void initDataBase(Context context);
    void btnAddHouseClicked();
    void addCancleClick();
    void addOkClick(String name);
    void onItemClick(int position);
    void initRecycler();
    void setHouseAdapterDataModel(HouseAdapter adapter);
    interface View{
        void showAddWindow();
        void hideAddWindow();
        void refreshRecycler();
    }
}
