package com.pais.house.adapter;

import com.pais.domain.house.HouseItem;

import java.util.List;

/**
 * Created by SSL-D on 2016-08-04.
 */

public interface HouseAdapterDataModel {
    int getSize();
    void clear();
    void add(HouseItem houseItem);
    void set(List<HouseItem> houseItems);
    HouseItem getHouseItem(int index);
}
