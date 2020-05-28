package com.example.quarantine_app.ui.global;

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

import com.example.quarantine_app.R;

import java.util.Map;

public class GlobalFragment extends Fragment {

    private GlobalViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(GlobalViewModel.class);

        View root = inflater.inflate(R.layout.fragment_global, container, false);

        final TextView confirmedCasesAmount = root.findViewById(R.id.text_new_confirmed_amount);
        final TextView totalConfirmedAmount = root.findViewById(R.id.total_confirmed_amount);

        final TextView newDeathsAmount = root.findViewById(R.id.new_deaths_amount);
        final TextView totalDeathsAmount = root.findViewById(R.id.total_deaths_amount);

        final TextView newRecoveredAmount = root.findViewById(R.id.new_recovered_amount);
        final TextView totalRecoveredAmount = root.findViewById(R.id.total_recovered_amount);


        dashboardViewModel.getGlobalData().observe(getViewLifecycleOwner(), data -> {
            confirmedCasesAmount.setText(String.valueOf(data.get("NewConfirmed")).replace(".0", ""));
            totalConfirmedAmount.setText(String.valueOf(data.get("TotalConfirmed")).replace(".0", ""));

            newDeathsAmount.setText(String.valueOf(data.get("NewDeaths")).replace(".0", ""));
            totalDeathsAmount.setText(String.valueOf(data.get("TotalDeaths")).replace(".0", ""));

            newRecoveredAmount.setText(String.valueOf(data.get("NewRecovered")).replace(".0", ""));
            totalRecoveredAmount.setText(String.valueOf(data.get("TotalRecovered")).replace(".0", ""));
        });

        return root;
    }
}
