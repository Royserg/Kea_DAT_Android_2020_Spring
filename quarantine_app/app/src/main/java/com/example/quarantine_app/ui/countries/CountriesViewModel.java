package com.example.quarantine_app.ui.countries;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Response;
import com.example.quarantine_app.models.Country;
import com.example.quarantine_app.repository.CovidAPI;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountriesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Country>> countries = new MutableLiveData<>();
    private CovidAPI covidAPI;

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        covidAPI = new CovidAPI(getApplication());
        loadCountriesData();
    }

    public LiveData<List<Country>> getCountriesData() {

        return countries;
    }

    private void loadCountriesData() {
        Response.Listener<JSONObject> listener = response -> {
            try {
                List<Country> countriesList = new ArrayList<>();

                JSONArray results = response.getJSONArray("Countries");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject result = results.getJSONObject(i);

                    Country country = new Country(
                            result.getString("Country"),
                            result.getInt("NewConfirmed"),
                            result.getInt("NewDeaths"),
                            result.getInt("NewRecovered")
                    );

                    countriesList.add(country);

                }

                countries.setValue(countriesList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        covidAPI.fetchGlobalData(listener);
    }
}