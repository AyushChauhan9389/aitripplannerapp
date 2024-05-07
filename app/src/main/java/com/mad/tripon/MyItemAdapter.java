package com.mad.tripon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter {

    Context context;
    List<PlaceResponseModel.Result> items;

    public MyItemAdapter(Context context, List<PlaceResponseModel.Result> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.nameView.setText(items.get(position).getName());
        myViewHolder.addressView.setText(items.get(position).getFormatted_address());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
