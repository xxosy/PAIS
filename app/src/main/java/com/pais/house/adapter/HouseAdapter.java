package com.pais.house.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pais.R;
import com.pais.domain.house.HouseItem;
import com.pais.house.presenter.HousePresenter;
import com.pais.views.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SSL-D on 2016-08-04.
 */

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder>
                            implements HouseAdapterDataModel,
                                        HouseAdapterDataView{
    private Context context;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<HouseItem> items;
    public HouseAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.house_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvHouseName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return getSize();
    }
    @Override
    public int getSize(){
        return items.size();
    }
    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void add(HouseItem houseItem) {
        items.add(houseItem);
    }
    @Override
    public void set(List<HouseItem> houseItems) {
        this.items = houseItems;
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_house_name)
        TextView tvHouseName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
