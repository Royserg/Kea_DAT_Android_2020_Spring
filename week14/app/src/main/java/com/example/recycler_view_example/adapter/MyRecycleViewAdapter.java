package com.example.recycler_view_example.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_view_example.R;
import com.example.recycler_view_example.view.ViewHolder;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> list;

    public MyRecycleViewAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.custom_row, parent, false);

        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowTextView.setText(list.get(position)); // bind data to a particular row in the view
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
