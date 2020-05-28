package com.example.quarantine_app.ui.global;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.example.quarantine_app.repository.CovidAPI;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class GlobalViewModel extends AndroidViewModel {

    private MutableLiveData<Map<String, String>> globalData;
    private CovidAPI covidAPI;

    /* Constructor */
    public GlobalViewModel(@NonNull Application application) {
        super(application);

        globalData = new MutableLiveData<>();
        covidAPI = new CovidAPI(getApplication());

        loadGlobalData();
    }


    public LiveData<Map<String, String>> getGlobalData() {
        return globalData;
    }

    /* Fetch global data */
    public void loadGlobalData() {
        Response.Listener<JSONObject> listener = response -> {
            try {
                JSONObject result = response.getJSONObject("Global");
                Gson gson = new Gson();
                Map resultMap = gson.fromJson(result.toString(), Map.class);

                globalData.setValue(resultMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        covidAPI.fetchGlobalData(listener);
    }
}