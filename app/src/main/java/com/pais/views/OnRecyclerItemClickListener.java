package com.pais.views;

import android.support.v7.widget.RecyclerView;

public interface OnRecyclerItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter, int position);
}
