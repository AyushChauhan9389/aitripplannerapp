package com.mad.tripon;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView nameView , addressView;

    LinearLayout layout;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.linearlayout1);
        img = itemView.findViewById(R.id.img);
        nameView = itemView.findViewById(R.id.nameplace);
        addressView = itemView.findViewById(R.id.addressplace);


    }
}
