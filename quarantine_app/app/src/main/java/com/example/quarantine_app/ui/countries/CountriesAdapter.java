package com.example.quarantine_app.ui.countries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantine_app.R;
import com.example.quarantine_app.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    public List<Country> countries = new ArrayList<>();

    public CountriesAdapter() { }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country currentCountry = countries.get(position);
        holder.setData(currentCountry);
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public void setList(List<Country> data) {
        this.countries.addAll(data);
        this.notifyDataSetChanged();
    }

    /* === View Holder === */
    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private TextView newCases;
        private TextView newDeaths;
        private TextView newRecovered;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.country_name);
            newCases = itemView.findViewById(R.id.country_new_cases);
            newDeaths = itemView.findViewById(R.id.country_new_deaths);
            newRecovered = itemView.findViewById(R.id.country_new_recovered);
        }

        public void setData(Country country) {
            countryName.setText(country.country);
            newCases.setText("New Cases: " + String.valueOf(country.newConfirmed));
            newDeaths.setText("New Deaths: " + String.valueOf(country.newDeaths));
            newRecovered.setText("New Recovered: " + String.valueOf(country.newRecovered));
        }
    }
}
