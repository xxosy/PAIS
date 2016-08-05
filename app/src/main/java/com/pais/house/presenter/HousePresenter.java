package com.pais.house.presenter;

import android.content.Context;

import com.pais.house.adapter.HouseAdapter;
import com.pais.views.PageChange;

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
    void setPageChange(PageChange pageChange);
    void onItemModifyClick(int position);
    void onItemDeleteClick(int position);

    interface View{
        void showAddWindow();
        void hideAddWindow();
        void refreshRecycler();
        void setEditTextHouseName(String name);
        void showBtnAddHouse();
        void hideBtnAddHouse();
    }
}
