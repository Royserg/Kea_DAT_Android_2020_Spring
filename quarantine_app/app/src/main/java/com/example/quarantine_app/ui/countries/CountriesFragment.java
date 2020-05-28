package com.example.quarantine_app.ui.countries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantine_app.R;

public class CountriesFragment extends Fragment {

    private CountriesViewModel notificationsViewModel;
    private RecyclerView countriesRecyclerView;
    private CountriesAdapter countriesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(CountriesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_countries, container, false);

        countriesRecyclerView = root.findViewById(R.id.countries_recycler_view);
        countriesAdapter = new CountriesAdapter();
        countriesRecyclerView.setAdapter(countriesAdapter);


        notificationsViewModel.getCountriesData().observe(getViewLifecycleOwner(), data -> countriesAdapter.setList(data));
        return root;
    }
}
