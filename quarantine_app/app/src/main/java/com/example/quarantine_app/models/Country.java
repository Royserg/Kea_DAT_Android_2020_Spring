package com.example.quarantine_app.models;

public class Country {
    public String country;
    public int newConfirmed;
    public int newDeaths;
    public int newRecovered;

    public Country(String country, int newConfirmed, int newDeaths, int newRecovered) {
        this.country = country;
        this.newConfirmed = newConfirmed;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
    }
}
