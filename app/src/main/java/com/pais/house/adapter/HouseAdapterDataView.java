package com.pais.house.adapter;

import com.pais.views.OnRecyclerItemClickListener;

/**
 * Created by SSL-D on 2016-08-04.
 */

public interface HouseAdapterDataView {
    void refresh();

    void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener);
}
