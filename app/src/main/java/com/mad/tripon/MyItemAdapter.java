package com.mad.tripon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
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
        myViewHolder.layout.setOnClickListener(v -> {
            Intent intent= new Intent(context,SearchNextActivity.class);
            intent.putExtra("place_id", items.get(position).getPlace_id());
            intent.putExtra("name", items.get(position).getName());
            intent.putExtra("address", items.get(position).getFormatted_address());
            intent.putExtra("lat", items.get(position).getGeometry().location.lat);
            intent.putExtra("lng", items.get(position).getGeometry().location.lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this flag
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
