package com.example.recycler_view_example.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_view_example.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView rowTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout) itemView;
        rowTextView = linearLayout.findViewById(R.id.row_text_view);
    }
}
