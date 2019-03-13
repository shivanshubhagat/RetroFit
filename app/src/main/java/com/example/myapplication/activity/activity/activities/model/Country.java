package com.example.myapplication.activity.activity.activities.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    private String countryName;
    private String countryCapital;


    public Country(String countryName, String countryCapital) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public String getCountryName() {
        return countryName;
    }


    public String getCountryCapital() {
        return countryCapital;
    }

}

