package com.mad.tripon;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView nameView , addressView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        nameView = itemView.findViewById(R.id.nameplace);
        addressView = itemView.findViewById(R.id.addressplace);
    }
}
