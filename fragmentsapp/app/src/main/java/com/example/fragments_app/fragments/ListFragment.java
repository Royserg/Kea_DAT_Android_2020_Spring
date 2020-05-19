package com.example.fragments_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragments_app.MainActivity;
import com.example.fragments_app.R;
import com.example.fragments_app.adapters.ListAdapter;

public class ListFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.fragment_list, container, false);
        listView = layoutView.findViewById(R.id.list);

        listView.setAdapter(new ListAdapter((MainActivity) getActivity()));

        return layoutView;
    }
}
