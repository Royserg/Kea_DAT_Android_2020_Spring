package com.example.quarantine_app.repository;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class CovidAPI {

    private RequestQueue requestQueue;
    private String url = "https://api.covid19api.com/";

    public CovidAPI(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void fetchGlobalData(Response.Listener<JSONObject> listener) {
        String globalDataUrl = url + "summary";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                globalDataUrl,
                null,
                listener,
                error -> fetchGlobalData(listener));

        requestQueue.add(request);
    }

}
