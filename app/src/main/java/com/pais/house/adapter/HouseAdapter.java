package com.pais.house.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pais.R;
import com.pais.domain.house.HouseItem;
import com.pais.views.OnRecyclerItemClickListener;
import com.pais.views.OnRecyclerItemDeleteClickListener;
import com.pais.views.OnRecyclerItemModifyClickListener;

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
    private OnRecyclerItemModifyClickListener onRecyclerItemModifyClickListener;
    private OnRecyclerItemDeleteClickListener onRecyclerItemDeleteClickListener;
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
        holder.houseItem.setOnClickListener(v -> onRecyclerItemClickListener.onItemClick(HouseAdapter.this,position));
        holder.houseItem.setOnLongClickListener(v -> {
            refreshUpdateItems(holder.updateItems);
            return true;
        });
        holder.btnHouseDelete.setOnClickListener(v -> {
            onRecyclerItemDeleteClickListener.OnItemDeleteClick(HouseAdapter.this,position);
            refreshUpdateItems(holder.updateItems);
        });
        holder.btnHouseModify.setOnClickListener(v -> onRecyclerItemModifyClickListener.OnItemModifyClick(HouseAdapter.this,position));
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
    public HouseItem getHouseItem(int index) {
        return items.get(index);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void setOnRecyclerItemModifyClickListener(OnRecyclerItemModifyClickListener onRecyclerItemModifyClickListener) {
        this.onRecyclerItemModifyClickListener = onRecyclerItemModifyClickListener;
    }

    @Override
    public void setOnRecyclerItemDeleteClickListener(OnRecyclerItemDeleteClickListener onRecyclerItemDeleteClickListener) {
        this.onRecyclerItemDeleteClickListener = onRecyclerItemDeleteClickListener;
    }

    public void refreshUpdateItems(View view) {
        if(view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_house_name)
        TextView tvHouseName;
        @Bind(R.id.houseitem)
        LinearLayout houseItem;
        @Bind(R.id.btn_house_modify)
        LinearLayout btnHouseModify;
        @Bind(R.id.btn_house_delete)
        LinearLayout btnHouseDelete;
        @Bind(R.id.updateItems)
        LinearLayout updateItems;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
