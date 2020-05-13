package com.example.week13.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week13.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView myTextView;
    public ImageView myImageView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout) itemView;

        myTextView = linearLayout.findViewById(R.id.my_text_view);
        myImageView = linearLayout.findViewById(R.id.my_image_view);
    }

    public void setData(String d) {
        myTextView.setText(d);
    }
}
