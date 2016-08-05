package com.pais.house.adapter;

import com.pais.views.OnRecyclerItemClickListener;
import com.pais.views.OnRecyclerItemDeleteClickListener;
import com.pais.views.OnRecyclerItemModifyClickListener;

/**
 * Created by SSL-D on 2016-08-04.
 */

public interface HouseAdapterDataView {
    void refresh();

    void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener);
    void setOnRecyclerItemModifyClickListener(OnRecyclerItemModifyClickListener onRecyclerItemModifyClickListener);
    void setOnRecyclerItemDeleteClickListener(OnRecyclerItemDeleteClickListener onRecyclerItemModifyClickListener);
}
