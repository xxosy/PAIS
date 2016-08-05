package com.pais.views;

import android.support.v7.widget.RecyclerView;

/**
 * Created by SSL-D on 2016-08-05.
 */

public interface OnRecyclerItemModifyClickListener {
    void OnItemModifyClick(RecyclerView.Adapter adapter, int position);
}
